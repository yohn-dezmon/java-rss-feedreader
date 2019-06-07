package mytests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.boot.test.context.SpringBootTest;

import com.jdes.rssfeed.model.Article;


import com.jdes.rssfeed.dao.ArticleRepository;
import com.jdes.rssfeed.dao.SourceRepository;

import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
public class SpringBootTomcatConnectionPoolIntegrationTest {

	@Autowired
    private ArticleRepository articleRepo;
     
    @Test
    public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
    	String expected = "org.apache.tomcat.jdbc.pool.DataSource";
        assertEquals(articleRepo.getClass().getName(), expected);
        
    }
}
