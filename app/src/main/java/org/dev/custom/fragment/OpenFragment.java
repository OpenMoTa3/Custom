package org.dev.custom.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import org.dev.custom.databinding.FragmentOpenBinding;

public class OpenFragment extends Fragment {

    private FragmentOpenBinding fob;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fob = FragmentOpenBinding.inflate(inflater, container, false);
        fob.codeEditor.setColorScheme(new SchemeDarcula());
        return fob.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fob = null;
    }
}
