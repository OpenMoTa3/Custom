package org.dev.custom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.textview.MaterialTextView;
import java.io.File;
import java.nio.file.Files;
import org.dev.custom.R;
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.databinding.AdapterCardProjectBinding;
import org.dev.custom.util.FilePathUtils;
import org.dev.custom.util.FileUtils;

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
                        .inflate(R.layout.adapter_card_project, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterCardProjectBinding acpb = AdapterCardProjectBinding.bind(holder.itemView);
        acpb.textView.setText(project[position].getName());
        acpb.getRoot()
                .setOnClickListener(
                        (v) -> {
                            v.getContext()
                                    .startActivity(
                                            new Intent(v.getContext(), EditorActivity.class)
                                                    .putExtra(
                                                            "projectPath",
                                                            project[position].getAbsolutePath()));
                        });
        acpb.getRoot()
                .setOnLongClickListener(
                        (v) -> {
                            PopupMenu pm = new PopupMenu(v.getContext(), v);
                            pm.getMenu().add("删除项目");
                            pm.setOnMenuItemClickListener(
                                    (m) -> {
                                        if (m.getTitle().equals("删除项目")) {
                                            try {
                                                FileUtils.delete(project[position]);
                                            } catch (Exception err) {
                                                System.out.println(err);
                                                return false;
                                            }
                                            updateProject();
                                        }
                                        return true;
                                    });
                            pm.show();
                            return true;
                        });
    }

    public void updateProject() {
        project = FilePathUtils.projectStorage.listFiles();
        notifyDataSetChanged();
    }

    public class ProjectCard extends RecyclerView.ViewHolder {
        public ProjectCard(View v) {
            super(v);
        }
    }
}
