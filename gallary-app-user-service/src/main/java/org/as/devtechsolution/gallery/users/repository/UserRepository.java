package org.as.devtechsolution.gallery.users.repository;

import org.as.devtechsolution.gallery.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

}
