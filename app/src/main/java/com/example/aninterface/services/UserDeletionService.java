//package com.example.aninterface.services;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.UserRecord;
//
//public class UserDeletionService {
//
//    public void deleteChildUser(String uid) {
//        try {
//            FirebaseAuth.getInstance().deleteUser(uid);
//            System.out.println("Successfully deleted user with UID: " + uid);
//        } catch (FirebaseAuthException e) {
//            System.out.println("Error deleting user with UID: " + uid + ", " + e.getMessage());
//        }
//    }
//}
