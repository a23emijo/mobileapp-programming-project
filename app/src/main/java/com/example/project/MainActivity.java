package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    // Fields
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23emijo";

    private RecyclerView recView;

    private RecyclerViewAdapter recViewAdapter;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Executes the JSON download
        new JsonTask(this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {

        // Creates an array of all the items in Animal
        Type type = new TypeToken<ArrayList<Animal>>() {}.getType();
        ArrayList<Animal> listOfAnimals = gson.fromJson(json, type);

        // Find the RecyclerView and adds data to it
        recViewAdapter = new RecyclerViewAdapter(this, listOfAnimals, new RecyclerViewAdapter.OnClickListener(){

            @Override
            public void onClick(Animal animal) {
                // Sets the intents
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("auxInfo", animal.getAuxdata().getInfo()); // Gets the auxInfo data
                startActivity(intent); // Starts the activity with the intents
            }
        });
        recView = findViewById(R.id.recycler_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recViewAdapter);

        recViewAdapter.notifyDataSetChanged();
    }

}