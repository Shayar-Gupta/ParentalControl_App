package com.example.aninterface.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aninterface.R;

import java.util.ArrayList;
import java.util.List;

public class FloatingAddChildDialogFragment extends DialogFragment {

    private List<String> children = new ArrayList<>();
    private RecyclerView recyclerView;

    private AddChildDialogListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Ensure that the parent activity has implemented the interface
        try {
            listener = (AddChildDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddChildDialogListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_child, container, false);
        EditText edtNewChildName = view.findViewById(R.id.edtNewChildName);
        EditText edtNewChildEmail = view.findViewById(R.id.edtNewChildEmail);
        Button btnAddChild = view.findViewById(R.id.btnAddChild);
        Button btnCancelAddChild = view.findViewById(R.id.btnCancelAddChild);


        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String childName = edtNewChildName.getText().toString().trim();
                String childEmail = edtNewChildEmail.getText().toString().trim();

                if (!childName.isEmpty()) {
                    listener.onChildAdded(childName); // Send the child name to the activity
                    dismiss(); // Close the dialog
                }

                if (childName.trim().isEmpty()) {
                    edtNewChildName.setError("Name cannot be blank");
                } else if (!childName.matches("[a-zA-Z ]+")) {
                    edtNewChildName.setError("Name can only contain alphabets");
                }

                if (childEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(childEmail).matches()) {
                    edtNewChildEmail.setError("Enter a valid email address");
                }

            }
        });

        btnCancelAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Dismiss the dialog on cancel
            }
        });
        return view;
    }

    public interface AddChildDialogListener {
        void onChildAdded(String childName);
    }

}