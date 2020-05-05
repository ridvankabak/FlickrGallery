package com.ridvankabak.flickrgallery.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablo_foto")

public class Images {

    @ColumnInfo(name = "image_no")
    public String imageNo;
    @ColumnInfo(name = "title")
    public String title;
    @PrimaryKey(autoGenerate = true)
    int imageId = 0;

    @NonNull
    @Override
    public String toString() {
        return imageNo+" "+title+" "+imageId;
    }
}

