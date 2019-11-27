package com.example.hongchen.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hongchen.Adapter.BannerAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Quangcao;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.show();

        view = inflater.inflate(R.layout.fragment_banner, container, false);
        anhxa();
        getData();
        return view;//chua duoc luon
    }

    public void getData() {
        bannerAdapter = new BannerAdapter(getContext(), new ArrayList<Quangcao>());
        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);

        Dataservice dataservice = APIService.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter.setData(banners);//crash roi

                dialog.dismiss();

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= (viewPager.getAdapter()).getCount()) {
                            currentItem = 0;
                        }

                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 10000);
                    }
                };

                handler.postDelayed(runnable, 10000); // delay chi vay


            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }

    public void anhxa() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }
}
