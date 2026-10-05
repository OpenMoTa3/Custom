package org.dev.custom.fragment;

import android.os.Bundle;
import android.app.Dialog;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import org.dev.custom.activity.MainActivity;
import org.dev.custom.adapter.ProjectAdapter;
import org.dev.custom.databinding.ActivitySettingsBinding;
import org.dev.custom.databinding.ActivityStartBinding;
import org.dev.custom.databinding.FragmentCreateProjectBinding;
import org.dev.custom.util.FilePathUtils;
import org.dev.custom.util.TemplateUtils;

public class CreateProjectFragment extends BottomSheetDialogFragment {
    FragmentCreateProjectBinding fcpb;
    ProjectAdapter pa;
    public CreateProjectFragment(ProjectAdapter pa) {
    	this.pa=pa;
    }
    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        fcpb = FragmentCreateProjectBinding.inflate(getLayoutInflater());
        fcpb.create.setOnClickListener((v)->{
            TemplateUtils.createTemplate(FilePathUtils.projectStorage,fcpb.name.getText().toString());
           pa.updateProject();
           
        });
        return fcpb.getRoot();
    }
}
