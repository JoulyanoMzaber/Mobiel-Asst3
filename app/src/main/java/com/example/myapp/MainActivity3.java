package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
  The MainActivity3 class allows adding or updating an entry.
  It contains the Add and Update buttons to interact with the database.
  It also has buttons to navigate to other activities.
 */
public class MainActivity3 extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //----------------------INITIALIZING--------------------------//
        // BUTTONS
        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        Button listMyGames2 = (Button) findViewById(R.id.listMyGames2);
        Button homeBtn2 = (Button) findViewById(R.id.homeBtn2);
        // INPUTS
        EditText gameIdInput = findViewById(R.id.gameIdInput);
        EditText gameTitleInput = findViewById(R.id.gameTitleInput);
        EditText ratingInput = findViewById(R.id.ratingInput);
        // DATABASE
        DataDB dataDB = DataDB.getInstance(this);

        //------------------FUNCTIONS---------------------------------//
        /**Sets a click listener on the ADD button that retrieves
           user input for a new Game and inserts it into the database.
         @param addBtn is clicked.*/
        addBtn.setOnClickListener((View view) ->
        {
            // Get user input
            String text = gameTitleInput.getText().toString();
            int rate = Integer.parseInt(ratingInput.getText().toString());
            int id = gameIdInput.getId();

            // Validate input
            if (!text.isEmpty() || id != 0 || rate != 0)
            {
                // Add data to the database
                text = text.trim();
                Data data = new Data(text, rate);
                dataDB.dataDAO().insert(data);

             // clear TextBoxes
                gameIdInput.setText("");
                gameTitleInput.setText("");
                ratingInput.setText("");
            }
        });

        /**Sets a click listener on the UPDATE button that retrieves
           user input for an existing Game ID and updates it.
         @param updateBtn is clicked.*/
        updateBtn.setOnClickListener((View view) ->
        {
            // Get user input
            String title = gameTitleInput.getText().toString().trim();
            int rate = Integer.parseInt(ratingInput.getText().toString());
            long id = Long.parseLong(gameIdInput.getText().toString());

            // Validate input
            if (!title.isEmpty() && id > 0 && rate > 0)
            {
                // Create Data object with updated values
                Data data = new Data(title, rate);
                data.id = id;

                // Update data in database
                dataDB.dataDAO().updateGame(id, title, rate);

                // Clear TextBoxes
                gameIdInput.setText("");
                gameTitleInput.setText("");
                ratingInput.setText("");
            }
        });

        //----------------------Activities-----------------------------//
        /**Sets a click listener on the "List My Games" button that launches the MainActivity2 activity.
         @param listMyGames2 is clicked.*/
        listMyGames2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        /**Sets a click listener on the "Home" button that launches the MainActivity activity.
          @param homeBtn2 is clicked.*/
        homeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}