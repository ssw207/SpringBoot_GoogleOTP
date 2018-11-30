package blue.coding.tistory.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.service.impl.UserServiceImple;

@Controller
public class UserCtrl {
	private Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	//DI
	@Autowired
	private UserServiceImple service;

	//페이지이동
	//초기화면
	@GetMapping("/")
	public String login() {
		logger.info("시작");
		return "login";
	}
	//회원가입 페이지 이동
	@GetMapping("/signUpForm.do")
	public String signUpForm() {
		return "signUpForm";
	}

	//회원가입실행
	@PostMapping("/userInsert.do")
	public String userInsert(User user, Model model, HttpServletResponse resp) throws IOException {
		logger.info("회원가입 : {}",user);
		//회원가입 실행
		service.userInsert(user);
		return "login";
	}

	//로그인
	@PostMapping("/userLogin.do")
	public String userLogin(User user, HttpSession session, HttpServletResponse resp) throws IOException {
		logger.info("시작{}",user);
		//로그인
		String pw=user.getPlainPassword();//사용자가 입력한 패스워드
		User dbUser=service.userLogin(user);
		logger.info("dbUser : {}",dbUser);
		String dbPw=dbUser.getPlainPassword();//db에 저장된 패스워드
		if(pw.equals(dbPw)) {//패스워드가 일치하면
			session.setAttribute("userDto", dbUser);//세션 정보저장
			logger.info("로그인성공{}",user);
			//cService.javaAlert(resp, msg);
			//분기-DB에 OTP key가 있으면 OTP로그인으로 없으면 OTP생성으로
			if(dbUser.getOtpSecretKey()==null) {
				logger.info("사용자가 OTP를 등록했는지 여부 : {}",dbUser.getOtpSecretKey());
				return "redirect:./generateKeyUrl.do";//otp등록으로 이동
			}else{
				logger.info("사용자가 OTP를 등록했는지 여부 : {}",dbUser.getOtpSecretKey());
				return "otpCheck";//otp로그인으로 이동
			}//if2			
		}else {
			return "login";
		}//if
	}//userLogin

	//로그아웃 logout.do
	@GetMapping("/logout.do")
	public String logout(HttpSession session, HttpServletResponse resp) throws IOException {
		User dto= (User)session.getAttribute("userDto");
		if(dto!=null) {
			session.removeAttribute("userDto");
		}
		return "login";//메인 초기 화면으로 이동
	}	
}
