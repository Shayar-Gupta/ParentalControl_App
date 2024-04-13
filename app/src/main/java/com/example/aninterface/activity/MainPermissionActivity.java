package com.example.aninterface.activity;

//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.aninterface.R;
//import com.example.aninterface.dialogfragment.PermissionExplanationDialogFragment;
//
//public class MainPermissionActivity extends AppCompatActivity {
//
//
//    private AlertDialog infoDialog;
//
//    private TextView TitleTextView;
//    private TextView ExplanationTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_permission);
//
//        // Show the PermissionExplanationDialogFragment
//        PermissionExplanationDialogFragment dialogFragment = new PermissionExplanationDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "MainPermissionDialogFragment");
//
//
//            // Initialize the dialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            LayoutInflater inflater = getLayoutInflater();
//            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
//            builder.setView(dialogView);
//            infoDialog = builder.create();
//
//            // Initialize the TextView inside the dialog
//            ExplanationTextView = dialogView.findViewById(R.id.edtPermissionExplanations);
//            TitleTextView = dialogView.findViewById(R.id.edtPermissionName);
//
//            TextView edtxtSendSMS = findViewById(R.id.edtSendSMS);
//            TextView edtxtReadSMS = findViewById(R.id.edtReadSMS);
//            TextView edtxtReceiveSMS = findViewById(R.id.edtReceiveSMS);
//            TextView edtxtPhoneState = findViewById(R.id.edtPhoneState);
//            TextView edtxtReadCallLogs = findViewById(R.id.edtReadCallLogs);
//            TextView edtxtReadContacts = findViewById(R.id.edtReadContacts);
//            TextView edtxtWriteSettings = findViewById(R.id.edtWriteSettings);
//            TextView edtxtOverlayPermission = findViewById(R.id.edtOverlayPermission);
//            TextView edtxtPackageUsage = findViewById(R.id.edtPackageUsage);
//            TextView edtxtDeviceAdmin = findViewById(R.id.edtDeviceAdmin);
//            TextView edtxtLocationPermission = findViewById(R.id.edtLocationPermission);
//
//            edtxtSendSMS.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TitleTextView.setText(getString(R.string.send_sms_permission));
//                    ExplanationTextView.setText(getString(R.string.send_sms_explanation));
//                    infoDialog.show();
//                }
//            });
//
//            edtxtReadSMS.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TitleTextView.setText(getString(R.string.read_sms_permission));
//                    ExplanationTextView.setText(getString(R.string.read_sms_explanation));
//                    infoDialog.show();
//                }
//            });
//
//        edtxtReceiveSMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.receive_sms_permission));
//                ExplanationTextView.setText(getString(R.string.receive_sms_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtPhoneState.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.phone_state_permission));
//                ExplanationTextView.setText(getString(R.string.read_phone_state_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtReadCallLogs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.read_call_log_permission));
//                ExplanationTextView.setText(getString(R.string.read_call_log_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtReadContacts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.read_contacts_permission));
//                ExplanationTextView.setText(getString(R.string.read_contacts_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtWriteSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.write_settings_permission));
//                ExplanationTextView.setText(getString(R.string.write_settings_permission_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtOverlayPermission.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.overlay_permission));
//                ExplanationTextView.setText(getString(R.string.overlay_permission_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtPackageUsage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.package_usage_permission));
//                ExplanationTextView.setText(getString(R.string.package_usage_permission_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtDeviceAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.device_admin_permission));
//                ExplanationTextView.setText(getString(R.string.device_admin_explanation));
//                infoDialog.show();
//            }
//        });
//
//        edtxtLocationPermission.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TitleTextView.setText(getString(R.string.location_permission));
//                ExplanationTextView.setText(getString(R.string.location_permission_explanation));
//                infoDialog.show();
//            }
//        });
//    }
//}

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.aninterface.R;
import com.example.aninterface.dialogfragment.PermissionExplanationDialogFragment;

public class MainPermissionActivity extends AppCompatActivity {

    private AlertDialog infoDialog;
    private TextView TitleTextView;
    private TextView ExplanationTextView;
    private Switch switchSendSMSPermission;
    private Switch switchReadSMSPermission;
    private Switch switchReceiveSMSPermission;

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

        // Initialize switches
        switchSendSMSPermission = findViewById(R.id.switchSendSMSPermission);
        switchReadSMSPermission = findViewById(R.id.switchReadSMSPermission);
        switchReceiveSMSPermission = findViewById(R.id.switchReceiveSMSPermission);

        // Set onClickListener for each permission TextView
        setPermissionOnClickListener(R.id.edtSendSMS, R.string.send_sms_permission, R.string.send_sms_explanation, Manifest.permission.SEND_SMS);
        setPermissionOnClickListener(R.id.edtReadSMS, R.string.read_sms_permission, R.string.read_sms_explanation, Manifest.permission.READ_SMS);
        setPermissionOnClickListener(R.id.edtReceiveSMS, R.string.receive_sms_permission, R.string.receive_sms_explanation, Manifest.permission.RECEIVE_SMS);
        // Add more TextViews and permissions as needed

        // Set onClickListeners for each switch
        setSwitchOnClickListener(R.id.switchSendSMSPermission, Manifest.permission.SEND_SMS);
        setSwitchOnClickListener(R.id.switchReadSMSPermission, Manifest.permission.READ_SMS);
        setSwitchOnClickListener(R.id.switchReceiveSMSPermission, Manifest.permission.RECEIVE_SMS);
        // Add more switches and permissions as needed

        // Set OnCheckedChangeListener for each switch
        setSwitchOnCheckedChangeListener(R.id.switchSendSMSPermission, Manifest.permission.SEND_SMS);
        setSwitchOnCheckedChangeListener(R.id.switchReadSMSPermission, Manifest.permission.READ_SMS);
        setSwitchOnCheckedChangeListener(R.id.switchReceiveSMSPermission, Manifest.permission.RECEIVE_SMS);
        // Add more switches and permissions as needed
    }

    // Function to set OnClickListener for permission TextViews
    private void setPermissionOnClickListener(int textViewId, final int titleResId, final int explanationResId, final String permission) {
        TextView textView = findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show permission explanation dialog
                TitleTextView.setText(getString(titleResId));
                ExplanationTextView.setText(getString(explanationResId));
                infoDialog.show();
            }
        });
    }

    // Function to set OnCheckedChangeListener for switches
    private void setSwitchOnCheckedChangeListener(int switchId, final String permission) {
        final Switch permissionSwitch = findViewById(switchId);
        permissionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Permission switch is toggled on, check if permission is granted
                    if (ContextCompat.checkSelfPermission(MainPermissionActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted, request it
                        ActivityCompat.requestPermissions(MainPermissionActivity.this, new String[]{permission}, 1);
                    }
                }
            }
        });
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                // Reset the switch to off mode
                switchSendSMSPermission.setChecked(false);
                switchReadSMSPermission.setChecked(false);
                switchReceiveSMSPermission.setChecked(false);
                // Add more switches as needed
            }
        }
    }

    private void setSwitchOnClickListener(int switchId, final String permission) {
        final Switch permissionSwitch = findViewById(switchId);
        permissionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Permission switch is toggled on, check if permission is granted
                    if (ContextCompat.checkSelfPermission(MainPermissionActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted, request it
                        ActivityCompat.requestPermissions(MainPermissionActivity.this, new String[]{permission}, 1);
                    } else {
                        // Permission is already granted, disable the switch
                        permissionSwitch.setEnabled(false);
                    }
                } else {
                    // Switch is toggled off, enable it
                    permissionSwitch.setEnabled(true);
                }
            }
        });
    }


}

