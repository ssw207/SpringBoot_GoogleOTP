package blue.coding.tistory.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import blue.coding.tistory.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);//유저 아이디로 유저 정보를 조회하기 위해 사용
}