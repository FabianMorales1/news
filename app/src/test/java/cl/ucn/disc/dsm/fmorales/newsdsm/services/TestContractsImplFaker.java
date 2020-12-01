/*
 * Copyright (c) 2020 Fabian Morales Araya, fabian.morales@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fmorales.newsdsm.services;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;

import java.util.List;

import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;

import static org.threeten.bp.ZoneOffset.UTC;

public class TestContractsImplFaker {

    private static final Logger log = LoggerFactory.getLogger(TestContractsImpl.class);


    @Test
    public void testRetrieveNews(){

        log.debug("Testing...");
        Integer size = 2;

        //La implementacion
        Contracts contracts = new ContractsImplFaker();


        //LLamada al metodo
        List<News> news = contracts.retrieveNews(size);

        for(int i = 0; i<size; i++){
            System.out.println(news.get(i).getAuthor() +" " + news.get(i).getPublishedAt());
        }


        Assertions.assertNotNull(news,"List was null :(");
        Assertions.assertTrue(news.size() != 0,"empty list ?");
        Assertions.assertTrue(news.size() == size,"list size != 5");

        /*
        // size = 0
        Assertions.assertEquals(0,contracts.retrieveNews(0).size(),"List != 0");

        // size = 3
        Assertions.assertEquals(3,contracts.retrieveNews(3).size(),"List != 3");

        // size = 10
        Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10,"List != 10");
        */

        log.debug("Done.");
    }

    @Test
    public void testSaveNews(){
        log.debug("Testing...");

        //Declaracion del faker
        Faker faker = Faker.instance();


        //the implementation
        Contracts contracts = new ContractsImplFaker();

        //Obtener lista inicial
        List<News> newsBefore = contracts.retrieveNews(5);




        //Call the method

        // TEST NULL VALUE
        //News newNews = null;

        //TEST NEW VALUE
        //Se crea una nueva noticia
        News newNews = new News(faker.name().title(),
                faker.name().username(),faker.name().fullName(),faker.internet().url(),
                faker.internet().url(),faker.lorem().toString(),faker.lorem().toString(),
                ZonedDateTime.now(UTC));

        //Se guarda la nueva noticia
        List<News> news = contracts.save(newNews);


        log.debug("Done.");
    }


}
