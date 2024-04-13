package com.example.aninterface.dialogfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.aninterface.R;
import com.example.aninterface.interfaces.OnDeleteAccountListener;
import com.example.aninterface.utils.Constant;
import com.example.aninterface.utils.SharedPrefsUtils;
import com.example.aninterface.utils.Validators;

public class DeleteAccountDialogFragment extends DialogFragment {

    private EditText txtDeleteAccountPassword;
    private Button btnDeleteAccount;
    private Button btnCancelDeleteAccount;
    private OnDeleteAccountListener onDeleteAccountListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_delete_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        onDeleteAccountListener = (OnDeleteAccountListener) getActivity();

        txtDeleteAccountPassword = view.findViewById(R.id.txtDeleteAccountPassword);
        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    onDeleteAccountListener.onDeleteAccount(txtDeleteAccountPassword.getText().toString());
                    dismiss();
                }
            }
        });

        btnCancelDeleteAccount = view.findViewById(R.id.btnCancelDeleteAccount);
        btnCancelDeleteAccount.setOnClickListener(view1 -> dismiss());
    }

    private boolean isValid() {
        if (!Validators.isValidPassword(txtDeleteAccountPassword.getText().toString())) {
            txtDeleteAccountPassword.setError(getString(R.string.wrong_password));
            txtDeleteAccountPassword.requestFocus();
            return false;
        }

        if (!txtDeleteAccountPassword.getText().toString().equals(SharedPrefsUtils.getStringPreference(getContext(), Constant.PASSWORD, ""))) {
            txtDeleteAccountPassword.setError(getString(R.string.wrong_password));
            txtDeleteAccountPassword.requestFocus();
            return false;
        }

        return true;
    }

    public void setOnDeleteAccountListener(OnDeleteAccountListener listener) {
        this.onDeleteAccountListener = listener;
    }
}
