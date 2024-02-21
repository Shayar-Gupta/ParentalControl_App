package com.example.aninterface.dialogfragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aninterface.R;
import com.example.aninterface.interfaces.OnConfirmationListener;
import com.example.aninterface.utils.Constant;

public class ConfirmationDialogFragment extends DialogFragment {
    private Button btnConfirm;
    private Button btnCancelConfirm;
    private TextView txtConfirmationBody;
    private OnConfirmationListener onConfirmationListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Call requestWindowFeature() before inflating the layout
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.dialog_fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        onConfirmationListener = (OnConfirmationListener) getActivity();

        Bundle bundle = getArguments();
        String confirmationMessage = bundle.getString(Constant.CONFIRMATION_MESSAGE);

        txtConfirmationBody = view.findViewById(R.id.txtConfirmationBody);
        txtConfirmationBody.setText(confirmationMessage);

        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmationListener.onConfirm();
                dismiss();
            }
        });

        btnCancelConfirm = view.findViewById(R.id.btnCancelConfirm);
        btnCancelConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmationListener.onConfirmationCancel();
                dismiss();
            }
        });
    }
}