package com.example.aninterface.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aninterface.R;

public class ParentLoginActivity extends AppCompatActivity {
    private EditText txtLogInEmail;
    private EditText txtLogInPassword;
    private Button btnLogin;
    private TextView txtSignUp;
    private Button btnGoogleSignUp;
    private TextView txtForgotPassword;
    private CheckBox checkBoxRememberMe;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentLoginActivity.this, ParentSignedInActivity.class));
            }
        });
    }
}