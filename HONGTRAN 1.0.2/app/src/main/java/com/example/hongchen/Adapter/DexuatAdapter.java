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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DexuatAdapter extends RecyclerView.Adapter<DexuatAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public DexuatAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_dexuat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imageviewdexuat);
        holder.textviewdexuat.setText(baihat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewdexuat;
        ImageView imageviewdexuat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewdexuat = itemView.findViewById(R.id.textviewtendexuat);
            imageviewdexuat = itemView.findViewById(R.id.imagedexuat);
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

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.dong_dexuat,null);
//            viewHolder = new ViewHolder();
//            viewHolder.textviewdexuat = convertView.findViewById(R.id.textviewtendexuat);
//            viewHolder.imageviewdexuat = convertView.findViewById(R.id.imagedexuat);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        Baihat baihat = getItem(position);
//        Picasso.with(getContext()).load(baihat.getHinhBaiHat()).into(viewHolder.imageviewdexuat);
//        viewHolder.textviewdexuat.setText(baihat.getTenBaiHat());
//        return convertView;
//    }
}
