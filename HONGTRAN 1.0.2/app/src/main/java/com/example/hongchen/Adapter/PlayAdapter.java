package com.example.hongchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Activity.PlayActivity;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;

import java.util.ArrayList;

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public PlayAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_danhsachbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.textviewplaynhactenbaihat.setText(baihat.getTenBaiHat());
        holder.textviewplaynhactencasi.setText(baihat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewplaynhactenbaihat,textviewplaynhactencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewplaynhactenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
            textviewplaynhactencasi = itemView.findViewById(R.id.textviewplaynhactencasi);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, PlayActivity.class);
//                    intent.putExtra("baihat",baihatArrayList.get(getPosition()));
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}
