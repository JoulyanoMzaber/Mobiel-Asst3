package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
  The MainActivity2 class displays a list of games.
  It contains various buttons to interact with the data,
  such as display all games, find a game by id, delete a game by id, and delete all games.
  It also has buttons to navigate to other activities.
 */
public class MainActivity2 extends AppCompatActivity
{
    // INITIALIZING
    ListView myListView;
    List<Data> allReviews = null;
    ArrayAdapter<Data> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //----------------------INITIALIZING--------------------------//
        // BUTTONS
        Button findIdBtn = (Button) findViewById(R.id.findIdBtn);
        Button displayAllBtn = (Button)findViewById(R.id.displayAllBtn);
        Button deleteIdBtn = (Button) findViewById(R.id.deleteIdBtn);
        Button deleteAllBtn = (Button) findViewById(R.id.deleteAllBtn);
        Button editMyGames2 = (Button) findViewById(R.id.editMyGames2);
        Button homeBtn = (Button) findViewById(R.id.homeBtn);
        // INPUTS
        EditText findIdInput = findViewById(R.id.findIdInput);
        EditText deleteIdInput = findViewById(R.id.deleteIdInput);
        // Database
        DataDB dataDB = DataDB.getInstance(this);

        //------------------FUNCTIONS---------------------------------//
        /**Sets a click listener on the DISPLAY ALL button that displays all existing data.
         @param displayAllBtn is clicked.*/
        displayAllBtn.setOnClickListener((View view) ->
        {
            allReviews = dataDB.dataDAO().findAllData();
            myListView = findViewById(R.id.myListView);
            adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, allReviews);
            myListView.setAdapter(adapter);
        });
        /**Sets a click listener on the FIND ID button that displays a single game as requested.
         @param findIdBtn is clicked.*/
        findIdBtn.setOnClickListener((View view) ->
        {
            String parseId = findIdInput.getText().toString();
            long id = Long.parseLong(parseId);
            Data data = dataDB.dataDAO().findDataById(id);

            if (data != null)
            {
                allReviews = new ArrayList<>();
                allReviews.add(data);
                myListView = findViewById(R.id.myListView);
                adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, allReviews);
                myListView.setAdapter(adapter);
            }
            else
            {
                Toast.makeText(this, "No game found with id " + id, Toast.LENGTH_SHORT).show();
            }
        });
        /**Sets a click listener on the DELETE ALL button that deletes all existing data from the database.
         @param deleteAllBtn is clicked.*/
        deleteAllBtn.setOnClickListener((View view) ->
        {
            dataDB.dataDAO().deleteAll();
            allReviews.clear();
            adapter.notifyDataSetChanged();
        });
        /**Sets a click listener on the DELETE ID button that deletes a single game as requested.
         @param deleteIdBtn is clicked.*/
        deleteIdBtn.setOnClickListener((View view) ->
        {
            String idString = deleteIdInput.getText().toString();
            long id = Long.parseLong(idString);
            dataDB.dataDAO().deleteById(id);
            displayAllBtn.performClick();
        });

        //----------------------Activities-----------------------------//
        /**Sets a click listener on the "Edit My Games" button that launches the MainActivity3 activity.
         @param editMyGames2 is clicked.*/
        editMyGames2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        /**Sets a click listener on the "Home" button that launches the MainActivity activity.
         @param homeBtn is clicked.*/
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}