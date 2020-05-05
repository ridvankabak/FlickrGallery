package com.ridvankabak.flickrgallery.ui.FavoriteActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ridvankabak.flickrgallery.Adapter.ImageAdapter;
import com.ridvankabak.flickrgallery.Data.AppDatabase;
import com.ridvankabak.flickrgallery.Data.Images;
import com.ridvankabak.flickrgallery.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements FavoriteActivityContract.View{
    private static final String TAG = "FavoriteActivity";
    private FavoriteActivityContract.Presenter mPresenter;
    private ImageView imageViewBack;
    private LinearLayout linearLayout;
    private RecyclerView rv;
    ImageAdapter adapter;
    List<Images> resimler;
    static AppDatabase db;

    public static void deleteImageData(Images image) {
        db.imageDao().setDeleteImage(image);

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String myDir = root + "/flickr_saved_images";
        File file = new File(myDir, "Image-"+image.imageNo+".jpg");
        Log.e("n",String.valueOf(image.imageNo));
        file.delete();

        Log.e("Kalan",String.valueOf(db.imageDao().getTumResimler().size()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initUi();
        setListener();

        mPresenter = new FavoriteActivityPresenter(this);
        mPresenter.pullImage();
        mPresenter.checkImage(resimler);


    }

    private void setListener() {
        imageViewBack.setOnClickListener(v -> onBackPressed());
    }

    private void initUi() {
        imageViewBack = findViewById(R.id.imageViewBackFav);
        linearLayout = findViewById(R.id.linearlayout);
        rv = findViewById(R.id.rvfav);
        resimler = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"resimler")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();



    }

    @Override
    public void pushImage() {
        resimler = db.imageDao().getTumResimler();
    }

    @Override
    public void loadImage() {
        adapter = new ImageAdapter(this,resimler);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,1));

        rv.setAdapter(adapter);
    }

    @Override
    public void removeNothing() {
        linearLayout.setVisibility(View.INVISIBLE);
        rv.setVisibility(View.VISIBLE);
    }

    @Override
    public void openNothing() {
        rv.setVisibility(View.INVISIBLE);
       linearLayout.setVisibility(View.VISIBLE);
    }
}
