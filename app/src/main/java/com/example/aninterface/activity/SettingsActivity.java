package com.example.aninterface.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aninterface.R;

import com.example.aninterface.dialogfragment.*;

public class SettingsActivity extends AppCompatActivity {

    private static final String CONFIRMATION_MESSAGE = "SettingsActivity.CONFIRMATION_MESSAGE";
    private static final String CONFIRMATION_FRAGMENT_TAG = "SettingsActivity.CONFIRMATION_FRAGMENT_TAG";
    private static final String ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG = "SettingsActivity.ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG";
    private static final String PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG = "SettingsActivity.PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG";
    private Button btnLogout;
    private Button btnChangePassword;
    private Button btnDeleteAccount;
    private Button btnAbout;
    private Button btnSendFeedBack;
    private ImageButton btnBack;
    private ImageButton btnSettings;
    private TextView txtTitle;
    private FrameLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_setting_activity);


        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> {
            ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
            confirmationDialogFragment.show(getSupportFragmentManager(), CONFIRMATION_FRAGMENT_TAG);
        });

        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(view -> {
            ChangePasswordDialogFragment passwordChangingDialogFragment = new ChangePasswordDialogFragment();
            passwordChangingDialogFragment.setCancelable(false);
            passwordChangingDialogFragment.show(getSupportFragmentManager(), PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG);
        });


        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(view -> {
            DeleteAccountDialogFragment accountDeleteDialogFragment = new DeleteAccountDialogFragment();
            accountDeleteDialogFragment.show(getSupportFragmentManager(), ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG);
        });
    }
}