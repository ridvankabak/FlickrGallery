package com.ridvankabak.flickrgallery.ui.MainActivity;

import com.ridvankabak.flickrgallery.Model.Photo;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter,MainActivityContract.Model.OnFinishedListener {

    private MainActivityContract.View mView;
    private MainActivityContract.Model mModel;

    public MainActivityPresenter(MainActivityContract.View view) {
        mView= view;
        mModel = new MainActivityModel();
    }

    @Override
    public void getMoreData(int pageNo) {
        mModel.getPhotoList(this,pageNo);
    }

    @Override
    public void permissionCheck() {
        if(!mView.isPermissionGranted());
    }

    @Override
    public void requestFromDataServer() {
        if (mView != null) {
            mView.showProgress();
        }
        mModel.getPhotoList(this, 1);
    }

    @Override
    public void onFinished(List<Photo> photoArrayList) {
        mView.setDataToRecyclerView(photoArrayList);
        if (mView != null) {
           mView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        mView.onResponseFailure(t);
        if (mView != null) {
            mView.hideProgress();
        }
    }
}
