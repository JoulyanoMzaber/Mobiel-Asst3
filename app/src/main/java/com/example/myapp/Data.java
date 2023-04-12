package com.example.myapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data{

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name="gameTitle")
    String gameTitle;

    @ColumnInfo(name="gameRating")
    Integer gameRating;

    public Data(String gameTitle, Integer gameRating)
    {
        this.gameTitle = gameTitle;
        this.gameRating = gameRating;
    }

    public Data(){}

    @Override
    public String toString()
    {
        return "ID: " + id + "Game Title: " + gameTitle + "Game Rating: " + gameRating;
    }
}
