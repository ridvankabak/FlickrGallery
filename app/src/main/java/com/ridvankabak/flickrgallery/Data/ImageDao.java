package com.ridvankabak.flickrgallery.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Query("SELECT * FROM tablo_foto")
    List<Images> getTumResimler();

    @Insert
    void setImage(Images... image);

    @Delete
    void setDeleteImage(Images... deleteImage);
}
