package com.example.hongchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        if (baihatArrayList == null){
            return 0;
        }
        else {
            return baihatArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textviewplaynhactenbaihat,textviewplaynhactencasi;
        Button imagebuttonlove;

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
