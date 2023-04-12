package com.example.myapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDAO {
    @Query("SELECT * FROM data")
    List<Data> findAllData();

    @Query("SELECT * FROM data WHERE id=:id")
    Data findDataById(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert (Data data);

    @Query ("DELETE FROM data WHERE id=:id")
    void deleteById(Long id);

    @Query ("DELETE FROM data")
    void deleteAll();

    @Query("UPDATE data SET gameTitle = :newTitle, gameRating = :newRating WHERE id = :id")
    void updateGame(Long id, String newTitle, Integer newRating);
}

