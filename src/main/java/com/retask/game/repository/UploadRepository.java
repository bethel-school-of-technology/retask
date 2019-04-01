package com.retask.game.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retask.game.model.Upload;

@Repository
@Transactional
public interface UploadRepository extends JpaRepository<Upload, Long> {
	
	//Always use the class name instead of the table name
		@Query("SELECT a FROM Upload a WHERE a.uploadable_type = :type and a.uploadable_id = :source_id")
		List<Upload> findBySourceTypeAndId(@Param("type") String type,
				@Param("source_id") Long source_id);

}