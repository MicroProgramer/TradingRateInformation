package com.tradingrateinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.microprogramer.library.CircularImageView;
import com.tradingrateinformation.adapter.ProductAdapter;

public class BuyerPanel extends AppCompatActivity {
    RecyclerView recyclerView;
    CircularImageView buyer_top_dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_panel);

        buyer_top_dp = findViewById(R.id.buyer_top_dp);
        buyer_top_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyerPanel.this,ProfileScreen.class));
            }
        });

        recyclerView = findViewById(R.id.product_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}