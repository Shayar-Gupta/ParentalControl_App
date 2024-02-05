package com.example.aninterface.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aninterface.R;
import com.example.aninterface.activity.MainPermissionActivity;

public class ChildLoginActivity extends AppCompatActivity {
    private Button btnChildLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);

        btnChildLogin = findViewById(R.id.btnChildLogin);

        btnChildLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to MainPermissionActivity
                Intent intent = new Intent(ChildLoginActivity.this, MainPermissionActivity.class);
                startActivity(intent);
            }
        });
    }
}
