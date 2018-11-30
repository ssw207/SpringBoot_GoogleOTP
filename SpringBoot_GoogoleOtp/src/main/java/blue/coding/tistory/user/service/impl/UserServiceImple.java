package blue.coding.tistory.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.repository.UserRepository;
import blue.coding.tistory.user.service.UserIService;

@Service
public class UserServiceImple implements UserIService {

	@Autowired
	UserRepository dao;
	/**
	 * 회원가입기능
	 */
	@Override
	public void userInsert(User user) {
		dao.save(user);
	}
	/**
	 * 로그인 기능
	 * */
	@Override
	public User userLogin(User user) {
		return user=dao.findByUsername(user.getUsername());
	}

}
