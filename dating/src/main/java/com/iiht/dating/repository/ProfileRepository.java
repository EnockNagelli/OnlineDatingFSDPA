package com.iiht.dating.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.dating.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Profile pf WHERE pf.userId=?1")
	public Integer deleteByProfileId(Long userId);
}