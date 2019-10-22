package com.example.hongchen.Adapter;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerPlaylistnhac extends FragmentPagerAdapter {

    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerPlaylistnhac(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentArrayList == null){
            return 0;
        }
        else {
            return fragmentArrayList.size();
        }
    }

    public void AddFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
    }
}
