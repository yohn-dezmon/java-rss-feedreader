package com.jdes.rssfeed;

import com.jdes.rssfeed.Source;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


// What is the Integer after Source, ??
public interface SourceRepository extends CrudRepository<Source, Integer> {
	
}

