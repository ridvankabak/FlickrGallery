package com.ridvankabak.flickrgallery.ui.MainActivity;

import com.ridvankabak.flickrgallery.Model.Photo;

import java.util.List;

public interface MainActivityContract {

    interface  View {

        void setDataToRecyclerView(List<Photo> photoArrayList);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable throwable);

        void onPhotoItemClick(String photoCome,String photoTitle);

        boolean isPermissionGranted();
    }

    interface Model {
        interface OnFinishedListener {
            void onFinished(List<Photo> movieArrayList);

            void onFailure(Throwable t);
        }

        void getPhotoList(OnFinishedListener onFinishedListener, int pageNo);
    }

    interface Presenter {


        void requestFromDataServer();

        void getMoreData(int pageNo);

        void permissionCheck();
    }
}
