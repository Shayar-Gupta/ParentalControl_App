package com.example.aninterface.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aninterface.R;
import com.example.aninterface.dialogfragment.PermissionExplanationDialogFragment;

public class MainPermissionActivity extends AppCompatActivity {


    private AlertDialog infoDialog;

    private TextView TitleTextView;
    private TextView ExplanationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_permission);

        // Show the PermissionExplanationDialogFragment
        PermissionExplanationDialogFragment dialogFragment = new PermissionExplanationDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "MainPermissionDialogFragment");


            // Initialize the dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
            builder.setView(dialogView);
            infoDialog = builder.create();

            // Initialize the TextView inside the dialog
            ExplanationTextView = dialogView.findViewById(R.id.edtPermissionExplanations);
            TitleTextView = dialogView.findViewById(R.id.edtPermissionName);

            TextView edtxtSendSMS = findViewById(R.id.edtSendSMS);
            TextView edtxtReadSMS = findViewById(R.id.edtReadSMS);
            TextView edtxtReceiveSMS = findViewById(R.id.edtReceiveSMS);
            TextView edtxtPhoneState = findViewById(R.id.edtPhoneState);
            TextView edtxtReadCallLogs = findViewById(R.id.edtReadCallLogs);
            TextView edtxtReadContacts = findViewById(R.id.edtReadContacts);
            TextView edtxtWriteSettings = findViewById(R.id.edtWriteSettings);
            TextView edtxtOverlayPermission = findViewById(R.id.edtOverlayPermission);
            TextView edtxtPackageUsage = findViewById(R.id.edtPackageUsage);
            TextView edtxtDeviceAdmin = findViewById(R.id.edtDeviceAdmin);
            TextView edtxtLocationPermission = findViewById(R.id.edtLocationPermission);

            edtxtSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TitleTextView.setText(getString(R.string.send_sms_permission));
                    ExplanationTextView.setText(getString(R.string.send_sms_explanation));
                    infoDialog.show();
                }
            });

            edtxtReadSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TitleTextView.setText(getString(R.string.read_sms_permission));
                    ExplanationTextView.setText(getString(R.string.read_sms_explanation));
                    infoDialog.show();
                }
            });

        edtxtReceiveSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.receive_sms_permission));
                ExplanationTextView.setText(getString(R.string.receive_sms_explanation));
                infoDialog.show();
            }
        });

        edtxtPhoneState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.phone_state_permission));
                ExplanationTextView.setText(getString(R.string.read_phone_state_explanation));
                infoDialog.show();
            }
        });

        edtxtReadCallLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.read_call_log_permission));
                ExplanationTextView.setText(getString(R.string.read_call_log_explanation));
                infoDialog.show();
            }
        });

        edtxtReadContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.read_contacts_permission));
                ExplanationTextView.setText(getString(R.string.read_contacts_explanation));
                infoDialog.show();
            }
        });

        edtxtWriteSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.write_settings_permission));
                ExplanationTextView.setText(getString(R.string.write_settings_permission_explanation));
                infoDialog.show();
            }
        });

        edtxtOverlayPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.overlay_permission));
                ExplanationTextView.setText(getString(R.string.overlay_permission_explanation));
                infoDialog.show();
            }
        });

        edtxtPackageUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.package_usage_permission));
                ExplanationTextView.setText(getString(R.string.package_usage_permission_explanation));
                infoDialog.show();
            }
        });

        edtxtDeviceAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.device_admin_permission));
                ExplanationTextView.setText(getString(R.string.device_admin_explanation));
                infoDialog.show();
            }
        });

        edtxtLocationPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleTextView.setText(getString(R.string.location_permission));
                ExplanationTextView.setText(getString(R.string.location_permission_explanation));
                infoDialog.show();
            }
        });
    }
}