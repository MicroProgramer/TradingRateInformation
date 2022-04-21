package com.tradingrateinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tradingrateinformation.model.USER;

public class SignUpScreen extends AppCompatActivity {
    EditText name,email,password;
    RadioGroup radioGroup;
    RadioButton radioButton;
    FirebaseAuth fAuth;
    String category = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        fAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
        name  = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                category = radioButton.getText().toString();
            }
        });

        findViewById(R.id.sign_up_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String n = name.getText().toString();
                String p = password.getText().toString();
                if (e.isEmpty()|| n.isEmpty()||p.isEmpty()||category.isEmpty()){
                    Toast.makeText(SignUpScreen.this, "all field is required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String id = fAuth.getCurrentUser().getUid();
                            USER user = new USER(id,n,e,p,category);
                            reference.child("users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        if (category.equals("Yes")){
                                            startActivity(new Intent(SignUpScreen.this,SellerPanel.class));
                                        }else{
                                            startActivity(new Intent(SignUpScreen.this,BuyerPanel.class));
                                        }
                                    }else{
                                        Toast.makeText(SignUpScreen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUpScreen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}