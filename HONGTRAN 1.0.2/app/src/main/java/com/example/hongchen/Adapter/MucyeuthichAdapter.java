package com.example.hongchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongchen.Activity.PlayActivity;
import com.example.hongchen.Activity.PlayvideoActivity;
import com.example.hongchen.Model.YeuThich;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MucyeuthichAdapter extends RecyclerView.Adapter<MucyeuthichAdapter.ViewHolder>{

    Context context;
    ArrayList<YeuThich> baiHatYeuThich;

    public MucyeuthichAdapter(Context context, ArrayList<YeuThich> baihatyeuthich) {
        this.context = context;
        this.baiHatYeuThich = baihatyeuthich;
    }

    @NonNull
    @Override
    public MucyeuthichAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_mucyeuthich,parent,false);
        return new MucyeuthichAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MucyeuthichAdapter.ViewHolder holder, int position) {

        final YeuThich yeuThich = baiHatYeuThich.get(position);
        Picasso.get().load(yeuThich.getImage()).into(holder.imageviewmucyeuthich);
        holder.textviewtenbaihatmucyeuthich.setText(yeuThich.getName());
        if (yeuThich.getType().equals("MV")){
            holder.textviewMV.setVisibility(View.VISIBLE);
        }else{
            holder.textviewMV.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (yeuThich.getType().equals("MV")){
                    Intent intent = new Intent(context, PlayvideoActivity.class);
                    intent.putExtra("tenBaiHat",yeuThich.getName());
                    intent.putExtra("linkBaiHat",yeuThich.getUrl());
                    intent.putExtra("MucYeuThichItem","MucYeuThich");
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, PlayActivity.class);
                    intent.putExtra("tenBaiHat",yeuThich.getName());
                    intent.putExtra("linkBaiHat",yeuThich.getUrl());
                    intent.putExtra("id",yeuThich.getId());
                    intent.putExtra("image",yeuThich.getImage());
                    intent.putExtra("caSi",yeuThich.getTacGia());
                    intent.putExtra("MucYeuThichItem","MucYeuThich");
                    context.startActivity(intent);
                }


            }
        });
    }


    @Override
    public int getItemCount() {
        if (baiHatYeuThich == null){
            return 0;
        }
        else {
            return baiHatYeuThich.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageviewmucyeuthich;
        TextView textviewtenbaihatmucyeuthich, textviewMV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewmucyeuthich = itemView.findViewById(R.id.imageviewmucyeuthich);
            textviewtenbaihatmucyeuthich = itemView.findViewById(R.id.textviewtenbaihatmucyeuthich);
            textviewMV = itemView.findViewById(R.id.textviewMV);

        }
    }
}
