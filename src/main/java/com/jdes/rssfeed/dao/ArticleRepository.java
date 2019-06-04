package com.jdes.rssfeed.dao;

//import java.util.Collection;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.jdes.rssfeed.model.Article;


public interface ArticleRepository extends CrudRepository<Article, Integer> {
	
	// SELECT * Throws an error, as if there is no column specified the SELECT * is not necessary 
	@Query(value = "SELECT * FROM article WHERE article.unread = 0 AND article.source_id = :srcId ORDER BY article.date_added DESC", nativeQuery = true)
	public Iterable<Article> findUnreadArticles(@Param("srcId") int srcId);
	
//	@Query(value = "SELECT article_id FROM article WHERE article.article_id = :articleId", nativeQuery = true)
//	public Iterable<Article> getArticleFromId(@Param("articleId") int articleId);
	
	// should I use 'Collection', 'Stream', 'List', or another type to store my results?
	
	public Article findById(int id);
	
	
	
	
}