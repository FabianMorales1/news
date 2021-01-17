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

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface NewsDao {

    /**
     * Get news from the local storage from newer to older
     */
    @Query("SELECT * FROM news ORDER BY publishedAt DESC ")
    List<News> getAll();

    /**
     * Insert news on the local app storage
     */
    @Insert
    void insert(News... news);

    /**
     * Delete a certain news from the local storage
     */
    @Delete
    void delete(News news);

    /**
     * Delete all the news from the app storage
     */
    @Query("DELETE FROM news")
    void deleteAll();

}
