package com.tradingrateinformation.config;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tradingrateinformation.model.USER;

import java.util.ArrayList;

public class Database {
    public static DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
    public static ArrayList<USER> userArrayList;

    public static void GetData(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userArrayList.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    USER user = userSnapshot.getValue(USER.class);
                    userArrayList.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static USER GetCurrentUser(String id){
        for (USER user: userArrayList) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
