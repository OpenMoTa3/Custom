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
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.adapter.ProjectAdapter;
import org.dev.custom.databinding.FragmentHomeBinding;
import org.dev.custom.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fhb;
    CreateProjectFragment cpf;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fhb = FragmentHomeBinding.inflate(inflater, container, false);
        cpf = new CreateProjectFragment();
        fhb.projectHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        fhb.projectHome.setAdapter(new ProjectAdapter());
        fhb.fab.setOnClickListener(
                (v) -> {
                    cpf.show(getActivity().getSupportFragmentManager(), "dialog");
                });
        return fhb.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fhb = null;
    }
}
