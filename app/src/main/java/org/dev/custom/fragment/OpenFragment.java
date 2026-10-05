package org.dev.custom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import java.io.File;
import org.dev.custom.databinding.FragmentOpenBinding;
import org.dev.custom.util.FileUtils;

public class OpenFragment extends Fragment {
    FragmentOpenBinding fob;
    File openFile;

    public OpenFragment(File p) {
        openFile = p;
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fob = FragmentOpenBinding.inflate(inflater, container, false);
        fob.codeEditor.setColorScheme(new SchemeDarcula());
        fob.codeEditor.setText(FileUtils.read(openFile));
        return fob.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fob = null;
    }

    public File getOpenFile() {
        return this.openFile;
    }
}
