package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<Mountain> mountains;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mountainAdapter;
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new JsonFile(this, this).execute(JSON_FILE);
        new JsonTask(this).execute(JSON_URL);

        // Lookup the recyclerview in activity layout
        recyclerView = findViewById(R.id.recyclerViewID);
        mountains = new ArrayList<Mountain>();
        // Create adapter passing in the sample user data
        mountainAdapter = new RecyclerViewAdapter(mountains);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(mountainAdapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);

        //mountains = new Gson().fromJson(json, new TypeToken<ArrayList<Mountain>>(){}.getType());
        ArrayList<Mountain> data = new Gson().fromJson(json, new TypeToken<ArrayList<Mountain>>(){}.getType());
        mountains.addAll(data);


        mountainAdapter.notifyDataSetChanged();
    }

}
