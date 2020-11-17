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

import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;

import static org.threeten.bp.ZoneOffset.UTC;

public class ContractsImpl implements Contracts {
    /**
     * Get the list of news
     *
     * @param size size of the list
     * @return the list of news
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        final List<News> news = new ArrayList<>();

        // TODO: Add the faker news to the list

        Faker faker = Faker.instance();
        for(int i = 0; i<5; i++){


            News testNews = new News(Long.MIN_VALUE + i,faker.name().title(),faker.name().username(),faker.name().fullName(),faker.internet().url(),faker.internet().url(),faker.lorem().toString(),faker.lorem().toString(),ZonedDateTime.now(UTC));

            // log.debug("Name: {}",faker.name().fullName());
            // FIXME: Remover
            // System.out.println("Name: " + faker.name().fullName());
            news.add(testNews);

        }

       /*
        for(int i = 0; i<5; i++){
            System.out.println(news.get(i).getAuthor() +" " + news.get(i).getPublishedAt());
        }
        */




        return news;
    }



}
