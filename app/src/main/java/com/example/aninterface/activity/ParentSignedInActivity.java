package com.example.aninterface.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.aninterface.R;
import com.example.aninterface.Adapter.ChildAdapter; // Add this import statement
import com.example.aninterface.dialogfragment.FloatingAddChildDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ParentSignedInActivity extends AppCompatActivity implements FloatingAddChildDialogFragment.AddChildDialogListener {

    private ImageButton btnSettings;
    private FloatingActionButton btnAddChild;
    private List<String> children = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChildAdapter adapter;


    @Override
    public void onChildAdded(String childName) {
        // Add the childName to your data source and notify the adapter
        children.add(childName);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signed_in);

        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChildAdapter(children); // Make sure ChildAdapter is defined and imported
        recyclerView.setAdapter(adapter);

        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentSignedInActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnAddChild = findViewById(R.id.btnAddChild);
        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an instance of the dialog fragment
                FloatingAddChildDialogFragment dialogFragment = new FloatingAddChildDialogFragment();

                // Show the dialog
                dialogFragment.show(getSupportFragmentManager(), "FloatingAddChildDialog");
            }
        });
    }
}
