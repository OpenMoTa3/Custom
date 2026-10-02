package org.dev.custom.fragment;

import android.os.Bundle;
import android.app.Dialog;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import org.dev.custom.databinding.ActivitySettingsBinding;
import org.dev.custom.databinding.ActivityStartBinding;
import org.dev.custom.databinding.FragmentCreateProjectBinding;

public class CreateProjectFragment extends BottomSheetDialogFragment {
    FragmentCreateProjectBinding fcpb;

    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        fcpb = FragmentCreateProjectBinding.inflate(getLayoutInflater());
        return fcpb.getRoot();
    }
}
