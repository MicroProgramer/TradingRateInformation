package com.tradingrateinformation;

import static com.tradingrateinformation.config.Database.GetCurrentUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tradingrateinformation.model.USER;

public class SignInScreen extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_ed);
        password = findViewById(R.id.password_ed);
        findViewById(R.id.sign_in_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if (e.isEmpty() || p.isEmpty()) {
                    Toast.makeText(SignInScreen.this, "All field required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (e.equals("admin") && p.equals("admin")) {
                    SharedPreferences.Editor preferences = getSharedPreferences("admin", MODE_PRIVATE).edit();
                    preferences.putString("admin", "admin");
                    preferences.apply();
                    startActivity(new Intent(SignInScreen.this, AdminPanel.class));
                    return;
                }
                mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = mAuth.getCurrentUser().getUid();
                            USER user = GetCurrentUser(id);
                            if (user.getCategory().equals("Yes")) {
                                startActivity(new Intent(SignInScreen.this, SellerPanel.class));
                                return;
                            }
                            startActivity(new Intent(SignInScreen.this, BuyerPanel.class));
                        }
                    }
                });
            }
        });
        findViewById(R.id.new_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInScreen.this, SignUpScreen.class));
            }
        });
    }

    @Override
    protected void onStart() {
        SharedPreferences preferences = getSharedPreferences("admin", MODE_PRIVATE);
        String category = preferences.getString("admin", null);
        if (mAuth.getCurrentUser() != null) {
            if (GetCurrentUser(mAuth.getCurrentUser().getUid()).getId().equals("Yes")) {
                startActivity(new Intent(SignInScreen.this, SellerPanel.class));
            } else if (GetCurrentUser(mAuth.getCurrentUser().getUid()).getId().equals("No")) {
                startActivity(new Intent(SignInScreen.this, BuyerPanel.class));
            }
        } else if (category != null) {
            startActivity(new Intent(SignInScreen.this, AdminPanel.class));
        }
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}