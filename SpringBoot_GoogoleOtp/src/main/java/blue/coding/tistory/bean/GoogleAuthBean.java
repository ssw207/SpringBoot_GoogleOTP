package blue.coding.tistory.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;
import com.warrenstrange.googleauth.IGoogleAuthenticator;

import blue.coding.tistory.otp.service.impl.CredentialRepositoryImpl;

@Configuration//@bean을 설정한 매소드를 @Autowired로 호출할수 있게함
public class GoogleAuthBean {
	@Bean
	public IGoogleAuthenticator auth() {
		IGoogleAuthenticator auth=new GoogleAuthenticator();
		return auth;
	}
}
