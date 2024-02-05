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
import android.widget.TextView;
import android.widget.Toast;

import com.example.aninterface.R;
import com.example.aninterface.activity.ParentLoginActivity;

public class ConfirmationDialogFragment extends DialogFragment {

    private static final String CONFIRMATION_MESSAGE = "ConfirmationDialogFragment.CONFIRMATION_MESSAGE";
    private Button btnConfirm;
    private Button btnCancelConfirm;
    private TextView txtConfirmationBody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You have been logged out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ParentLoginActivity.class);
                startActivity(intent);
            }

        });

        btnCancelConfirm = view.findViewById(R.id.btnCancelConfirm);
        btnCancelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}