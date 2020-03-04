package org.as.devtechsolution.gallery.users.repository;

import java.util.Optional;

import org.as.devtechsolution.gallery.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
	UserEntity findByUserId(String userId);
	

}
