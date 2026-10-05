package org.dev.custom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import org.dev.custom.R;
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.adapter.FileAdapter;
import org.dev.custom.adapter.ProjectAdapter;
import org.dev.custom.databinding.FragmentFileBinding;
import org.dev.custom.databinding.FragmentHomeBinding;

public class FileFragment extends Fragment {

    private FragmentFileBinding ffb;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ffb = FragmentFileBinding.inflate(inflater, container, false);
        ffb.projectFile.setLayoutManager(new LinearLayoutManager(getActivity()));
        ffb.projectFile.setAdapter(new FileAdapter(new File(getActivity().getIntent().getStringExtra("projectPath")),(EditorActivity)getActivity()));
        return ffb.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ffb = null;
    }
}
