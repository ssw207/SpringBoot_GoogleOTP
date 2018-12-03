package blue.coding.tistory.otp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.warrenstrange.googleauth.ICredentialRepository;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.service.UserIService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CredentialRepositoryImplTest {

	@Autowired 
	ICredentialRepository cretentialRepository;
	@Autowired
	UserIService userIService;

	@Before
	public void setting() {
		//회원가입시 저장되는 유저정보
		User user = new User();
		user.setUsername("userID");
		userIService.userInsert(user);
	}
	
	@Test
	public void key저장_key조회() {
		//given
		String userName = "userID"; 
		String secretKey = "AWEFAGWEGHFWEWEF";
		int validationCode = 0; 
		List<Integer> scratchCodes = null;
		
		//when
		cretentialRepository.saveUserCredentials(userName, secretKey, validationCode, scratchCodes); // 회원 정보에 otpkey 업데이트

		//then
		String key= cretentialRepository.getSecretKey(userName);
		assertThat(key, is(secretKey));

	}
}
