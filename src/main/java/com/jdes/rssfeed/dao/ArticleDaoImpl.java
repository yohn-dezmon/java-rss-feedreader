package com.jdes.rssfeed.dao;

import java.util.Optional;

import javax.persistence.EntityManager;

//import org.springframework.data.repository.query.Param;
import javax.persistence.EntityNotFoundException;
import com.jdes.rssfeed.model.Article;

// I set up this implementation to perform a custom query

public class ArticleDaoImpl implements ArticleRepository {
	
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

	@Override
	public <S extends Article> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Article> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Article> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Article> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Article> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Article entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Article> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Article> findUnreadArticles(int srcId) {
		// TODO Auto-generated method stub
		return null;
	}

}
