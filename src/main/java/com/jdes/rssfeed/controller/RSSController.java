package com.jdes.rssfeed.controller;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;



import com.jdes.rssfeed.dao.ArticleRepository;
import com.jdes.rssfeed.dao.SourceRepository;


import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import com.jdes.rssfeed.service.HibernateSearchService;
import com.jdes.rssfeed.service.SourceService;
import com.jdes.rssfeed.service.UpdateServiceImpl;

import java.lang.InterruptedException;


import com.jdes.rssfeed.model.Source;
import com.jdes.rssfeed.model.Article;
import com.jdes.rssfeed.controller.Feed;
// DateTime conversion dependencies

import java.time.LocalDateTime;






@Controller
public class RSSController {
	
	// not sure what this does exactly, https://kodejava.org/how-do-i-find-entity-by-their-id-in-jpa/
	public static final String PERSISTENCE_UNIT_NAME = "article";

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
	
	@Autowired
	private UpdateServiceImpl updateServiceImpl;
	
// this is the HomePage, it displays four News Sources and their Articles sorted by Date Added to the DB
@GetMapping("/")
    public String mainPage(Model model) {
	
	
	// This retrieves all of the Articles for a given source, the srcId can be changed as desired
	int srcId = 1;
	Iterable<Article> firstArticles = articleRepository.findUnreadArticles(srcId);

	// This provides the name of the News Source
	String firstSourceTitle = sourceRepository.sourceTitle(srcId);

	model.addAttribute("firstArticles", firstArticles);
	model.addAttribute("firstSourceTitle", firstSourceTitle);

	int srcId2 = 2;
	Iterable<Article> secondArticles = articleRepository.findUnreadArticles(srcId2);
	String secondSourceTitle = sourceRepository.sourceTitle(srcId2);

	model.addAttribute("secondArticles", secondArticles);
	model.addAttribute("secondSourceTitle", secondSourceTitle);

	int srcId3 = 3;
	Iterable<Article> thirdArticles = articleRepository.findUnreadArticles(srcId3);
	String thirdSourceTitle = sourceRepository.sourceTitle(srcId3);

	model.addAttribute("thirdArticles", thirdArticles);
	model.addAttribute("thirdSourceTitle", thirdSourceTitle);

	int srcId4 = 9;
	Iterable<Article> fourthArticles = articleRepository.findUnreadArticles(srcId4);
	String fourthSourceTitle = sourceRepository.sourceTitle(srcId4);

	model.addAttribute("fourthArticles", fourthArticles);
	model.addAttribute("fourthSourceTitle", fourthSourceTitle);

	return "index";
}





// This is the page to add News Sources using an RSS feed
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


// This Post Method updates first the source table in the DB and then the 
// article table in the DB, then redirects to the HomePage
@RequestMapping(value = "/thymesources", method = RequestMethod.POST)
public String insertToSource(@ModelAttribute UsrInput input) {

	// this url was used as a test initially...
//	String url = "http://rss.cnn.com/rss/money_topstories.rss";

	String url = input.getUsrInput();

	RSSFeedParser newSource = new RSSFeedParser(url);
	// This will return a feed [ITEM, TITLE, DESCRIPTION, LINK
	// GUID, LANGUAGE, AUTHOR, PUB_DATE, COPYRIGHT]
	String title = "";
	String subTitle = "";
	String link = "";

	
	try {
	Feed parsed = newSource.readFeed();
	title = parsed.title;
	subTitle = parsed.description;
	link = parsed.link;
	} catch (Exception e) {
		System.out.println("error parsing RSS");
	}
	// I NEED -> title, subtitle, link, feed,

	LocalDateTime now = LocalDateTime.now();

	// this should insert this source into the DB..
	Source s = new Source();
	try {
	s.setFeed(url);
	s.setLink(link);
	s.setTitle(title);
	s.setSubtitle(subTitle);
	s.setDateAdded(now); }
	catch (Exception e) {
		System.out.println("error setting Source attributes");
	}
	try {
	sourceRepository.save(s); }
	catch (Exception e) {
		System.out.println("error inserting into DB [hibernate]");
	}
	
	
	
	try {
	updateServiceImpl.updatingLoop(); }
	catch (InterruptedException e) {
		System.out.println("InterruptedException when updating articles");
	}
	
	

	return "redirect:/";

}

@GetMapping(path="/sources")
public @ResponseBody Iterable<Source> displaySources() {

	// sourceRepository.findAll(); returns an Iterable of the sources...
	return sourceRepository.findAll();

}


@GetMapping(path="/sourcestitles")
public @ResponseBody List<String> getSourceTitles() {

	return sourceservice.getSources();
}





}
