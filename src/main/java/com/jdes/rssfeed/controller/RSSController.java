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

import java.util.ArrayList;
import java.util.List;
import java.util.Date;



import com.jdes.rssfeed.service.SourceServiceImpl;
import com.jdes.rssfeed.service.HibernateSearchService;
import com.jdes.rssfeed.service.SourceService;

import com.jdes.rssfeed.model.Source;
import com.jdes.rssfeed.model.Article;
import com.jdes.rssfeed.controller.Feed;
// DateTime conversion dependencies
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;







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

	int srcId4 = 4;
	Iterable<Article> fourthArticles = articleRepository.findUnreadArticles(srcId4);

	// I want the name of the Article... I might just do this manually...
	String fourthSourceTitle = sourceRepository.sourceTitle(srcId4);



	model.addAttribute("fourthArticles", fourthArticles);
	model.addAttribute("fourthSourceTitle", fourthSourceTitle);



//	List<String> myList = new ArrayList<String>();
//
//
//	for (Article a: firstArticles) {
//		String title = a.getTitle();
//		myList.add(title);
//		System.out.println(title);
//
//	}
//
//	model.addAttribute("myList", myList);




//	query = Article.query
//		    def query_over(query, src_id):
//		        query = query.filter(Article.unread == True, Article.source_id == src_id)
//		        query = query.order_by(Article.date_added.desc())
//		        return query.all()
//
//		# this should be automated better, to query for available source ids, and for all source ids it should create an articles box...
//		    first_articles = query_over(query, 1)
//		    second_articles = query_over(query, 2)
//		    third_articles = query_over(query, 11)
//		    fourth_articles = query_over(query, 10)

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
	Feed parsed = newSource.readFeed();
	// I NEED -> title, subtitle, link, feed,


	String title = parsed.title;
	String subTitle = parsed.description;
	String link = parsed.link;

	LocalDateTime now = LocalDateTime.now();

	// this should insert this source into the DB..
	Source s = new Source();
	s.setFeed(url);
	s.setLink(link);
	s.setTitle(title);
	s.setSubtitle(subTitle);
	s.setDateAdded(now);
	sourceRepository.save(s);

	return "redirect:thymesources";

}

//@RequestMapping(value = "/updateArticles", method= RequestMethod.GET)
//public String updatingLoop(Model model) throws InterruptedException {
//
//	Iterable<Source> sources = sourceRepository.findAll();
//
//	System.out.println("Articles being updated...");
//
//
//
//
//		for (Source s: sources) {
//
//				RSSFeedParser newSource = new RSSFeedParser(s.getFeed());
//				Feed parsed = newSource.readFeed();
//
//				// entries is a list of "messages"... each of which
//				// consists of title, link, author, guid, pubDate
//				// it just returns this in a string though... so IDK how to access it
//				// Maybe I can do like FeedMessage.getTitle() = Article.setTitle();
//				List<FeedMessage> entries = parsed.getMessages();
//
//
//				for (FeedMessage q: entries) {
//					Article a = new Article();
//					String title = q.getTitle();
//					String body = q.getDescription();
//					String link = q.getLink();
//					String guid = q.getGuid();
//
//					a.setTitle(title);
//					a.setBody(body);
//					a.setLink(link);
//					a.setGuid(guid);
//
//					// how can I access the source from which the article comes...
//					a.setSourceId(s);
//
//					// Converting date to LocalDateTime
//					String pubDate = q.getPubDate();
//					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
//					LocalDateTime dateTime = LocalDateTime.parse(pubDate, formatter);
//					a.setDatePublished(dateTime);
//
//					//Date added time
//					LocalDateTime now = LocalDateTime.now();
//					a.setDateAdded(now);
//					// OK I NEED THE ARTICLE REPOSITORY YA DUM DUM...
////					sourceRepository.save(s);
//					try {
//					articleRepository.save(a);
//
//					}catch (Exception e) {
//						System.out.println("DB Article Insert error");
//					}
//				} // for loop
//
//		} // other for loop
//
//		Iterable<Article> articles = articleRepository.findAll();
//
//		model.addAttribute("articles", articles);
//
//
//	return "articles";
//
//} // method






}
