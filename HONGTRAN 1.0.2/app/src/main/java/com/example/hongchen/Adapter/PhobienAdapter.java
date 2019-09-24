package com.example.hongchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Activity.DanhsachbaihatActivity;
import com.example.hongchen.Activity.HomePage;
import com.example.hongchen.Fragment.Fragment_PhoBien;
import com.example.hongchen.Model.Playlist;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhobienAdapter extends RecyclerView.Adapter<PhobienAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public PhobienAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_phobien,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewplaylistphobien;
        ImageView imageplaylistphobien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageplaylistphobien = itemView.findViewById(R.id.imageplaylistphobien);
            textviewplaylistphobien = itemView.findViewById(R.id.textviewplaylistphobien);
            imageplaylistphobien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        Picasso.with(context).load(playlist.getHinhNen()).into(holder.imageplaylistphobien);
        holder.textviewplaylistphobien.setText(playlist.getTen());
    }

}
