/*
 * Copyright (c) 2020 Fabian Morales Araya, fabian.morales@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fmorales.newsdsm.model;


import org.threeten.bp.ZonedDateTime;

/**
 * The domain model: News
 * @author Fabian Morales Araya
 */
public class News {
    /**
     * Unique id
     */
    private long id;
    /**
     * The Title
     * Restrictions: not null, size > 2
     */
    private String title;
    /**
     * The source
     */
    private String source;
    /**
     * The Author
     */
    private String author;
    /**
     * The url
     */
    private String url;
    /**
     * The url of the image
     */
    private String urlImage;
    /**
     * The description
     */
    private String description;
    /**
     * The content
     */
    private String content;
    /**
     * The date of publish
     */
    private ZonedDateTime publishedAt;

    /**
     * The constructor
     * @param id
     * @param title
     * @param source
     * @param author
     * @param url
     * @param urlImage
     * @param description
     * @param content
     * @param publishedAt
     */
    public News(long id, String title, String source, String author, String url, String urlImage, String description, String content, ZonedDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.author = author;
        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}