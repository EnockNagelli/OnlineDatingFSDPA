package com.iiht.dating.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iiht.dating.model.DatingSpecs;

@Repository
public interface DatingRepository extends JpaRepository<DatingSpecs, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM DatingSpecs ds WHERE ds.userId=?1")
	public Integer deleteDatingSpecsById(Long userId);
}