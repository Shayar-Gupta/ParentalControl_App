package com.example.aninterface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aninterface.R;
import com.example.aninterface.fragments.OnAppClickListener;
import com.example.aninterface.models.App;
import com.example.aninterface.utils.BackgroundGenerator;

import java.util.ArrayList;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppAdapterViewHolder> {
    private static final String TAG = "AppAdapterTAG";
    private Context context;
    private ArrayList<App> apps;
    private OnAppClickListener onAppClickListener;

    public AppAdapter(Context context, ArrayList<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    public void setOnAppClickListener(OnAppClickListener onAppClickListener) {
        this.onAppClickListener = onAppClickListener;
    }

    @NonNull
    @Override
    public AppAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_item, viewGroup, false);
        return new AppAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdapterViewHolder appAdapterViewHolder, int i) {
        App app = apps.get(i);
        if (app != null) {
            appAdapterViewHolder.txtAppName.setText(app.getAppName());
            appAdapterViewHolder.switchAppState.setChecked(app.isBlocked());
            appAdapterViewHolder.txtAppBackground.setText(BackgroundGenerator.getFirstCharacters(app.getAppName()));
            appAdapterViewHolder.txtAppBackground.setBackground(BackgroundGenerator.getBackground(context));

        }
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class AppAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAppBackground;
        private TextView txtAppName;
        private Switch switchAppState;

        public AppAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAppBackground = itemView.findViewById(R.id.txtAppBackground);
            txtAppName = itemView.findViewById(R.id.txtAppName);
            switchAppState = itemView.findViewById(R.id.switchAppState);
            switchAppState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isPressed()) {
                        onAppClickListener.onItemClick(apps.get(getAdapterPosition()).getPackageName(), apps.get(getAdapterPosition()).getAppName(), isChecked); //changed from txtAppName.getText()
                        Log.i(TAG, "onCheckedChanged: packageName: " + apps.get(getAdapterPosition()).getPackageName());
                        Log.i(TAG, "onCheckedChanged: appName: " + apps.get(getAdapterPosition()).getAppName());
                    }

                }
            });
        }
    }

}