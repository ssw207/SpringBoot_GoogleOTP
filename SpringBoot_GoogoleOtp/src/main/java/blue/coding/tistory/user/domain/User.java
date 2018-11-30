package blue.coding.tistory.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Entity//DB상호작용 객체 선언
@NoArgsConstructor//디폴트 생성자 생성
public class User {

	@Id//PK : 유일성을가짐
	@GeneratedValue(strategy = GenerationType.IDENTITY)//시퀀스 생성, 입력하지 않아도 자동으로 1씩증가
	private Long id;
	
	@Column(length=100)
	private String username;//유저id

	private String otpSecretKey;//OTP key
	
	private String plainPassword;//사용자 비밀번호
	
//	@Column
	/**
	 * 기본적으로 5 개의 스크래치 코드가 새로운 공유 암호와 함께 생성됩니다. 
	 * 스크래치 코드는 사용자가 토큰 장치에 액세스 할 수없는 경우 안전망을위한 것입니다. 
	 * 스크래치 노드는 TOTP 표준에 필요한 기능 이 아니며 개발자가 자신의 응용 프로그램에서 사용해야하는지 여부를 결정해야합니다.
	 */
//	private List<Integer> scratchCodes;
}
