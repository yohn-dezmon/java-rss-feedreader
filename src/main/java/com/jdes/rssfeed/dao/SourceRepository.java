package com.jdes.rssfeed.dao;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.jdes.rssfeed.model.Source;

import org.springframework.data.jpa.repository.Query;
import java.util.List;





// What is the Integer after Source, ??
public interface SourceRepository extends CrudRepository<Source, Integer> {
	
	
}

