package org.dev.custom.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragment;
import org.dev.custom.R;
import org.dev.custom.databinding.ActivityMainBinding;
import org.dev.custom.databinding.ActivitySettingsBinding;
import org.dev.custom.fragment.RootSettingsFragment;
public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding asb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        asb=null;
    }
    public void init() {
    	asb = ActivitySettingsBinding.inflate(getLayoutInflater());
        setSupportActionBar(asb.toolbar);
        setContentView(asb.getRoot());
        getSupportFragmentManager().beginTransaction().add(R.id.content,new RootSettingsFragment(),"root").commitNow();
    }
}
