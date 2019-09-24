package com.example.hongchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Activity.PlayActivity;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchbaihatAdapter extends RecyclerView.Adapter<SearchbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public SearchbaihatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_searchbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.textviewsearchbaihattenbaihat.setText(baihat.getTenBaiHat());
        holder.textviewsearchbaihattencasi.setText(baihat.getCaSi());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imageviewsearchbaihat);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewsearchbaihattenbaihat,textviewsearchbaihattencasi;
        ImageView imageviewtuychonbaihat,imageviewsearchbaihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewsearchbaihattenbaihat = itemView.findViewById(R.id.textviewsearchbaihattenbaihat);
            textviewsearchbaihattencasi = itemView.findViewById(R.id.textviewsearchbaihattencasi);
            imageviewtuychonbaihat = itemView.findViewById(R.id.imageviewtuychonbaihat);
            imageviewsearchbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayActivity.class);
                    intent.putExtra("baihat",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
