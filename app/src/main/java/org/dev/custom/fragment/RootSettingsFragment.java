package org.dev.custom.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;
import org.dev.custom.R;

public class RootSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
       // super.onCreatePreferences(arg0, arg1);
        addPreferencesFromResource(R.xml.preference_root);
    }
}
