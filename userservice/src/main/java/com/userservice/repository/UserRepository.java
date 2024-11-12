package com.userservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.userservice.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	 Optional<User> findByEmail(String email);
}
