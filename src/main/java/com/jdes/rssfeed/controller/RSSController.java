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
import java.util.List;


import com.jdes.rssfeed.service.SourceServiceImpl;
import com.jdes.rssfeed.service.HibernateSearchService;
import com.jdes.rssfeed.service.SourceService;




import com.jdes.rssfeed.model.Source;
import com.jdes.rssfeed.model.Article;



@Controller
public class RSSController {

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

@Autowired // get the bean called articleRepository 
private ArticleRepository articleRepository;

// get articles
// get a single article
// delete an article 

//@GetMapping("/articles")
//public List<Article> getAllArticles() {
//	return articleRepository.findAll();
//		
//	}

@Autowired 
SourceRepository sourceRepository;

@GetMapping(path="/sources")
public @ResponseBody Iterable<Source> displaySources() {
	// USE JINJAVA HERE! :D 
	// first you need to add atleast one source, then query it... 
	// then we can test if jinjava works..
	
	// sourceRepository.findAll(); returns a JSON of the sources... how do I extract just the title?
	
	
	return sourceRepository.findAll();

	
//	// set up a for loop to access the Source entities by source.get...?
//	Source s = new Source();
//	
//	for source in s 
//	
	
}

@Autowired
private HibernateSearchService searchservice;

@Autowired
private SourceService sourceservice;


@GetMapping(path="/sourcestitles")
public @ResponseBody List<String> doWhatISaid() {
	
//	try {
//		
//		String egg = sourceservice.getSources();
//		
//	}
//	catch (Exception Ex) {
//		System.out.println("errrr");
//	}
   
	
	return sourceservice.getSources();
}

@Autowired 
SourceRepository sourcerepository;

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





	
}