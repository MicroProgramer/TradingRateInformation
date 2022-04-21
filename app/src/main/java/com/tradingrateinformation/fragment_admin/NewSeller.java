package com.tradingrateinformation.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tradingrateinformation.R;
import com.tradingrateinformation.adapter.NewSellersAdapter;

public class NewSeller extends Fragment {
    View layout;
    RecyclerView new_seller_rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_new_seller, container, false);

        NewSellersAdapter adapter = new NewSellersAdapter();
        new_seller_rv = layout.findViewById(R.id.new_seller_rv);
        new_seller_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        new_seller_rv.setAdapter(adapter);

        return layout;
    }
}