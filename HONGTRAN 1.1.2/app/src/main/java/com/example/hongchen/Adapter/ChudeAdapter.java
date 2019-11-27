package com.example.hongchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongchen.Model.ChuDe;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChudeAdapter extends ArrayAdapter<ChuDe> {

    public ChudeAdapter(@NonNull Context context, int resource, @NonNull List<ChuDe> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView textviewchude;
        ImageView imagechude;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_chude, null);
            viewHolder = new ChudeAdapter.ViewHolder();
            viewHolder.textviewchude = convertView.findViewById(R.id.textviewtenchude);
            viewHolder.imagechude = convertView.findViewById(R.id.imagechude);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChudeAdapter.ViewHolder) convertView.getTag();
        }

        ChuDe chude = getItem(position);
        Picasso.get().load(chude.getHinhChuDe()).into(viewHolder.imagechude);
        viewHolder.textviewchude.setText(chude.getTenChuDe());
        return convertView;
    }

}
