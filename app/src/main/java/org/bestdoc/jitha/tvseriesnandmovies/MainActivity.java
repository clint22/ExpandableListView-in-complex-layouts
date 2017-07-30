package org.bestdoc.jitha.tvseriesnandmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView)findViewById(R.id.exp_list_view);


        try {
            createSeriesAndMoviesList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createSeriesAndMoviesList() throws JSONException {

        String message;
        JSONArray json = new JSONArray();
//        json.put("name", "student");

        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        JSONObject item4 = new JSONObject();

        item.put("name", "Fight Club");
        item.put("id", 1);
        item.put("type", "Movie");

        item1.put("name", "13 reasons why");
        item1.put("id", 2);
        item1.put("type", "Series");

        item2.put("name", "Dunkirk");
        item2.put("id", 3);
        item2.put("type", "Movie");

        item3.put("name", "Game of thrones");
        item3.put("id", 4);
        item3.put("type", "Series");


        item4.put("name", "Godfather");
        item4.put("id", 5);
        item4.put("type", "Movie");





        array.put(item);
        array.put(item1);
        array.put(item2);
        array.put(item3);
        array.put(item4);

        JSONObject mainObj = new JSONObject();
        mainObj.put("movie_details", array);


        message = mainObj.toString();


        retrieveJSONManualParsing(message);
        retrieveJSONUsingGSON(message);


    }

    private void retrieveJSONUsingGSON(String message) {


        DetailsModel metrics = new Gson().fromJson(message, DetailsModel.class);
        Log.e("dets", new Gson().toJson(metrics));

        setSortedList(metrics);





    }

    private void setSortedList(DetailsModel movie_details) {

        if (movie_details.getMovie_details() == null || movie_details.getMovie_details().size() == 0) {
            return;
        }


        Log.e("movdets", new Gson().toJson(movie_details.getMovie_details()));
        List<String> listDataHeader = new ArrayList<>();

        listDataHeader.add("Movies");
        listDataHeader.add("Series");


        HashMap<String, List<MovieDetails>> listDataChild = new HashMap<>();

        List<MovieDetails> series = new ArrayList<>();
        List<MovieDetails> movies = new ArrayList<>();

        for (MovieDetails child : movie_details.getMovie_details()) {


            if (child.getType().equals("Movie")) {

                movies.add(child);
            }
            else {

                series.add(child);
            }

        }



        listDataChild.put("Movies", movies);
        listDataChild.put("Series", series);

        Log.e("movdets", new Gson().toJson(movies));
        Log.e("serdets", new Gson().toJson(series));



        expandableListViewAdapter =
                new ExpandableListViewAdapter(MainActivity.this, listDataHeader, listDataChild, movies.size(),
                        series.size());


        expandableListView.setAdapter(expandableListViewAdapter);
        expandableListView.expandGroup(0);
        expandableListView.expandGroup(1);


    }

    private void retrieveJSONManualParsing(String message) throws JSONException {


        JSONObject jsonobj = new JSONObject(message);
        JSONArray jsonarray = new JSONArray();
        jsonarray = jsonobj.getJSONArray("movie_details");

        for ( int i = 0; i < jsonarray.length(); i++) {

            JSONObject jsoninsideobj = jsonarray.getJSONObject(i);
            String name = jsoninsideobj.getString("name");
            Log.e("name", name);



        }
    }
}
