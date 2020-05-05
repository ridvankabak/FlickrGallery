package com.ridvankabak.flickrgallery.ui.DetailActivity;

public class DetailActivityPresenter implements DetailActivityContract.Presenter {

    private DetailActivityContract.View mView;

    public DetailActivityPresenter(DetailActivityContract.View mView){
        this.mView = mView;
    }

    @Override
    public void getPhoto(String photoCome) {
        mView.setPhotoToViews(photoCome);
    }

    @Override
    public void getTitle(String phototitle) {
        mView.setTitle(phototitle);
    }
}
