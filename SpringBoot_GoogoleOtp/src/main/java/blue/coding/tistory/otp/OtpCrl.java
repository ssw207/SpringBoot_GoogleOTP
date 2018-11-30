package blue.coding.tistory.otp;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.ICredentialRepository;
import com.warrenstrange.googleauth.IGoogleAuthenticator;

import blue.coding.tistory.user.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OtpCrl {
	
	@Autowired
	private ICredentialRepository gService;//구글auth가 사용하는 서비스
	@Autowired
	private IGoogleAuthenticator gAuth;//구글 API

	//============================페이지이동====================
	//OTP 로그인
	@GetMapping("/otpTest.do")
	public String otpTest() {
		return "otpTest";
	}
	//OTP 등록
	@GetMapping("/otpCheck.do")
	public String otpCheck() {
		return "otpCheck";
	}
	//========================================================
	/**
	 * OTP Key와 URL를 등록하고 유저정보에 입력하는 기능
	 * @param resp
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/generateKeyUrl.do")
	public String generateKeyUrl(HttpSession session, Model model){
		//API가 사용할 구현체 세팅 - 해당 인터페이스를 implements하여 2개의 필수 매소드를 구현한 상태여야함. 
		gAuth.setCredentialRepository(gService);

		String ISSUER="GoogleAuthOTP_Exam"; //등록시 OTP 앱에서 보여지는 사이트명	
		
		User user=(User)session.getAttribute("userDto");
		
		//Key생성 - 생성된 key는 CredentialRepositoryImple에서 구현한 saveUserCredentials메소드에 의해 자동으로 db에 저장됨
		final GoogleAuthenticatorKey authKey = gAuth.createCredentials(user.getUsername()); 
		
		//생성된 키를 가져옴
		String key=authKey.getKey();
		
		//QR코드생성
		String qrCodeUrl                        //지정한 공급자명,     유저아이디            ,  생성한key
		= GoogleAuthenticatorQRGenerator.getOtpAuthURL(ISSUER, user.getUsername(), authKey); 
		
		user.setOtpSecretKey(key);
		
		//스코프에 key, url저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", qrCodeUrl);
		map.put("encodedKey", key);
		model.addAttribute("urlKeyMap", map);
		//유저정보 세션에 다시저장-회원가입후 재로그인 하지않아도 세션에담긴 key를 가지고 OTP로그인 가능하게 하기위함
		session.setAttribute("userDto", user);
		return "otpTest";
	}
	
	//OTP로그인 기능
	@GetMapping("/result.do")
	public String result(HttpServletResponse resp, HttpSession session,String user_code) throws IOException {
		User user=(User) session.getAttribute("userDto");
		//공백제거
		String user_input_code_noEmpty=user_code.trim().replaceAll("\\p{Z}", "");//정규 표현식
		log.info("user_code :{}",user_code);
		
		//사용자가 입력한 OTP 임시 생성 비밀번호 일치여부 확인
		boolean isCodeValid = gAuth.authorize(user.getOtpSecretKey(), Integer.parseInt(user_input_code_noEmpty));
		log.info("코드 유효성검사 : {}", isCodeValid);

		//일치시 로그인
		if(isCodeValid==true) {
			return "main";//true면 인증성공 
		}else{
			return "main";
		}
	}
}
