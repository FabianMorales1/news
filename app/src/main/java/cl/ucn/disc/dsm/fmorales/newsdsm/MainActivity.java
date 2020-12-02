/*
 * Copyright (c) 2020 Fabian Morales Araya, fabian.morales@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 *  associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 *  NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.fmorales.newsdsm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;
import cl.ucn.disc.dsm.fmorales.newsdsm.services.Contracts;
import cl.ucn.disc.dsm.fmorales.newsdsm.services.ContractsImplNewsApi;


/**
 * The Main Class.
 *
 * @author Fabian Morales Araya
 *
 */
public class MainActivity extends AppCompatActivity {

    protected ListView listView;

    /**
     * OnCreate
     *
     * @param savedInstanceState used to reload the app
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = findViewById(R.id.am_lv_news);

        // Get the news in the background
        AsyncTask.execute(() -> {

            // Using the contracts to get the news
            Contracts contracts = new ContractsImplNewsApi("ded30ff72b6a434caea6cd13ed35fda2");

            //Get the news from the NewsApi(internet!)
            List<News> listNews = contracts.retrieveNews(30);

            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1,
                    listNews
            );

            // Set the adapter
            runOnUiThread(() -> {
                this.listView.setAdapter(adapter);
            });



        });
    }


}