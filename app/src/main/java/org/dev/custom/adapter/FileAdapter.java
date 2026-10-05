package org.dev.custom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.io.File;
import org.dev.custom.R;
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.databinding.AdapterCardFileBinding;

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    File[] projectPath;
    File root;
    EditorActivity ea;

    public FileAdapter(File pp, EditorActivity ea) {
        root = pp;
        this.ea = ea;
        projectPath = pp.listFiles();
    }

    @Override
    public int getItemCount() {
        if (projectPath != null) return projectPath.length + 1;
        return 1;
    }

    @Override
    public FileCard onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileCard(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_card_file, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterCardFileBinding acfb = AdapterCardFileBinding.bind(holder.itemView);
        File path = null;
        if (position == 0) {
            acfb.textView.setText("...");
        }
        if (position != 0) {
            path = projectPath[position - 1];
            acfb.textView.setText(path.getName());
        }
        acfb.getRoot()
                .setOnClickListener(
                        (v) -> {
                            if (position == 0) {
                                root = root.getParentFile();
                                projectPath = root.listFiles();
                                notifyDataSetChanged();
                            } else if (projectPath[position - 1].isDirectory()) {
                                root = projectPath[position - 1];
                                projectPath = root.listFiles();
                                notifyDataSetChanged();
                            } else {
                                ea.getOpenPagerAdapter().openFile(projectPath[position - 1]);
                            }
                            // c.startActivity(new Intent(c,EditorActivity.class));
                        });
    }

    public class FileCard extends RecyclerView.ViewHolder {
        public FileCard(View v) {
            super(v);
        }
    }
}
