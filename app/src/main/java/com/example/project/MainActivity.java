package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23emijo";

    private ArrayList<Animal> Animals;

    private RecyclerView recView;

    private RecyclerViewAdapter recViewAdapter;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animals = new ArrayList<>(Arrays.asList(
                new Animal("Zebra"),
                new Animal("Hund"),
                new Animal("Björn")
        ));

        recViewAdapter = new RecyclerViewAdapter(this, Animals);

        recView = findViewById(R.id.recycler_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recViewAdapter);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);

        Type type = new TypeToken<ArrayList<Animal>>() {}.getType();
        ArrayList<Animal> listOfMountains = gson.fromJson(json, type);

        recViewAdapter.updateAdapter(listOfMountains);

        recViewAdapter.notifyDataSetChanged();
    }

}
