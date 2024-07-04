package com.example.aninterface.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aninterface.R;
import com.example.aninterface.dialogfragment.ChangePasswordDialogFragment;
import com.example.aninterface.dialogfragment.ConfirmationDialogFragment;
import com.example.aninterface.dialogfragment.DeleteAccountDialogFragment;
import com.example.aninterface.interfaces.OnDeleteAccountListener;
//import com.example.aninterface.services.UserDeletionService;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity implements OnDeleteAccountListener {

    private static final String CONFIRMATION_FRAGMENT_TAG = "SettingsActivity.CONFIRMATION_FRAGMENT_TAG";
    private static final String ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG = "SettingsActivity.ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG";
    private static final String PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG = "SettingsActivity.PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG";

    private Button btnLogout;
    private Button btnChangePassword;
    private Button btnDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> {
            ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
            confirmationDialogFragment.show(getSupportFragmentManager(), CONFIRMATION_FRAGMENT_TAG);
        });

        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(view -> {
            ChangePasswordDialogFragment passwordChangingDialogFragment = new ChangePasswordDialogFragment();
            passwordChangingDialogFragment.setCancelable(false);
            passwordChangingDialogFragment.show(getSupportFragmentManager(), PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG);
        });

        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(view -> showDeleteAccountDialog());
    }

    private void showDeleteAccountDialog() {
        DeleteAccountDialogFragment accountDeleteDialogFragment = new DeleteAccountDialogFragment();
        accountDeleteDialogFragment.setCancelable(false);
        accountDeleteDialogFragment.setOnDeleteAccountListener(this);
        accountDeleteDialogFragment.show(getSupportFragmentManager(), ACCOUNT_DELETE_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void onDeleteAccount(String password) {
        // Verify password before deleting account
        verifyPasswordAndDeleteAccount(password);
        removeUserDataFromDatabase();
        exitApp();
    }

    private void verifyPasswordAndDeleteAccount(String password) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            // User is not authenticated, show an error
            Toast.makeText(this, "User is not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create credentials for re-authentication
        AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), password);

        // Re-authenticate user
        currentUser.reauthenticate(credential)
                .addOnSuccessListener(aVoid -> {
                    // If re-authentication is successful, delete the account
                    currentUser.delete()
                            .addOnSuccessListener(aVoid1 -> {
                                // Account deleted successfully
                                // Now remove user's data from the Realtime Database
                                removeUserDataFromDatabase();
                            })
                            .addOnFailureListener(e -> {
                                // Handle account deletion failure
                                Toast.makeText(SettingsActivity.this, "Failed to delete account: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    // Handle re-authentication failure
                    Toast.makeText(SettingsActivity.this, "Authentication failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void removeUserDataFromDatabase() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

            // List to store child UIDs associated with the current user's email
            List<String> childUIDs = new ArrayList<>();

            // Query to get child nodes whose parentEmail matches the email of the current user
            Query childQuery = usersRef.child("childs").orderByChild("parentEmail").equalTo(currentUser.getEmail());
            childQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        String childUID = childSnapshot.getKey(); // Get the UID of the child
                        if (childUID != null) {
                            childUIDs.add(childUID);
                        }
                        // Remove child node
                        childSnapshot.getRef().removeValue();
                    }

                    // Remove child user objects from authentication
//                    for (String childUID : childUIDs) {
//                        removeChildFromAuthentication(childUID);
//                    }

                    // Remove the parent node whose email matches the current user's email
                    Query parentQuery = usersRef.child("parents").orderByChild("email").equalTo(currentUser.getEmail());
                    parentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot parentSnapshot : dataSnapshot.getChildren()) {
                                parentSnapshot.getRef().removeValue()
                                        .addOnSuccessListener(aVoid -> {
                                            // Parent's data successfully removed
                                            // Also remove parent's email from authentication
                                            removeParentFromAuthentication(currentUser.getEmail());
                                        })
                                        .addOnFailureListener(e -> {
                                            // Handle failure to remove parent's node
                                            Toast.makeText(SettingsActivity.this, "Failed to remove parent's node: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle onCancelled
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle onCancelled
                }
            });
        }
    }


    private void removeParentFromAuthentication(String email) {
        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> signInMethods = Objects.requireNonNull(task.getResult()).getSignInMethods();
                        if (signInMethods != null && !signInMethods.isEmpty()) {
                            // The email is associated with an account, so delete the user
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, "") // Sign in to get the user
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            if (user != null) {
                                                user.delete()
                                                        .addOnSuccessListener(aVoid -> {
                                                            // User deleted successfully
                                                        })
                                                        .addOnFailureListener(e -> {
                                                            // Handle failure to delete user
                                                            Toast.makeText(SettingsActivity.this, "Failed to delete child from authentication: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        });
                                            }
                                        } else {
                                            // Handle failure to sign in
                                            Toast.makeText(SettingsActivity.this, "Failed to sign in to delete child from authentication", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        // Handle failure to fetch sign-in methods
                        Toast.makeText(SettingsActivity.this, "Failed to fetch sign-in methods for email: " + email, Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private void removeChildFromAuthentication(String uid) {
//        UserDeletionService deletionService = new UserDeletionService();
//        deletionService.deleteChildUser(uid);
//    }


    private void exitApp() {
        // Close all activities and exit the app
        finishAffinity();
    }
}
