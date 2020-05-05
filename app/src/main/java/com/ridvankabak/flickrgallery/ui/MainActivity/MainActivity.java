package com.ridvankabak.flickrgallery.ui.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ridvankabak.flickrgallery.Adapter.PhotoAdapter;
import com.ridvankabak.flickrgallery.ui.DetailActivity.DetailActivity;
import com.ridvankabak.flickrgallery.Model.Photo;
import com.ridvankabak.flickrgallery.R;
import com.ridvankabak.flickrgallery.ui.FavoriteActivity.FavoriteActivity;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{
    private static final String TAG = "MainActivity";
    private MainActivityContract.Presenter mPresenter;
    private RecyclerView rv;
    private ImageView imageViewFav;
    private GeometricProgressView pbLoading;

    PhotoAdapter adapter;
    List<Photo> photoList;

    private int pageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setListener();

        mPresenter = new MainActivityPresenter(this);
        mPresenter.permissionCheck();
        mPresenter.requestFromDataServer();
    }

    private void initUI() {
        rv = findViewById(R.id.rv);
        imageViewFav = findViewById(R.id.imageViewFav);
        photoList = new ArrayList<>();
        adapter = new PhotoAdapter(this,photoList);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));

        rv.setAdapter(adapter);

        pbLoading = findViewById(R.id.pb_loading);

    }

    private void setListener() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(isLastItemDisplaying(recyclerView)){
                    showProgress();
                    mPresenter.getMoreData(pageNo);
                    Log.e(TAG,"Load More");
                }

            }
        });

        imageViewFav.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), FavoriteActivity.class)));

    }

    @Override
    public void setDataToRecyclerView(List<Photo> photoArrayList) {
        photoList.addAll(photoArrayList);
        adapter.notifyDataSetChanged();

        pageNo++;
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void onPhotoItemClick(String photoCome,String photoTitle) {
        if (photoCome == null) {
            return;
        }
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("nesnefoto",photoCome);
        detailIntent.putExtra("nesnebaslik",photoTitle);
        startActivity(detailIntent);
    }

    @Override
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }else{
            Toast.makeText(this, "İzin verilmediği için beğendiniz gönderilere ulaşamazsınız!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if(recyclerView.getAdapter().getItemCount() != 0){
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            if(lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)

                return true;
        }

        return false;
    }


}
