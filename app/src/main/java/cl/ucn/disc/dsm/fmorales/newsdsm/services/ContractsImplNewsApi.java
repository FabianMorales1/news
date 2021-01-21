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

import com.kwabenaberko.newsapilib.models.Article;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.function.Function;

import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;
import cl.ucn.disc.dsm.fmorales.newsdsm.model.NewsL;
import cl.ucn.disc.dsm.fmorales.newsdsm.utils.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

/**
 * The ContractsImplNewsApi Class
 *
 * @author Fabian Morales, Felipe Herrera, Diego Duarte
 */
public final class ContractsImplNewsApi implements Contracts {

    /**
     * The logger.
     **/
    private static final Logger log = LoggerFactory.getLogger(ContractsImplNewsApi.class);

    /**
     * The connection to NewsApi.
     */
    private final NewsApiService newsApiService;

    /**
     * The Constructor.
     *
     * @param theApiKey to use.
     */
    public ContractsImplNewsApi(final String theApiKey) {
        Validation.minSize(theApiKey, 10, "ApiKey !!");
        this.newsApiService = new NewsApiService(theApiKey);
    }

    /**
     * The Assembler/Transformer pattern!
     *
     * @param article used to source
     * @return the News.
     */
    private static News toNews(final Article article) {
        Validation.notNull(article, "Article null !?!");

        // Warning message?
        boolean needFix = false;

        // Fix the author null
        if (article.getAuthor() == null || article.getAuthor().length() == 0) {
            article.setAuthor("No author*");
            needFix = true;
        }

        //	Fix more restrictions
        if (article.getDescription() == null || article.getDescription().length() == 0) {
            article.setDescription("No description*");
            needFix = true;
        }

        //	.. yes, warning message.
        if (needFix) {
            // Debug of Article
            log.warn("Article with invalid restrictions: {}.", ToStringBuilder.reflectionToString(
                    article, MULTI_LINE_STYLE
            ));
        }

        // The Date
        ZonedDateTime publishedAt = ZonedDateTime
                .parse(article.getPublishedAt())
                .withZoneSameInstant(ZoneId.of("-3"));

        // The News
        return new News(
                article.getTitle(),
                article.getSource().getName(),
                article.getAuthor(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getDescription(),
                article.getDescription(), // FIXME: Where is the content?
                publishedAt
        );
    }

    /**
     * Get the list of News from news api and laravel server.
     *
     * @param size of the list.
     * @return the List of News.
     */
    @Override
    public List<News> retrieveNews(final Integer size) {

        try {
            List<Article> articles = newsApiService.getTopHeadlines(
                    "technology", size
            );

            // The List of Articles to List of News
            List<News> news = new ArrayList<>();
            for (Article article : articles) {
                // log.debug("Article: {}", ToStringBuilder.reflectionToString(article, ToStringStyle.MULTI_LINE_STYLE));
                news.add(toNews(article));
            }
            // Get the news from the api with retrofit the url is the ip of the host
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.83:8101/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Laravelapi lapi = retrofit.create(Laravelapi.class);
            Call<List<NewsL>> call = lapi.getnewl();
            call.enqueue(new Callback<List<NewsL>>() {

                /**
                 * save the response code given from the server
                 * if the code is 200 the news are saved to the news list
                 * @param call is the call from de app to the server laravel.
                 * @param response the response code and the news of the server laravel.
                 */
                @Override
                public void onResponse(Call<List<NewsL>> call, Response<List<NewsL>> response) {
                    if (!response.isSuccessful()) {
                        //si hay un error solo se sale
                        return;
                    }
                    List<NewsL> listaL = response.body();
                    for (NewsL newsl : listaL) {
                        News A = new News(newsl.getTitle(), newsl.getAuthor(), newsl.getAuthor(), newsl.getUrl(), newsl.getUrlImage(), newsl.getDescription(), newsl.getContent(), newsl.getPublishedAt());
                        news.add(A);
                    }
                }

                /**
                 *   all error that ar not responses form the server are catched in this function
                 * @param call  is the call from de app to the server laravel.
                 * @param t error catch
                 */
                @Override
                public void onFailure(Call<List<NewsL>> call, Throwable t) {
                    //si hay un error solo se sale
                    return;
                }

            });
            return news.stream().filter(distinctById(News::getId))
                    .sorted((k1, k2) -> k2.getPublishedAt()
                            .compareTo(k1.getPublishedAt()))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            log.error("Error", ex);
            return null;
        }
    }

    /**
     * Filter the stream
     *
     * @param idExtractor
     * @param <T>
     * @return true if the news already exists.
     */
    private static <T> Predicate<T> distinctById(Function<? super T, ?> idExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(idExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Save one News into the System.
     *
     * @param news to save.
     */
    @Override
    public List<News> save(final News news) {
        throw new NotImplementedException("Can't save in NewsAPI");
    }
}
