package com.tradingrateinformation.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tradingrateinformation.R;
import com.tradingrateinformation.fragment_admin.Purchaser;
import com.tradingrateinformation.fragment_admin.Seller;

public class SectionAdapter extends FragmentPagerAdapter {
    String[] title;
    Fragment[] fragments;
    Context context;
    public SectionAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
        title = context.getResources().getStringArray(R.array.panel_title);
        fragments = new Fragment[]{
                new Seller(),
                new Purchaser()
        };
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
