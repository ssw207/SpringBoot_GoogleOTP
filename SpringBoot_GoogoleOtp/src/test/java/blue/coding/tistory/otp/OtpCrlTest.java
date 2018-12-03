package blue.coding.tistory.otp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.ICredentialRepository;
import com.warrenstrange.googleauth.IGoogleAuthenticator;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.service.impl.UserServiceImple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OtpCrlTest {

	@Autowired
	private ICredentialRepository gService;//구글auth가 사용하는 서비스
	@Autowired
	private IGoogleAuthenticator gAuth;//구글 API
	@Autowired
	private UserServiceImple userServiceImple;
	
	User user = null;
	
	@Before
	public void before() {
		gAuth.setCredentialRepository(gService); // API에서 사용할 구현체 세팅
		
		user = new User(); // key를 저장하기위한 회원정보
		user.setUsername("BlueCoding");
		user.setPlainPassword("pw");
	}
	
	@After
	public void cleanup() {
		
	}
	
	@Test
	public void OTP키생성_QR코드생성_DB저장_테스트() {
		//given
		String ISSUER = "commanyName"; // OTP 등록시 노출되는 사이트정보

		//when
		GoogleAuthenticatorKey authKey = gAuth.createCredentials(user.getUsername()); // Key생성, 저장

		User userDBInfo = userServiceImple.userLogin(user); // 저장된 key 조회

		String qrCodeUrl                 		       //지정한 공급자명,     유저아이디            ,  생성한key
		= GoogleAuthenticatorQRGenerator.getOtpAuthURL(ISSUER, userDBInfo.getUsername(), authKey); // QR코드생성

		log.info("getKey : "+authKey.getKey());
		log.info("getDbKey : "+userDBInfo.getOtpSecretKey());

		//then
		assertThat(authKey.getKey(), is(userDBInfo.getOtpSecretKey()));
		assertThat(qrCodeUrl, is(notNullValue())); //  QR코드가 생성되었는지만 체크
	}

	/**
	 * otp인증기능 테스트
	 * OTP키생성_QR코드생성_DB저장_테스트 실행시 자동으로 DB에 key값이 업데이트 되므로 
	 * 위에서 테스트한 유저와 다른유저로 테스트해야 한다
	 */
	@Test
	public void OTP인증기능_테스트() {
		//given
		int tempCode = 668789;
		String userOtpSecretKey = "AWEFAGAWEWFWAEF";
		
		//when
		boolean isCodeValid = gAuth.authorize(userOtpSecretKey , tempCode);
		//then

		assertThat(isCodeValid, is(true));
	}

}
