package com.ridvankabak.flickrgallery.Service;


import com.ridvankabak.flickrgallery.Model.PhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppcentDaoInterface {

    @GET("rest?")
    Call<PhotoResponse> tumFotolar(@Query("method") String method, @Query("api_key") String api_key,
                                   @Query("per_page") String per_page, @Query("format") String format,
                                   @Query("nojsoncallback") String nojsoncallback, @Query("page") String page);


    //?method=flickr.photos.getRecent
    // &api_key=d7e2baf2b3dd30ca1dbfc1cd136de755
    // &per_page=20
    // &format=json
    // &nojsoncallback=1

    //"http://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getServer()+".jpg"
}
