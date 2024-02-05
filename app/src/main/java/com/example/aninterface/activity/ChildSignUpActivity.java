package com.example.aninterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aninterface.R;

public class ChildSignUpActivity extends AppCompatActivity {

    private EditText txtChildSignUpEmail;
    private EditText txtChildSignUpPassword;
    private EditText txtChildSignUpName;
    private Button btnChildSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_signup);

        txtChildSignUpEmail = findViewById(R.id.txtChildSignUpEmail);
        txtChildSignUpPassword = findViewById(R.id.txtChildSignUpPassword);
        txtChildSignUpName = findViewById(R.id.txtChildSignUpName);
        btnChildSignUp = findViewById(R.id.btnChildSignUp);


        btnChildSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtChildSignUpEmail.getText().toString().trim();
                String password = txtChildSignUpPassword.getText().toString().trim();
                String name = txtChildSignUpName.getText().toString().trim();

                if (name.trim().isEmpty()) {
                    txtChildSignUpName.setError("Name cannot be blank");
                } else if (!name.matches("[a-zA-Z ]+")) {
                    txtChildSignUpName.setError("Name can only contain alphabets");
                }

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtChildSignUpEmail.setError("Enter a valid email address");
                }

                if (password.isEmpty()) {
                    txtChildSignUpPassword.setError("Enter a valid Password");
                }

                if (password.length() < 6 && password.length() != 0 ) {
                    txtChildSignUpPassword.setError("Password is too weak");
                }

                // If none of the fields have errors, you can start the LoginActivity here
                if (name.isEmpty() || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        || password.isEmpty() || password.length() < 6) {
                    return; // Don't proceed if there are errors
                }

                // Start LoginActivity
                startActivity(new Intent(ChildSignUpActivity.this, AccountVerificationActivity.class));
            }
        });

        TextView txtLoginHere = findViewById(R.id.txtChildloginIn);
        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChildSignUpActivity.this, ChildLoginActivity.class));
            }
        });
    }
}