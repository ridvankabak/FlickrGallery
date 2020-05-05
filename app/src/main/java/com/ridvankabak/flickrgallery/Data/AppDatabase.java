package com.ridvankabak.flickrgallery.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Images.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();
}
