package com.ridvankabak.flickrgallery.ui.SplashActivity;



public class SplashActivityPresenter implements SplashActivityContract.Presenter {

    SplashActivityContract.View mView;

    public SplashActivityPresenter(SplashActivityContract.View mView){
        this.mView = mView;
    }


    @Override
    public void showSplash() {
        mView.timer();
    }
}
