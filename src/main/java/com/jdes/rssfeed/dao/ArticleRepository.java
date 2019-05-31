package com.jdes.rssfeed.dao;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.jdes.rssfeed.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
	
}