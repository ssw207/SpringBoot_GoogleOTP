package blue.coding.tistory.otp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.ICredentialRepository;

import blue.coding.tistory.user.domain.User;
import blue.coding.tistory.user.repository.UserRepository;



@Service
public class CredentialRepositoryImpl implements ICredentialRepository {
	@Autowired
	private UserRepository uRepo;

	/**
	 * 사용자의 id를 입력하면 OTP key를 반환하는 매소드
 	 * API내에서 호출되므로 유저는 직접 이 메소드를 호출할 필요가 없다
	 * @param userName 사용자 id, 사용자 이름이 아니다
	 * @return 유저정보에 저장된 key
	 */
	@Override
	public String getSecretKey(String userName){
		User user= uRepo.findByUsername(userName); // 유저정보 조회
		return user.getOtpSecretKey(); // 유저정보에서 key를 가져옴
	}

	/**
	 * 유저정보 DB에 Key값 저장
	 * API내에서 호출되므로 유저는 직접 이 메소드를 호출할 필요가 없다 
	 * @param userName       유저id
	 * @param secretKey      OTP key
	 */
	@Override
	public void saveUserCredentials(
			String userName,
			String secretKey,
			int validationCode,
			List<Integer> scratchCodes)
	{	
		//JPA에서 업데이트는 기존정보 + 수정할 정보를 save하는 형태로 사용
		User user= uRepo.findByUsername(userName); // 아이디로 user정보 조회, 
		user.setOtpSecretKey(secretKey);
		uRepo.save(user); // 유저정보에 Key 저장
	}

}
