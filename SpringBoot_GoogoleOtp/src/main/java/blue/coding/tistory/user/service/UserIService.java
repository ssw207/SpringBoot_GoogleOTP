package blue.coding.tistory.user.service;

import blue.coding.tistory.user.domain.User;

public interface UserIService {
	//회원가입
	public void userInsert(User user);
	//로그인
	public User userLogin(User user);
}
