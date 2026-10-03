package org.dev.custom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.textview.MaterialTextView;
import java.io.File;
import org.dev.custom.R;
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.databinding.AdapterCardProjectBinding;
import org.dev.custom.util.FilePathUtils;

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    File[] project = FilePathUtils.projectStorage.listFiles();

    public ProjectAdapter() {
        FilePathUtils.projectStorage.mkdirs();
    }

    @Override
    public int getItemCount() {
        if (project != null) return project.length;
        return 0;
    }

    @Override
    public ProjectCard onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectCard(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_card_project, parent, false),
                parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterCardProjectBinding acpb = AdapterCardProjectBinding.bind(holder.itemView);
        acpb.textView.setText(project[position].getAbsolutePath());
        
    }

    public class ProjectCard extends RecyclerView.ViewHolder {
        public ProjectCard(View v, Context c) {
            super(v);
            v.setOnClickListener(
                    (vv) -> {
                        c.startActivity(new Intent(c, EditorActivity.class).putExtra("projectPath",((MaterialTextView)v.findViewById(R.id.text_view)).getText()));
                    });
        }
    }
}
