package cl.ucn.disc.dsm.fmorales.newsdsm.services;

import com.kwabenaberko.newsapilib.models.Article;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.List;

public class TestNewsApiServices {

    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(TestNewsApiServices.class);

    /**
     * The Testing.
     */
    @Test
    public void wrongApi() throws IOException { log.debug("Testing ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> { NewsApiService newsApiService = new NewsApiService(null);
        });

        log.debug("Wrong key .."); Assertions.assertThrows(RuntimeException.class, () -> {
            NewsApiService newsApiService = new NewsApiService("This is my wrong key"); List<Article> articles = newsApiService.getTopHeadlines("general", 10); log.debug("Articles: {}.", articles);
        });

        log.debug("Good key ..");
        {
// TODO: Add the real apikey
            NewsApiService newsApiService = new NewsApiService("<USE REAL APIKEY>"); List<Article> articles = newsApiService.getTopHeadlines("general", 10); log.debug("Articles: {}.", articles);
            log.debug("Articles size: {}.", articles.size());
        }

        {
// TODO: Add more testing to the backend
        }

        log.debug(".. Done.");

    }

}
