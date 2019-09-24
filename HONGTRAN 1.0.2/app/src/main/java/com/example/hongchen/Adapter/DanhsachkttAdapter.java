package com.example.hongchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Model.Kichtruyenthanh;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachkttAdapter extends RecyclerView.Adapter<DanhsachkttAdapter.ViewHolder> {
    Context context;
    ArrayList<Kichtruyenthanh> kichtruyenthanhArrayList;

    public DanhsachkttAdapter(Context context, ArrayList<Kichtruyenthanh> kichtruyenthanhArrayList) {
        this.context = context;
        this.kichtruyenthanhArrayList = kichtruyenthanhArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachktt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kichtruyenthanh kichtruyenthanh = kichtruyenthanhArrayList.get(position);
        Picasso.with(context).load(kichtruyenthanh.getHinhKTT()).into(holder.imageviewdanhsachktt);
        holder.textviewtendanhsachktt.setText(kichtruyenthanh.getTenKTT());
    }

    @Override
    public int getItemCount() {
        return kichtruyenthanhArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageviewdanhsachktt;
        TextView textviewtendanhsachktt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewdanhsachktt = itemView.findViewById(R.id.imageviewdanhsachktt);
            textviewtendanhsachktt = itemView.findViewById(R.id.textviewtendanhsachktt);
        }
    }
}
