
package com.tradingrateinformation.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tradingrateinformation.R;
import com.tradingrateinformation.adapter.SectionAdapter;

public class Home extends Fragment {
    View layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_home, container, false);

        SectionAdapter adapter = new SectionAdapter(getActivity(),getChildFragmentManager());
        ViewPager viewPager = layout.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = layout.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return layout;
    }
}