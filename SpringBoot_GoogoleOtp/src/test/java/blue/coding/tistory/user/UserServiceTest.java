package blue.coding.tistory.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.service.UserIService;
import blue.coding.tistory.user.service.impl.UserServiceImple;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserIService userIService;
	
	//회원가입
	@Test
	public void 회원가입_로그인테스트() {
		//give
		User user = new User();
		user.setUsername("BlueCoding");
		user.setPlainPassword("pw");
		
		//when
		userIService.userInsert(user);
		User userDBInfo= userIService.userLogin(user);
		
		//then
		assertThat(userDBInfo.getUsername(), is("BlueCoding") );
		assertThat(userDBInfo.getPlainPassword(), is("pw") );
	}
	//로그인
	//회원정보조회
	
	 	
}
