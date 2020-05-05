package com.ridvankabak.flickrgallery.Service;

public class ApiUtils {
    public static final String BASE_URL = "https://www.flickr.com/services/";
    public static final String METHOD = "flickr.photos.getRecent";
    public static final String API_KEY = "d7e2baf2b3dd30ca1dbfc1cd136de755";
    public static final String FORMAT = "json";
    public static final String PER_PAGE = "20";
    public static final String NO_JSON_CALL_BACK = "1";

    public static AppcentDaoInterface getAppcentDaoInterface(){

        return RetrofitClient.getClient(BASE_URL).create(AppcentDaoInterface.class);
    }
}

//https://www.flickr.com/services/rest/