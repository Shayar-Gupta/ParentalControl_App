package com.example.aninterface.fragments;

public interface OnAppClickListener {
    void onItemClick(String packageName, String appName, boolean blocked);

    /*void onLockCanceled();

    void onLockAppSet(int hours, int minutes);*/
}
