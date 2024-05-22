package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    // Fields
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a23emijo"; // Link to JSON data
    private RecyclerView recView;
    private RecyclerViewAdapter recViewAdapter;
    private Gson gson = new Gson();
    private Button sortByAToZ;
    private Button sortByZToA;
    private Button about;
    private int saveSort;
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortByAToZ = findViewById(R.id.sort_A_Z_button); // Finds the button for sorting A to Z
        sortByZToA = findViewById(R.id.sort_Z_A_button); // Finds the button for sorting Z to A
        about = findViewById(R.id.about_button); // Finds the to second activity button

        // Sets a OnClickListener for the AboutActivity button
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sets the intent
                startActivity(new Intent(MainActivity.this, AboutActivity.class)); // Starts the activity with the intents
            }
        });

        myPreferenceRef = getSharedPreferences("mySortPref", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        // Sorts the different elements from A to Z
        sortByAToZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recViewAdapter.sortByAToZ();
                recViewAdapter.notifyDataSetChanged();
                myPreferenceEditor.putInt("sortPreference", 1);
                myPreferenceEditor.apply();
            }
        });

        // Sorts the different elements from Z to A
        sortByZToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recViewAdapter.sortByZToA();
                recViewAdapter.notifyDataSetChanged();
                myPreferenceEditor.putInt("sortPreference", 2);
                myPreferenceEditor.apply();
            }
        });

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
                Intent intent = new Intent(MainActivity.this, InfoActivity.class); // Make the intent go from Main- to InfoActivity
                intent.putExtra("auxInfo", animal.getAuxdata().getInfo()); // Gets the auxInfo data
                startActivity(intent); // Starts the activity with the intents
            }
        });
        recView = findViewById(R.id.recycler_view); // Finds the RecyclerView
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(recViewAdapter);

        if(saveSort == 1){ // If A-Z was the latest sort
            recViewAdapter.sortByAToZ();
        } else if (saveSort == 2){ // If Z-A was the latest sort
            recViewAdapter.sortByZToA();
        }
        recViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        saveSort = myPreferenceRef.getInt("sortPreference", 0);
    }
}