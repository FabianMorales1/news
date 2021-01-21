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

package cl.ucn.disc.dsm.fmorales.newsdsm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.fmorales.newsdsm.utils.Validation;

/**
 * The domain model: News
 *
 * @author Fabian Morales Araya
 */
@Entity
public class News {

    /**
     * Unique id
     */
    @PrimaryKey()
    long id;

    /**
     * The Title
     * Restrictions: not null, size > 2
     */
    @ColumnInfo(name = "title")
    final String title;

    /**
     * The source
     */
    @ColumnInfo(name = "source")
    final String source;

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

    /**
     * The constructor
     *
     * @param title
     * @param source
     * @param author
     * @param url
     * @param urlImage
     * @param description
     * @param content
     * @param publishedAt
     */
    public News(String title, String source, String author, String url, String urlImage,
                String description, String content, ZonedDateTime publishedAt) {

        // Validacion de title
        Validation.minSize(title, 2, "title");

        // Validacion de source
        Validation.minSize(source, 2, "title");

        // Validacion de author
        Validation.minSize(author, 2, "title");

        // Validacion de content
        Validation.notNull(content, "title");

        // Validacion de publishedAt
        Validation.notNull(publishedAt, "title");

        this.id = LongHashFunction.xx().hashChars(title + "|" + source + "|" + author);
        this.title = title;
        this.source = source;
        this.author = author;
        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
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



