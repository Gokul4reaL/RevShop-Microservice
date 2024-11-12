package com.cartservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartservice.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	 Optional<User> findByEmail(String email);
}
