package com.example.aninterface.dialogfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aninterface.R;
import com.example.aninterface.activity.ParentSignUpActivity;

public class DeleteAccountDialogFragment extends DialogFragment {

    private EditText txtDeleteAccountPassword;
    private Button btnDeleteAccount;
    private Button btnCancelDeleteAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_delete_account, container, false);
        txtDeleteAccountPassword = view.findViewById(R.id.txtDeleteAccountPassword);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnCancelDeleteAccount = view.findViewById(R.id.btnCancelDeleteAccount);
        btnCancelDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Oldpassword = txtDeleteAccountPassword.getText().toString().trim();

                if (Oldpassword.isEmpty()) {
                    txtDeleteAccountPassword.setError("This field cannot be blank");
                } else {
                    Toast.makeText(getContext(), "Your account has been successfully deleted!", Toast.LENGTH_SHORT).show();

                    // Launch SignUpActivity
                    Intent intent = new Intent(getContext(), ParentSignUpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}