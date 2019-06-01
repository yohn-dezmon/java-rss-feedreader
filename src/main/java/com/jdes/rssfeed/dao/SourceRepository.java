package com.jdes.rssfeed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.jdes.rssfeed.model.Source;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

 
// What is the Integer after Source, ??
// WHy is this returning only the first row? https://stackoverflow.com/questions/39497931/jpa-hibernate-query-only-returning-one-row
public interface SourceRepository extends CrudRepository<Source, Integer> {
	
	@Query(value = "SELECT title FROM source", nativeQuery = true)
	List<String> findAllTitles();
	
	
	
}

