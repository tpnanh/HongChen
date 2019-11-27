package com.example.hongchen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hongchen.Activity.DanhsachostActivity;
import com.example.hongchen.Adapter.OstAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Ost;
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

public class Fragment_Ost extends Fragment {
    View view;
    RecyclerView recyclerviewost;
    TextView textviewost;
    OstAdapter ostAdapter;
    ArrayList<Ost> ostArrayList;

    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ost,container,false);

        dialog = new Dialog(getActivity());
        dialog.show();

        recyclerviewost = view.findViewById(R.id.listviewost);
        textviewost = view.findViewById(R.id.textviewost);
        getData();
        textviewost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachostActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Ost>> callback = dataservice.GetOst();
        callback.enqueue(new Callback<List<Ost>>() {
            @Override
            public void onResponse(Call<List<Ost>> call, Response<List<Ost>> response) {
                ostArrayList = (ArrayList<Ost>) response.body();
                ostAdapter = new OstAdapter(getActivity(),ostArrayList);
                recyclerviewost.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerviewost.setAdapter(ostAdapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Ost>> call, Throwable t) {

            }
        });
    }
}
