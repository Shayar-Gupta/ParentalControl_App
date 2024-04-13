package com.example.aninterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aninterface.R;
import com.example.aninterface.dialogfragment.LoadingDialogFragment;
import com.example.aninterface.interfaces.OnConfirmationListener;
import com.example.aninterface.interfaces.OnGoogleChildSignUp;
import com.example.aninterface.models.Child;
import com.example.aninterface.models.Parent;
import com.example.aninterface.utils.Constant;
import com.example.aninterface.utils.Validators;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity implements OnConfirmationListener, OnGoogleChildSignUp {
    private static final String TAG = "SignUpActivity";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private EditText txtSignUpEmail;
    private EditText txtParentEmail;
    private EditText txtSignUpPassword;
    private EditText txtSignUpName;
    private Button btnSignUp;
    private ImageView imgProfile;
    private String uid;
    private boolean parent = true;
    private boolean validParent = false;
    public String email;
    public String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent = getIntent();
        parent = intent.getBooleanExtra(Constant.PARENT_SIGN_UP, true);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        txtSignUpEmail = findViewById(R.id.txtSignUpEmail);
        txtParentEmail = findViewById(R.id.txtParentEmail);
        imgProfile = findViewById(R.id.imgProfile);
        txtParentEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Query query = databaseReference.child("parents").orderByChild("email").equalTo(txtParentEmail.getText().toString().toLowerCase());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        validParent = dataSnapshot.exists();
                        Log.i(TAG, "onDataChange: " + validParent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        if (!parent) {
            txtParentEmail.setVisibility(View.VISIBLE);
            imgProfile.setImageResource(R.drawable.ideogram_child);
        }

        txtSignUpPassword = findViewById(R.id.txtSignUpPassword);
        txtSignUpName = findViewById(R.id.txtSignUpName);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(v -> {
            email = txtSignUpEmail.getText().toString().toLowerCase().trim();
            password = txtSignUpPassword.getText().toString();
            signUp(email, password);
        });
    }

    private void signUp(String email, String password) {
        if (isValid()) {
            final LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
            startLoadingFragment(loadingDialogFragment);
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    stopLoadingFragment(loadingDialogFragment);
                    if (task.isSuccessful()) {
                        signUpRoutine(txtParentEmail.getText().toString().toLowerCase());
                        Toast.makeText(SignUpActivity.this, "Jaa Login Kar", Toast.LENGTH_SHORT).show();
                    } else {
                        String errorCode = task.getException().getMessage();
                        switch (errorCode) {
                            case "ERROR_INVALID_EMAIL":
                                txtSignUpEmail.setError(getString(R.string.enter_valid_email));
                                break;
                            case "ERROR_EMAIL_ALREADY_IN_USE":
                                txtSignUpEmail.setError(getString(R.string.email_is_already_in_use));
                                break;
                            case "ERROR_WEAK_PASSWORD":
                                txtSignUpPassword.setError(getString(R.string.weak_password));
                                break;
                            default:
                                Toast.makeText(SignUpActivity.this, getString(R.string.sign_up_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    private void signUpRoutine(String parentEmail) {
        uid = auth.getCurrentUser().getUid();
        addUserToDB(parentEmail, parent);
    }

    private void addUserToDB(String parentEmail, boolean parent) {
        String email;
        String name;
        email = txtSignUpEmail.getText().toString().toLowerCase();
        name = txtSignUpName.getText().toString().replaceAll("\\s+$", "");

        if (parent) {
            Parent p = new Parent(name, email);
            databaseReference.child("parents").child(uid).setValue(p);
        } else {
            Child c = new Child(name, email, parentEmail);
            databaseReference.child("childs").child(uid).setValue(c);
        }
    }

    private void startLoadingFragment(LoadingDialogFragment loadingDialogFragment) {
        loadingDialogFragment.setCancelable(false); // Show loading dialog
        loadingDialogFragment.show(getSupportFragmentManager(), "loading_dialog");
    }

    private void stopLoadingFragment(LoadingDialogFragment loadingDialogFragment) {
        // Dismiss loading dialog
        loadingDialogFragment.dismiss();
    }

    private boolean isValid() {
        if (!Validators.isValidName(txtSignUpName.getText().toString())) {
            txtSignUpName.setError(getString(R.string.name_validation));
            txtSignUpName.requestFocus();
            return false;
        }

        if (!Validators.isValidEmail(txtSignUpEmail.getText().toString())) {
            txtSignUpEmail.setError(getString(R.string.enter_valid_email));
            txtSignUpEmail.requestFocus();
            return false;
        }

        if (!parent) {
            if (!Validators.isValidEmail(txtParentEmail.getText().toString().toLowerCase()) || !validParent) {
                txtParentEmail.setError(getString(R.string.this_email_isnt_registered_as_parent));
                txtParentEmail.requestFocus();
                return false;
            }
        }

        if (!Validators.isValidPassword(txtSignUpPassword.getText().toString())) {
            txtSignUpPassword.setError(getString(R.string.enter_valid_password));
            txtSignUpPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onConfirm() {
        // Handle confirmation action
        Toast.makeText(this, getString(R.string.please_add_a_profile_image), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfirmationCancel() {
        // Handle cancellation action
        signUp(txtSignUpEmail.getText().toString().toLowerCase(), txtSignUpPassword.toString());
        //TODO:: default image here
        Log.i(TAG, "onConfirmationCancel: DONE");
    }

    @Override
    public void onModeSelected(String parentEmail) {
        // Handle mode selection
        signUpRoutine(parentEmail);
    }
}