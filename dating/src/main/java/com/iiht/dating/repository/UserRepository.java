package com.iiht.dating.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.dating.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional
	@Modifying
	@Query("delete FROM User us WHERE us.userId=?1")
	public Integer deleteByUserId(Long userId);
	
	
	@Query("SELECT u FROM User u WHERE u.loginName=?1 AND u.password=?2")
    public User validateUser(String loginName, String password);	
}