package com.ridvankabak.flickrgallery.ui.FavoriteActivity;

import com.ridvankabak.flickrgallery.Data.Images;

import java.util.List;

public class FavoriteActivityPresenter implements FavoriteActivityContract.Presenter {
    private FavoriteActivityContract.View mView;

    public FavoriteActivityPresenter(FavoriteActivityContract.View mView){
        this.mView = mView;
    }

    @Override
    public void pullImage() {
        mView.pushImage();
    }

    @Override
    public void checkImage(List<Images> resimler) {
        if(!resimler.isEmpty()){
            mView.removeNothing();
            mView.loadImage();
        }else{
            mView.openNothing();
        }
    }
}
