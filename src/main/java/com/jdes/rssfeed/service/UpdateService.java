package com.jdes.rssfeed.service;

import com.jdes.rssfeed.controller.Feed;
import com.jdes.rssfeed.model.Source;

import java.util.concurrent.Future;


public interface UpdateService {
	
	public void updatingLoop(); 

	public void updateArticles(Source source);


	public void getArticles(Feed parsed);

}
