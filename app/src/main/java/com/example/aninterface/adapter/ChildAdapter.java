package com.example.aninterface.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aninterface.R;
import com.example.aninterface.models.Child;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    private List<Child> children;

    public ChildAdapter(List<Child> children) {
        this.children = children;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false);
        return new ChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        Child child = children.get(position);
        holder.bind(child);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {

        private TextView txtChildName;
        private TextView txtChildEmail;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChildName = itemView.findViewById(R.id.txtChildName);
            txtChildEmail = itemView.findViewById(R.id.txtChildEmail);
        }

        public void bind(Child child) {
            txtChildName.setText(child.getName());
            txtChildEmail.setText(child.getEmail());
        }
    }
}

