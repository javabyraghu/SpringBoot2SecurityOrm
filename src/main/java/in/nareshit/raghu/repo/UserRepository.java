package in.nareshit.raghu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.model.User;

public interface UserRepository 
	extends JpaRepository<User, Integer> {

	// SELECT * FROM usertab  WHERE usrMail=?
	Optional<User> findByUsrMail(String usrMail);
}
