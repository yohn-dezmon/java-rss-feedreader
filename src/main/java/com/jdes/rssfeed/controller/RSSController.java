package com.jdes.rssfeed.controller;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import com.jdes.rssfeed.dao.ArticleRepository;
import com.jdes.rssfeed.dao.SourceRepository;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.jdes.rssfeed.dao.ArticleDaoImpl;

import com.jdes.rssfeed.service.SourceServiceImpl;
import com.jdes.rssfeed.service.HibernateSearchService;
import com.jdes.rssfeed.service.SourceService;
import com.jdes.rssfeed.service.UpdateServiceImpl;

import java.lang.InterruptedException;


import com.jdes.rssfeed.model.Source;
import com.jdes.rssfeed.model.Article;
import com.jdes.rssfeed.controller.Feed;
// DateTime conversion dependencies
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import org.springframework.web.servlet.view.RedirectView;




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

@GetMapping("/")
    public String mainPage(Model model) {

	int srcId = 1;
	Iterable<Article> firstArticles = articleRepository.findUnreadArticles(srcId);

	// I want the name of the Article... I might just do this manually...
	String firstSourceTitle = sourceRepository.sourceTitle(srcId);



	model.addAttribute("firstArticles", firstArticles);
	model.addAttribute("firstSourceTitle", firstSourceTitle);

	int srcId2 = 2;
	Iterable<Article> secondArticles = articleRepository.findUnreadArticles(srcId2);

	// I want the name of the Article... I might just do this manually...
	String secondSourceTitle = sourceRepository.sourceTitle(srcId2);



	model.addAttribute("secondArticles", secondArticles);
	model.addAttribute("secondSourceTitle", secondSourceTitle);

	int srcId3 = 3;
	Iterable<Article> thirdArticles = articleRepository.findUnreadArticles(srcId3);

	// I want the name of the Article... I might just do this manually...
	String thirdSourceTitle = sourceRepository.sourceTitle(srcId3);



	model.addAttribute("thirdArticles", thirdArticles);
	model.addAttribute("thirdSourceTitle", thirdSourceTitle);

	int srcId4 = 9;
	Iterable<Article> fourthArticles = articleRepository.findUnreadArticles(srcId4);

	// I want the name of the Article... I might just do this manually...
	String fourthSourceTitle = sourceRepository.sourceTitle(srcId4);



	model.addAttribute("fourthArticles", fourthArticles);
	model.addAttribute("fourthSourceTitle", fourthSourceTitle);

	return "index";
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
	
	

	return "redirect:thymesources";

}







}
