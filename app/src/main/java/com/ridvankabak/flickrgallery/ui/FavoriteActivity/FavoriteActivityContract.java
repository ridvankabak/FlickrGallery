package com.ridvankabak.flickrgallery.ui.FavoriteActivity;

import com.ridvankabak.flickrgallery.Data.Images;

import java.util.List;

public interface FavoriteActivityContract {
    interface View{

        void pushImage();

        void loadImage();

        void removeNothing();

        void openNothing();
    }
    interface Presenter{

        void pullImage();

        void checkImage(List<Images> resimler);
    }
}
