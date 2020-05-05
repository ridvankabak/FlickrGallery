package com.ridvankabak.flickrgallery.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.ridvankabak.flickrgallery.Data.Images;
import com.ridvankabak.flickrgallery.R;
import com.ridvankabak.flickrgallery.ui.FavoriteActivity.FavoriteActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.CardViewTasarimTutucu>{
    FavoriteActivity favoriteActivity;
    List<Images> imageList;

    public ImageAdapter(FavoriteActivity favoriteActivity,List<Images> imageList){
        this.favoriteActivity = favoriteActivity;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public CardViewTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_fav,parent,false);

        return new ImageAdapter.CardViewTasarimTutucu(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimTutucu holder, int position) {
        Images image = imageList.get(position);
        loadImage(image.imageNo,holder);
        holder.satirYazi.setText(image.title);

        holder.satirLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                FavoriteActivity.deleteImageData(image);
            }
        });



    }

    private void loadImage(String imageNo, CardViewTasarimTutucu holder) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String myDir = root + "/flickr_saved_images";
        try {
            File f=new File(myDir, "Image-"+imageNo+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.satirImage.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class CardViewTasarimTutucu extends RecyclerView.ViewHolder{
        public TextView satirYazi;
        public ImageView satirImage;
        public CardView satirCard;
        public LikeButton satirLike;

        public CardViewTasarimTutucu (View view){
            super(view);
            satirYazi = view.findViewById(R.id.textViewTitleFavv);
            satirImage = view.findViewById(R.id.imageViewGelPicFav);
            satirCard = view.findViewById(R.id.foto_card_fav);
            satirLike = view.findViewById(R.id.heart_button_fav);
        }
    }
}
