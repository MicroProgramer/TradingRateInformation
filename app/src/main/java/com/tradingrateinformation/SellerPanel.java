package com.tradingrateinformation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tradingrateinformation.fragment_seller.NewOrder;
import com.tradingrateinformation.fragment_seller.Purchase;
import com.tradingrateinformation.fragment_seller.SellerHistory;

public class SellerPanel extends AppCompatActivity {
    BottomNavigationView nv;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saller_panel);

        toolbar = findViewById(R.id.toolbar_seller);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seller");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.seller_fl, new NewOrder());
        transaction.commit();

        nv = findViewById(R.id.seller_nv);
        nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (id) {
                    case R.id.home:
                        transaction.replace(R.id.seller_fl, new NewOrder());
                        break;
                    case R.id.purchasing:
                        transaction.replace(R.id.seller_fl, new Purchase());
                        break;
                    case R.id.history:
                        transaction.replace(R.id.seller_fl, new SellerHistory());
                        break;
                }
                transaction.commit();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}