package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView textView;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        logout = findViewById(R.id.logoutApp);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        textView = findViewById(R.id.userDetails);
        if(user==null){
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }else{
            textView.setText(user.getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}