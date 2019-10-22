package com.example.hongchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hongchen.Model.Quangcao;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quangcao> arrayListBanner;

    @Override
    public int getCount() {
        if (arrayListBanner == null){
            return 0;
        }
        else {
            return arrayListBanner.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imagrviewbackgroundbanner = view.findViewById(R.id.imageviewbackgroundbanner);

        Picasso.get().load(arrayListBanner.get(position).getHinhAnh()).into(imagrviewbackgroundbanner);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setData(ArrayList<Quangcao> data) {
        this.arrayListBanner = data;
        notifyDataSetChanged();
    }
}
