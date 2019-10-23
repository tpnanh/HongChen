package com.example.hongchen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongchen.Adapter.ChudeAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.ChuDe;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe extends Fragment {
    View view;
    ListView listviewchude;
    TextView textviewchude;
    ChudeAdapter chudeAdapter;
    ArrayList<ChuDe> chuDeArrayList;

    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude, container, false);

        dialog = new Dialog(getActivity());
        dialog.show();

        listviewchude = view.findViewById(R.id.listviewpchude);
        textviewchude = view.findViewById(R.id.textviewtitlechude);
        getData();
        return view;
    }

    public void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetChude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDeArrayList = (ArrayList<ChuDe>) response.body();
                chudeAdapter = new ChudeAdapter(getContext(), android.R.layout.simple_list_item_1, chuDeArrayList);
                listviewchude.setAdapter(chudeAdapter);
                setListViewHeightBasedOnChildren(listviewchude);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    public int getItemCount(){
        if (chuDeArrayList == null){
            return 0;
        }
        else {
            return chuDeArrayList.size();
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
