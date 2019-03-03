package com.example.wireless;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager f) {

        super(f);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0: return FirstFragment.newInstance(null, null);
            case 1: return SecondFragment.newInstance(null, null);
            case 2: return ThirdFragment.newInstance(null, null);

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
