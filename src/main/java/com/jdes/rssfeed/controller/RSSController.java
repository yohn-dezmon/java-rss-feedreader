package com.jdes.rssfeed.controller;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.jdes.rssfeed.dao.ArticleRepository;
import com.jdes.rssfeed.dao.SourceRepository;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;



import com.jdes.rssfeed.service.SourceServiceImpl;
import com.jdes.rssfeed.service.HibernateSearchService;
import com.jdes.rssfeed.service.SourceService;

import com.jdes.rssfeed.model.Source;
import com.jdes.rssfeed.model.Article;
import com.jdes.rssfeed.controller.Feed;






@Controller
public class RSSController {
	
	@Autowired // get the bean called articleRepository 
	private ArticleRepository articleRepository;

	@Autowired 
	private SourceRepository sourceRepository;
	
	@Autowired
	private HibernateSearchService searchservice;

	@Autowired
	private SourceService sourceservice;
	
	@Autowired 
	private SourceRepository sourcerepository;

@GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
@GetMapping("/sample")
  public String showForm() {
    // the return "sample" refers to the resource to be called
    // What is displayed on the browser is the view defined within the .jsp
    // or the html file
    // the .jsp file should be stored in WEB-INF/view
    // the html file should be stored within resources/templates :D
    return "sample";
  }



@GetMapping(path="/sources")
public @ResponseBody Iterable<Source> displaySources() {
	
	// sourceRepository.findAll(); returns a JSON of the sources... how do I extract just the title?
	return sourceRepository.findAll();

}


@GetMapping(path="/sourcestitles")
public @ResponseBody List<String> doWhatISaid() {
	
	return sourceservice.getSources();
}

//get rid of @ResponseBody b/c this returns a string, as opposed to using a template!
@RequestMapping(value = "/thymesources", method = RequestMethod.GET)
public String thymeSource(Model model) {
	
	
	// this ideally should just produce 1 list with all of the titles of the RSS feeds...
	List<String> titles = sourcerepository.findAllTitles();
	
	
	// this allows thymeleaf to access "sources"
	model.addAttribute("sources", titles);
	model.addAttribute("input", new UsrInput());
	
	
	//this should use the sources.html file... 
	return "sources";
}


//
@RequestMapping(value = "/thymesources", method = RequestMethod.POST)
public String insertToSource(@ModelAttribute UsrInput input) {
	// ok how do I get the html form to 
	
//	String url = "http://rss.cnn.com/rss/money_topstories.rss";
	
	String url = input.getUsrInput();
	
	RSSFeedParser newSource = new RSSFeedParser(url);
	// This will return a feed [ITEM, TITLE, DESCRIPTION, LINK 
	// GUID, LANGUAGE, AUTHOR, PUB_DATE, COPYRIGHT] 
	Feed parsed = newSource.readFeed();
	// I NEED -> title, subtitle, link, feed, 

	
	String title = parsed.title;
	String subTitle = parsed.description;
	String link = parsed.link;
	 
	
	// this should insert this source into the DB..
	Source s = new Source();
	s.setFeed(url);
	s.setLink(link);
	s.setTitle(title);
	s.setSubtitle(subTitle);
	sourceRepository.save(s);
	
	return "redirect:thymesources";

}

public void updatingLoop() {
	
	Iterable<Source> sources = sourceRepository.findAll();
	
	
	boolean whileLooper = true;
	while (whileLooper == true) {
		// wait what is the type for s?????
		
		for (Source s: sources) {
			try {
				updateArticles(s);
				
			} catch (Exception e) {
				
			}
		}
		// find equivalent of sleep in java...
	}
}

public void updateArticles(Source source) {

		
		RSSFeedParser newSource = new RSSFeedParser(source.getFeed());
		Feed parsed = newSource.readFeed();
		
		// entires is a list of "messages"... each of which 
		// consists of title, link, author, guid, pubDate
		// it just returns this in a string though... so IDK how to access it 
		// Maybe I can do like FeedMessage.getTitle() = Article.setTitle();
		List<FeedMessage> entries = parsed.getMessages();
		
		//Article has 
//		for (FeedMessage q: entries) {
//			Article a = new Article();
//			a.setBody(q.description);
//			Date pubDate =(Date) q.getPubDate();
//			
//			a.setDatePublished(q.pubDate);
//			a.setGuid(q.guid);
//			//a.setSource
//			
//		}
		
		
		
		// ok I need to define .getArticles(); first before doing this...
		
	

	
	
	// src is Soure.query ... so sourceRepository.findall(); 
//	parsed = feed.parse(src.feed)
//		    feed_articles = feed.get_articles(parsed)
//		    articles.Article.insert_from_feed(src.id, feed_articles)
//		    print("Updated" + src.feed)
}

public void getArticles(Feed parsed) {
	
	List<String> articles = new ArrayList<String>();
	
	List<FeedMessage> entries = parsed.getMessages();
	
	
	try {
		for (int i=0; i < entries.size(); i++) {
			
		}
			
		
	} catch (Exception e) {
		
	}
	
	
	
	
}




	
}
