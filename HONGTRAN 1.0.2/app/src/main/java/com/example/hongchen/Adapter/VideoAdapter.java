package com.example.hongchen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongchen.Activity.PlayvideoActivity;
import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    Context context;
    ArrayList<Video> videoArrayList;

    public VideoAdapter(Context context, ArrayList<Video> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videoArrayList.get(position);
        Picasso.get().load(video.getHinhVideo()).into(holder.imagevideo);
        holder.textviewvideo.setText(video.getTenVideo());
    }

    @Override
    public int getItemCount() {
        if (videoArrayList == null){
            return 0;
        }
        else {
            return videoArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewvideo;
        ImageView imagevideo;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textviewvideo = itemView.findViewById(R.id.textviewvideo);
            imagevideo = itemView.findViewById(R.id.imagevideo);
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

    public void setData(ArrayList<Video> data) {
        this.videoArrayList = data;
        notifyDataSetChanged();
    }
}
