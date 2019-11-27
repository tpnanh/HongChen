package com.example.hongchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongchen.Model.Ost;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DanhsachostAdapter extends RecyclerView.Adapter<DanhsachostAdapter.ViewHolder>{
    Context context;
    ArrayList<Ost> ostArrayList;

    public DanhsachostAdapter(Context context, ArrayList<Ost> ostArrayList) {
        this.context = context;
        this.ostArrayList = ostArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachost,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ost ost = ostArrayList.get(position);
        Picasso.get().load(ost.getHinhOst()).into(holder.imagedanhsachost);
        holder.textviewdanhsachost.setText(ost.getTenOst());
    }

    @Override
    public int getItemCount() {
        if (ostArrayList == null){
            return 0;
        }
        else {
            return ostArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagedanhsachost;
        TextView textviewdanhsachost;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagedanhsachost = itemView.findViewById(R.id.imagedanhsachost);
            textviewdanhsachost = itemView.findViewById(R.id.textviewdanhsachost);
        }
    }
}
