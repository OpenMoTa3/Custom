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

public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    File[] projectFile;
    public FileAdapter() {
        
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public FileCard onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileCard(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_card_file, parent, false),parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    public class FileCard extends RecyclerView.ViewHolder {
        public FileCard(View v,Context c) {
            super(v);
            v.setOnClickListener((vv)->{
               // c.startActivity(new Intent(c,EditorActivity.class));
            });
        }
    }
}
