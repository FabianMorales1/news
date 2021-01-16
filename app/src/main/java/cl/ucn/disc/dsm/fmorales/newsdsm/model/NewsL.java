/*
 * Copyright (c) 2021 Fabian Morales Araya, fabian.morales@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fmorales.newsdsm.model;

import androidx.room.ColumnInfo;

import org.threeten.bp.ZonedDateTime;

public class NewsL {

    @ColumnInfo(name = "title")
    final String title;

    /**
     * The Author
     */
    @ColumnInfo(name = "author")
    final String author;

    /**
     * The url
     */
    @ColumnInfo(name = "url")
    final String url;

    /**
     * The url of the image
     */
    @ColumnInfo(name = "urlImage")
    final String urlImage;

    /**
     * The description
     */
    @ColumnInfo(name = "description")
    final String description;

    /**
     * The content
     */
    @ColumnInfo(name = "content")
    final String content;

    /**
     * The date of publish
     */
    @ColumnInfo(name = "publishedAt")
    final ZonedDateTime publishedAt;

    public NewsL(String title, String author, String url, String urlImage, String description, String content, ZonedDateTime publishedAt) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the urlImage
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the time tha publishedAt
     */
    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
