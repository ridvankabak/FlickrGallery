package com.ridvankabak.flickrgallery.ui.DetailActivity;

public interface DetailActivityContract {
    interface View{

        void setPhotoToViews(String photoCome);

        void setTitle(String phototitle);
    }
    interface Presenter{

        void getPhoto(String photoCome);

        void getTitle(String phototitle);
    }
}
