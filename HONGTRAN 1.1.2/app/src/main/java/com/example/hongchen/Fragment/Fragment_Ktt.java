package com.example.hongchen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.hongchen.Activity.DanhsachkttActivity;
import com.example.hongchen.Adapter.KttAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Kichtruyenthanh;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Ktt extends Fragment {
    View view;
    RecyclerView recyclerviewktt;
    TextView textviewktt;
    KttAdapter kttAdapter;
    ArrayList<Kichtruyenthanh> kichtruyenthanhArrayList;

    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kichtruyenthanh, container, false);

        dialog = new Dialog(getActivity());
        dialog.show();

        kttAdapter = new KttAdapter(getContext(), new ArrayList<Kichtruyenthanh>());
        recyclerviewktt = view.findViewById(R.id.listviewktt);
        textviewktt = view.findViewById(R.id.textviewktt);
        getData();
        textviewktt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachkttActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Kichtruyenthanh>> callback = dataservice.GetKichtruyenthanh();
        callback.enqueue(new Callback<List<Kichtruyenthanh>>() {
            @Override
            public void onResponse(Call<List<Kichtruyenthanh>> call, Response<List<Kichtruyenthanh>> response) {
                kichtruyenthanhArrayList = (ArrayList<Kichtruyenthanh>) response.body();
                kttAdapter.setData(kichtruyenthanhArrayList);
                recyclerviewktt.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerviewktt.setAdapter(kttAdapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Kichtruyenthanh>> call, Throwable t) {

            }
        });
    }
}
