package com.example.aninterface.dialogfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aninterface.R;

public class ChangePasswordDialogFragment extends DialogFragment {

    private EditText txtOldPassword;
    private EditText txtNewPassword;
    private EditText txtNewPasswordConfirmation;
    private Button btnChangePassword;
    private Button btnCancelChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_change_password, container, false);
        txtNewPassword = view.findViewById(R.id.txtNewPassword);
        txtNewPasswordConfirmation = view.findViewById(R.id.txtNewPasswordConfirmation);
        txtOldPassword = view.findViewById(R.id.txtOldPassword);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnCancelChangePassword = view.findViewById(R.id.btnCancelChangePassword);
        btnCancelChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NewpasswordConfirmation = txtNewPasswordConfirmation.getText().toString().trim();
                String Newpassword = txtNewPassword.getText().toString().trim();
                String Oldpassword = txtOldPassword.getText().toString().trim();

                if (Newpassword.isEmpty() || Oldpassword.isEmpty() || NewpasswordConfirmation.isEmpty()) {
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return; // Return to prevent further execution
                }

                if (Newpassword.equals(Oldpassword)) {
                    txtNewPassword.setError("New password cannot be the same as old password");
                    return; // Return to prevent further execution
                }

                if (!Newpassword.equals(NewpasswordConfirmation)) {
                    txtNewPasswordConfirmation.setError("Password didn't match");
                    return; // Return to prevent further execution
                }

                // All conditions met, password can be changed
                Toast.makeText(getContext(), "Your password has been successfully changed", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}