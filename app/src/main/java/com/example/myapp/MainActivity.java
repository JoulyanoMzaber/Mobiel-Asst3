package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
  MainActivity that displays two buttons.
  When the "listMyGames" button is clicked, it launches MainActivity2 activity.
  When the "editMyGames" button is clicked, it launches MainActivity3 activity.
 */
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------INITIALIZING--------------------------//
        // BUTTONS
        Button listMyGames = (Button) findViewById(R.id.listMyGames);
        Button editMyGames = (Button) findViewById(R.id.editMyGames);

        //----------------------Activities-----------------------------//
        /**Sets a click listener on the "editMyGames" button that launches the MainActivity3 activity.
         @param editMyGames is clicked.*/
        editMyGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        /**Sets a click listener on the "listMyGames" button that launches the MainActivity2 activity.
         @param listMyGames is clicked.*/
        listMyGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}