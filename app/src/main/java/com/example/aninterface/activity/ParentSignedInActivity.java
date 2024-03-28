package com.example.aninterface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.aninterface.R;
import com.example.aninterface.models.Child;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.aninterface.adapter.ChildAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParentSignedInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView txtParentName;
    private RecyclerView recyclerViewChilds;
    private ArrayList<Child> childs;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    private List<Child> children = new ArrayList<>();

    DatabaseReference mRef;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_parent_signed_in);

//            // Locate the toolbar
//            Toolbar toolbar = findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//
//            // Locate the btnSetting in the toolbar
//            ImageButton btnSetting = toolbar.findViewById(R.id.btnSetting);
//            btnSetting.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Start Intent to navigate to basic_setting_activity.xml
//                    Intent intent = new Intent(ParentSignedInActivity.this, SettingsActivity.class);
//                    startActivity(intent);
//                }
//            });

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance();
            mRef = mDatabase.getReference("users");

            // Get the currently logged-in user's email
            String parentEmail = mAuth.getCurrentUser().getEmail();

            // Query the database to find the child associated with the parent
            Query query = mRef.child("childs").orderByChild("parentEmail").equalTo(parentEmail);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String childName = childSnapshot.child("name").getValue(String.class);
                            String childEmail = childSnapshot.child("email").getValue(String.class);
                            Child child = new Child(childName, childEmail, "parent@gmail.com");
                            children.add(child);
                        // Show the child's email in a toast message
                        Toast.makeText(getApplicationContext(), "Child's Email: " + childEmail, Toast.LENGTH_SHORT).show();
                    }
                        // Populate the RecyclerView with child emails
                        RecyclerView recyclerView = findViewById(R.id.recyclerViewChilds);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ParentSignedInActivity.this));
                        recyclerView.setAdapter(new ChildAdapter(children));

                        // Set parent's name or email
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        String parentName = currentUser.getDisplayName();
                        if (parentName == null || parentName.isEmpty()) {
                            parentName = currentUser.getEmail();
                        }
                        TextView txtParentName = findViewById(R.id.txtParentName);
                        txtParentName.setText(parentName);
                    } else {
                        Toast.makeText(ParentSignedInActivity.this, "No child found for this parent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle any errors that may occur during the database query
                    Toast.makeText(ParentSignedInActivity.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



//package com.example.aninterface.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.aninterface.R;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class ParentSignedInActivity extends AppCompatActivity {
//
//    private FirebaseAuth auth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_parent_signed_in);
//
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference mRef = mDatabase.getReference("users");
//
//// Get the currently logged-in user's email
//        String parentEmail = mAuth.getCurrentUser().getEmail();
//
//// Query the database to find the child associated with the parent
//        Query query = mRef.child("childs").orderByChild("parentEmail").equalTo(parentEmail);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // Iterate through the children nodes to get the child's email
//                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                        String childEmail = childSnapshot.child("email").getValue(String.class);
//                        // Show the child's email in a toast message
//                        Toast.makeText(getApplicationContext(), "Child's Email: " + childEmail, Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // Handle the case where no child is found for the parent
//                    Toast.makeText(getApplicationContext(), "No child found for this parent", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle any errors that may occur during the database query
//                Toast.makeText(getApplicationContext(), "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//}
