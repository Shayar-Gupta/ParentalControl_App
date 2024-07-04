package com.example.aninterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.aninterface.R;
import com.example.aninterface.dialogfragment.InformationDialogFragment;
import com.example.aninterface.dialogfragment.PasswordValidationDialogFragment;
import com.example.aninterface.dialogfragment.PermissionExplanationDialogFragment;
import com.example.aninterface.interfaces.OnPasswordValidationListener;
import com.example.aninterface.utils.Constant;
import com.example.aninterface.utils.SharedPrefsUtils;
import com.example.aninterface.utils.Validators;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChildSignedInActivity {

}