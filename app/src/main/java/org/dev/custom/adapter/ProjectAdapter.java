package org.dev.custom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import org.dev.custom.R;
import org.dev.custom.activity.EditorActivity;

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ProjectAdapter() {}

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public ProjectCard onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectCard(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_card_project, parent, false),parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    public class ProjectCard extends RecyclerView.ViewHolder {
        public ProjectCard(View v,Context c) {
            super(v);
            v.setOnClickListener((vv)->{
                c.startActivity(new Intent(c,EditorActivity.class));
            });
        }
    }
}
