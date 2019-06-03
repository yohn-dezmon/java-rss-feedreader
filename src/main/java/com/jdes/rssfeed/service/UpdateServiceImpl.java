package com.jdes.rssfeed.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import com.jdes.rssfeed.controller.Feed;
import com.jdes.rssfeed.controller.FeedMessage;
import com.jdes.rssfeed.controller.RSSFeedParser;
import com.jdes.rssfeed.dao.ArticleRepository;
import com.jdes.rssfeed.dao.SourceRepository;
import com.jdes.rssfeed.model.Article;
import com.jdes.rssfeed.model.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;

//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.web.client.RestTemplate;


@Service
public class UpdateServiceImpl {
	
	// I don't know why but this doesn't work...
//	private final RestTemplate restTemplate;
	
	@Autowired 
	private SourceRepository sourceRepository;
	
	@Autowired // get the bean called articleRepository 
	private ArticleRepository articleRepository;
	
	
	@Async("asyncExecutor")
	public void updatingLoop() throws InterruptedException {
		
		Iterable<Source> sources = sourceRepository.findAll();
		
		System.out.println("Articles being updated...");
		
		boolean whileLooper = true;
		while (whileLooper == true) {
			
			
			for (Source s: sources) {
					
					RSSFeedParser newSource = new RSSFeedParser(s.getFeed());
					Feed parsed = newSource.readFeed();
					
					// entries is a list of "messages"... each of which 
					// consists of title, link, author, guid, pubDate
					// it just returns this in a string though... so IDK how to access it 
					// Maybe I can do like FeedMessage.getTitle() = Article.setTitle();
					List<FeedMessage> entries = parsed.getMessages();
					
					 
					for (FeedMessage q: entries) {
						Article a = new Article();
						String title = q.getTitle();
						String body = q.getDescription();
						String link = q.getLink();
						String guid = q.getGuid();
						
						a.setTitle(title);
						a.setBody(body);
						a.setLink(link);
						a.setGuid(guid);
						
						// how can I access the source from which the article comes...
						a.setSourceId(s);
						
						// Converting date to LocalDateTime
						String pubDate = q.getPubDate();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
						LocalDateTime dateTime = LocalDateTime.parse(pubDate, formatter);
						a.setDatePublished(dateTime);
						
						//Date added time
						LocalDateTime now = LocalDateTime.now();
						a.setDateAdded(now);
						// OK I NEED THE ARTICLE REPOSITORY YA DUM DUM...
//						sourceRepository.save(s);
						try {
						articleRepository.save(a); 
						
						}catch (Exception e) {
							System.out.println("DB Article Insert error");
						}
					} // for loop 
					
			} // other for loop 
			Thread.sleep(1000L);	
		} // while loop 
		
	} // method 
	
} // class


	
		
						
						
	
			
					
			
			
		
		
		
	
	
	

			
			
			
	
//	@Async
//	public void getArticles(Feed parsed) {
//		
//		List<String> articles = new ArrayList<String>();
//		
//		List<FeedMessage> entries = parsed.getMessages();
//		
//		
//		try {
//			for (int i=0; i < entries.size(); i++) {
//				
//			}
//				
//			
//		} catch (Exception e) {
//			
//		}
//		
//		
//		
//		
//	}


