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

package cl.ucn.disc.dsm.fmorales.newsdsm.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fmorales.newsdsm.R;
import cl.ucn.disc.dsm.fmorales.newsdsm.model.AppDataBase;
import cl.ucn.disc.dsm.fmorales.newsdsm.model.News;
import cl.ucn.disc.dsm.fmorales.newsdsm.services.Contracts;
import cl.ucn.disc.dsm.fmorales.newsdsm.services.ContractsImplNewsApi;

/**
 * The Main Class.
 *
 * @author Fabian Morales, Felipe Herrera, Diego Duarte.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * The SwipeRefresh reference
     */
    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * OnCreate.
     * @param savedInstanceState used to reload the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The toolbar
        this.setSupportActionBar(findViewById(R.id.am_t_toolbar));

        // The FastAdapter
        ModelAdapter<News, NewsItem> newsAdapter = new ModelAdapter<>(NewsItem::new);
        FastAdapter<NewsItem> fastAdapter = FastAdapter.with(newsAdapter);
        fastAdapter.withSelectable(false);

        // The Recycler view
        RecyclerView recyclerView = findViewById(R.id.am_rv_news);
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // The SwipeRefresh Interactions
        swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            // Get the news in the background thread
            AsyncTask.execute(() -> {

              // Using the contracts to get the news
              ContractsImplNewsApi contracts = new ContractsImplNewsApi(
                  "ded30ff72b6a434caea6cd13ed35fda2");
                if (isNetworkAvailable()) {
                    // Get the news from internet
                    List<News> newsList = contracts.retrieveNews(30);

                    // Set the adapter
                    runOnUiThread(() -> {
                        // clear the items
                        newsAdapter.clear();
                        // add the news items
                        newsAdapter.add(newsList);
                    });
                }
                else{

                    Context context = getApplicationContext();
                    CharSequence text = "Conexion a internet no disponible!";
                    int duration = Toast.LENGTH_SHORT;

                    if (Looper.myLooper()==null){
                        Looper.prepare();
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            });
               fastAdapter.notifyDataSetChanged();
               swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Create the local database to store the news
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "localNewsDb").allowMainThreadQueries().build();

        // Get the news in the background thread
        AsyncTask.execute(() -> {

            // If the app detects an internet connection
            // Delete all the news stored on the local database so they are replaced for the new ones
            if (isNetworkAvailable()) {
                db.newsDao().deleteAll();

                // Using the contracts to get the news ..
                Contracts contracts = new ContractsImplNewsApi("ded30ff72b6a434caea6cd13ed35fda2");
                // Get the News from NewsApi (internet!)
                List<News> listNews = contracts.retrieveNews(30);

                // Stores the news from the news api into the local database
                for (int i = 0; i < listNews.size()-1; i++) {
                    if (listNews.get(i) != null) {
                        db.newsDao().insert(listNews.get(i));
                    }
                }

                // Show the list of news from the internet
                // Set the adapter!
                runOnUiThread(() -> {
                    newsAdapter.add(listNews);
                });

                // If the app doest detect an internet connection
                // Show the list of news from the local database
            } else {
                runOnUiThread(() -> {
                newsAdapter.add(db.newsDao().getAll());
                });
            }
        });
    }

    /**
     * Function that checks if there is an active internet connection or not
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the correct item was clicked
        if (item.getItemId() == R.id.night_mode) {
        }
        // TODO: Get the night mode state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();

        // Set the theme mode for the restarted activity
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate();
        return true;
    }
}
