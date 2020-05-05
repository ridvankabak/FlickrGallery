package com.ridvankabak.flickrgallery.Adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ridvankabak.flickrgallery.ui.MainActivity.MainActivity;
import com.ridvankabak.flickrgallery.Model.Photo;
import com.ridvankabak.flickrgallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.CardViewTasarimTutucu> {
    private MainActivity mainActivity;
    private List<Photo> photoList;


    public PhotoAdapter(MainActivity mainActivity, List<Photo> photoList) {
        this.mainActivity = mainActivity;
        this.photoList = photoList;

    }

    @NonNull
    @Override
    public CardViewTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);

        return new CardViewTasarimTutucu(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimTutucu holder, int position) {
        Photo photo = photoList.get(position);
        String photoCome = "https://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg";

        Picasso.get().load(photoCome).into(holder.satirImage);
        String photoTitle= photo.getTitle();
        if(photo.getTitle().isEmpty()){
            holder.satirYazi.setText("Title boş gözüküyor!");
            Log.e("title","boş");
        }else{

            String msg;
            String titlee = photo.getTitle();
            String [] word = null;
            word = titlee.split(" ");
            if(word.length>10){
                msg = word[0]+" "+word[1]+" "+word[2]+" "+word[3]+" "+word[4]+" "+word[5]+" "+word[6]+" "+word[7]+" ... <b>devamını gör</b>";
                holder.satirYazi.setText(Html.fromHtml(msg));
            }else if(photo.getTitle().contains("?")){
                int titlechar = photo.getTitle().indexOf("?") + 1;
                String title = photo.getTitle().substring(0,titlechar) +" ... <b>devamını gör</b>";
                holder.satirYazi.setText(Html.fromHtml(title));
                Log.e("title","?");
            }else if(photo.getTitle().contains("!")){
                int titlechar = photo.getTitle().indexOf("!") + 1;
                String title = photo.getTitle().substring(0,titlechar) +" ... <b>devamını gör</b>";
                holder.satirYazi.setText(Html.fromHtml(title));
                Log.e("title","!");
            }else if(photo.getTitle().contains(".")){
                int titlechar = photo.getTitle().indexOf(".") + 1;
                String title = photo.getTitle().substring(0,titlechar) +" ... <b>devamını gör</b>";
                holder.satirYazi.setText(Html.fromHtml(title));
                Log.e("title",".");
            }else if(photo.getTitle().contains(",")){
                int titlechar = photo.getTitle().indexOf(",") + 1;
                String title = photo.getTitle().substring(0,titlechar-1) +" ... <b>devamını gör</b>";
                holder.satirYazi.setText(Html.fromHtml(title));
                Log.e("title",",");
            }else{
                holder.satirYazi.setText(photo.getTitle());
                Log.e("title","title");
            }


        }

       holder.satirCard.setOnClickListener(view -> {
           mainActivity.onPhotoItemClick(photoCome,photoTitle);
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class CardViewTasarimTutucu extends RecyclerView.ViewHolder{
        public TextView satirYazi;
        public ImageView satirImage;
        public CardView satirCard;

        public CardViewTasarimTutucu (View view){
            super(view);
            satirYazi = view.findViewById(R.id.textViewTitle);
            satirImage = view.findViewById(R.id.imageViewGelPic);
            satirCard = view.findViewById(R.id.foto_card);
        }
    }

}
