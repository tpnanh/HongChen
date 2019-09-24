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

import com.example.hongchen.Activity.MainActivity;
import com.example.hongchen.Activity.PlayActivity;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.textviewtenbaihat.setText(baihat.getTenBaiHat());
        holder.textviewtencasi.setText(baihat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewtenbaihat,textviewtencasi;
        ImageView imageviewtuychonbaihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewtenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
            textviewtencasi = itemView.findViewById(R.id.textviewtencasi);
            imageviewtuychonbaihat = itemView.findViewById(R.id.imageviewtuychonbaihat);
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
