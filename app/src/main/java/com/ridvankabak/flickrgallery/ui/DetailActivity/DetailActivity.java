package com.ridvankabak.flickrgallery.ui.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.ridvankabak.flickrgallery.Data.AppDatabase;

import com.ridvankabak.flickrgallery.Data.Images;
import com.ridvankabak.flickrgallery.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

public class DetailActivity extends AppCompatActivity implements DetailActivityContract.View {
    String photoCome,phototitle;
    ImageView imageViewDetayBackground,imageViewBack;
    TextView textViewTitle;
    LikeButton likeButton;
    AppDatabase db;
    DetailActivityPresenter mPresenter;

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();

        setListener();

        mPresenter = new DetailActivityPresenter(this);
        mPresenter.getPhoto(photoCome);
        mPresenter.getTitle(phototitle);
    }

    private void initUI() {
        imageViewDetayBackground = findViewById(R.id.imageViewDetayBackground);
        imageViewBack = findViewById(R.id.imageViewBack);
        textViewTitle = findViewById(R.id.textViewTitleDetay);
        likeButton = findViewById(R.id.heart_button);

        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"resimler")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        photoCome = getIntent().getStringExtra("nesnefoto");
        phototitle = getIntent().getStringExtra("nesnebaslik");
    }

    private void setListener() {
        imageViewBack.setOnClickListener(view -> onBackPressed());

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                BitmapDrawable drawable = (BitmapDrawable) imageViewDetayBackground.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();
                saveImage(bitmap);
                saveImageData();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                deleteImage();
            }
        });
    }

    private void saveImageData() {
        Images image = new Images();
        image.imageNo = String.valueOf(n);
        image.title = textViewTitle.getText().toString();
        db.imageDao().setImage(image);
    }

    private void deleteImage() {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String dosya = root + "/flickr_saved_images";
        File resim_yolu = new File(dosya, "Image-"+n+".jpg");
        Log.e("n",String.valueOf(n));
        resim_yolu.delete();

    }

    private void saveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File dosya = new File(root + "/flickr_saved_images");
        dosya.mkdirs();
        Random generator = new Random();
        n = 10000;
        n = generator.nextInt(n);
        String resim = "Image-" + n + ".jpg";
        File resim_yolu = new File(dosya, resim);
        if (resim_yolu.exists())
            resim_yolu.delete();
        try {
            FileOutputStream out = new FileOutputStream(resim_yolu);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPhotoToViews(String photoCome) {
        Picasso.get().load(photoCome).into(imageViewDetayBackground);
    }

    @Override
    public void setTitle(String phototitle) {
        textViewTitle.setText(phototitle);
    }
}
