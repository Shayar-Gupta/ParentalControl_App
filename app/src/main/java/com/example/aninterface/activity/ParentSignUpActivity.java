package com.example.aninterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aninterface.R;

public class ParentSignUpActivity extends AppCompatActivity {

    private EditText txtSignUpEmail;
    private EditText txtSignUpPassword;
    private EditText txtSignUpName;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signup);

        txtSignUpEmail = findViewById(R.id.txtSignUpEmail);
        txtSignUpPassword = findViewById(R.id.txtSignUpPassword);
        txtSignUpName = findViewById(R.id.txtSignUpName);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtSignUpEmail.getText().toString().trim();
                String password = txtSignUpPassword.getText().toString().trim();
                String name = txtSignUpName.getText().toString().trim();

                if (name.trim().isEmpty()) {
                    txtSignUpName.setError("Name cannot be blank");
                } else if (!name.matches("[a-zA-Z ]+")) {
                    txtSignUpName.setError("Name can only contain alphabets");
                }

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtSignUpEmail.setError("Enter a valid email address");
                }

                if (password.isEmpty()) {
                    txtSignUpPassword.setError("Enter a valid Password");
                }

                if (password.length() < 6 && password.length() != 0 ) {
                    txtSignUpPassword.setError("Password is too weak");
                }

                // If none of the fields have errors, you can start the LoginActivity here
                if (name.isEmpty() || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        || password.isEmpty() || password.length() < 6) {
                    return; // Don't proceed if there are errors
                }

                // Start LoginActivity
                startActivity(new Intent(ParentSignUpActivity.this, AccountVerificationActivity.class));
            }
        });

        TextView txtLoginHere = findViewById(R.id.txtloginIn);
        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentSignUpActivity.this, ParentLoginActivity.class));
            }
        });
    }
}

/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/activity_bg"
    android:focusableInTouchMode="true"
    android:gravity="center"
    android:layoutDirection="locale"
    android:orientation="vertical"
    tools:context="activity.SignUpActivity">

    <ScrollView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:fillViewport="true"
        tools:ignore="UselessParent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:orientation="vertical">

            <de.hdodenhof.circleimageview.CircleImageView
                android:id="@+id/imgProfile"
                android:layout_width="@dimen/circular_image_width_large"
                android:layout_height="@dimen/circular_image_height_large"
                android:src="@drawable/ic_profile_image"
                app:civ_border_color="@color/cloud"
                app:civ_border_width="@dimen/circular_image_border_large"
                app:civ_circle_background_color="@color/cloud" />


                <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="@dimen/padding_24dp">

                    <com.google.android.material.textfield.TextInputLayout
                        android:id="@+id/TextInputLayoutName"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        app:boxBackgroundColor="@color/darkest_blue"
                        app:boxBackgroundMode="outline"
                        app:endIconMode="clear_text"
                        android:textColorHint="@color/editText_Hint_Color"
                        app:endIconTint="@color/darkest_blue"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:startIconDrawable="@drawable/ic_person"
                        app:startIconTint="@color/darkest_blue">

                        <com.google.android.material.textfield.TextInputEditText
                            android:id="@+id/txtSignUpEmail"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:hint="Name"
                            android:inputType="textPersonName"
                            android:textColor="@color/darkest_blue"
                            android:textColorHint="@color/editText_Hint_Color" />
                    </com.google.android.material.textfield.TextInputLayout>

                    <com.google.android.material.textfield.TextInputLayout
                        android:id="@+id/TextInputLayoutEmail"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        app:boxBackgroundColor="@color/darkest_blue"
                        app:boxBackgroundMode="outline"
                        app:endIconMode="clear_text"
                        android:textColorHint="@color/editText_Hint_Color"
                        app:endIconTint="@color/darkest_blue"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:placeholderText="ex: 123@abc.com"
                        app:placeholderTextColor="@color/logo_dark_blue"
                        app:startIconDrawable="@drawable/ic_email"
                        app:startIconTint="@color/darkest_blue">

                        <com.google.android.material.textfield.TextInputEditText
                            android:id="@+id/txtSignUpEmail"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:hint="Email Address"
                            android:inputType="textEmailAddress"
                            android:textColor="@color/darkest_blue"
                            android:textColorHint="@color/editText_Hint_Color" />
                    </com.google.android.material.textfield.TextInputLayout>

                    <com.google.android.material.textfield.TextInputLayout
                        android:id="@+id/TextInputLayoutPassword"
                        android:layout_width="match_parent"
                        android:layout_height="67dp"
                        android:textColorHint="@color/editText_Hint_Color"
                        app:boxBackgroundColor="@color/red"
                        app:boxBackgroundMode="outline"
                        app:counterMaxLength="15"
                        app:endIconMode="password_toggle"
                        app:endIconTint="@color/darkest_blue"
                        app:layout_constraintEnd_toEndOf="parent"
                        app:layout_constraintStart_toStartOf="parent"
                        app:startIconDrawable="@drawable/change_password_33"
                        app:startIconTint="@color/darkest_blue">

                        <com.google.android.material.textfield.TextInputEditText
                            android:id="@+id/txtSignUpPassword"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:hint="Password"
                            android:inputType="textPassword"
                            android:textColor="@color/darkest_blue"
                            android:textColorHint="@color/editText_Hint_Color" />
                    </com.google.android.material.textfield.TextInputLayout>
            </LinearLayout>

            <Button
                android:id="@+id/btnSignUp"
                style="@style/OkButton"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginStart="@dimen/margin_32dp"
                android:layout_marginTop="@dimen/margin_8dp"
                android:layout_marginEnd="@dimen/margin_32dp"
                android:layout_marginBottom="@dimen/margin_8dp"
                android:text="@string/sign_up" />

            <ProgressBar
                android:id="@+id/progressBar"
                style="?android:attr/progressBarStyle"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginBottom="20dp"
                android:visibility="gone" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="16dp"
                android:layout_marginStart="@dimen/margin_32dp"
                android:layout_marginEnd="@dimen/margin_32dp"
                android:gravity="center"
                android:orientation="horizontal">

                <View
                    android:layout_width="100dp"
                    android:layout_height="1dp"
                    android:layout_gravity="center"
                    android:layout_margin="@dimen/margin_8dp"
                    android:background="@color/darkest_blue" />

                <TextView
                    android:layout_width="68dp"
                    android:layout_height="22dp"
                    android:layout_gravity="center"
                    android:layout_margin="@dimen/margin_8dp"
                    android:gravity="center"
                    android:text="@string/or"
                    android:textColor="@color/darkest_blue"
                    />

                <View
                    android:layout_width="100dp"
                    android:layout_height="1dp"
                    android:layout_gravity="center"
                    android:layout_margin="@dimen/margin_8dp"
                    android:background="@color/darkest_blue" />
            </LinearLayout>

            <Button
                android:id="@+id/btnSignUpWithGoogle"
                style="@style/OkButton"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginStart="@dimen/margin_32dp"
                android:layout_marginTop="@dimen/margin_8dp"
                android:layout_marginEnd="@dimen/margin_32dp"
                android:layout_marginBottom="@dimen/margin_8dp"
                android:text="@string/sign_up_with_google" />
            <ProgressBar
                android:id="@+id/progressBar"
                style="?android:attr/progressBarStyle"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginBottom="20dp"
                android:visibility="gone" />


            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="@dimen/margin_8dp"
                android:gravity="center"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="@string/login_here"
                    android:textColor="@color/black"
                    android:textSize="@dimen/text_size_16sp" />

                <TextView
                    android:id="@+id/txtloginIn"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="@dimen/margin_8dp"
                    android:text="@string/login_"
                    android:textColor="@color/button_color"
                    android:textSize="@dimen/text_size_16sp"
                    android:textStyle="bold" />
            </LinearLayout>
        </LinearLayout>
    </ScrollView>
</LinearLayout>
 */
