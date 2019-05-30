package com.jdes.rssfeed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;


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

@Autowired
private ArticleRepository articleRepository;

// get articles
// get a single article
// delete an article 

@GetMapping("/articles")
public List<Article> getAllArticles() {
	return articleRepository.findAll();
}
	
	
}
