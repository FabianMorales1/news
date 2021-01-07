/*
 * Copyright (c) 2020 Fabian Morales Araya, fabian.morales@alumnos.ucn.cl
 * Copyright (c) 2020 Felipe Herrera Encina, felipe.herrera01@alumnos.ucn.cl
 * Copyright (c) 2020 Diego Duarte Diaz, diego.duarte@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fmorales.newsdsm.services;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;

/**
 * Testing of ContractImpl
 * @author Fabian Morales, Felipe Herrera, Diego Duarte.
 */
public class TestContractsImpl {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestContractsImpl.class);

    @Test
    public void testRetrieveNews(){

        /**
         * The Test of Retrieve news.
         */
        log.debug("Testing...");

        //the implementation
        Contracts contracts = new ContractsImpl();

        //Call the method
        List<News> news = contracts.retrieveNews(5);
        Assertions.assertNotNull(news,"List was null :(");
        Assertions.assertTrue(news.size() != 0,"empty list ?");
        Assertions.assertTrue(news.size() == 5,"list size != 5");

        //debug to log
        for (News n : news){
            log.debug("News: {}", n);
        }
        log.debug("Done.");

        // size = 0
        Assertions.assertEquals(0,contracts.retrieveNews(0).size(), "List != 0");
        // size = 3
        Assertions.assertEquals(3,contracts.retrieveNews(3).size(), "List != 3");
        // size = 10
        Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10, "List size != 10 :(");
        log.debug("Done.");
    }

    /**
     * Show the faker
     */
    @Test
    public void testFaker(){

        //Build the Faker
        Faker faker = Faker.instance();
        for(int i = 0; i<5; i++){
            log.debug("Name: {}",faker.name().fullName());
            // FIXME: Remover
            System.out.println("Name: " + faker.name().fullName());
            System.out.println("Cat: " + faker.cat().name());
        }
    }
}
