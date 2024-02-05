package com.example.aninterface.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aninterface.R;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private List<String> children;

    public ChildAdapter(List<String> children) {
        this.children = children;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String child = children.get(position);
        holder.childName.setText(child);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView childName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            childName = itemView.findViewById(R.id.child_name);
        }
    }
}
