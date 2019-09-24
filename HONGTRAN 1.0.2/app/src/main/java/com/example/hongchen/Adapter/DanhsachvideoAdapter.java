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

import com.example.hongchen.Activity.PlayvideoActivity;
import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.hongchen.Activity.PlayActivity.baihatArrayList;

public class DanhsachvideoAdapter extends RecyclerView.Adapter<DanhsachvideoAdapter.ViewHolder>{

    Context context;
    ArrayList<Video> videoArrayList;

    public DanhsachvideoAdapter(Context context, ArrayList<Video> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachvideo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videoArrayList.get(position);
        Picasso.with(context).load(video.getHinhVideo()).into(holder.imageviewdanhsachvideo);
        holder.textviewdanhsachvideo.setText(video.getTenVideo());
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageviewdanhsachvideo;
        TextView textviewdanhsachvideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewdanhsachvideo = itemView.findViewById(R.id.imageviewdanhsachvideo);
            textviewdanhsachvideo = itemView.findViewById(R.id.textviewdanhsachvideo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayvideoActivity.class);
                    intent.putExtra("video",videoArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
