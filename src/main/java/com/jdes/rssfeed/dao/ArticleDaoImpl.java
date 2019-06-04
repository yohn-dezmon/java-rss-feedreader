package com.jdes.rssfeed.dao;

import javax.persistence.EntityManager;

//import org.springframework.data.repository.query.Param;
import javax.persistence.EntityNotFoundException;
import com.jdes.rssfeed.model.Article;



public abstract class ArticleDaoImpl implements ArticleRepository {
	
	private EntityManager manager;
	
//	public Iterable<Article> findUnreadArticles(@Param("srcId") int srcId);
	
	public ArticleDaoImpl(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Article findById(int id) {
		Article article = manager.find(Article.class, id);
		if (article == null) {
			throw new EntityNotFoundException("No article for this ID "+id);
		}
		
	
		return article;
	}

}
