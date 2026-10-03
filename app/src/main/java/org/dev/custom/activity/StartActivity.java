package org.dev.custom.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import org.dev.custom.databinding.ActivityStartBinding;

public class StartActivity extends Activity {
    ActivityStartBinding asb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        asb = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(asb.getRoot());
        startActivity(new Intent(this, MainActivity.class));
        asb.notify.setOnClickListener((v) -> {});

        asb.storage.setOnClickListener((v) -> {});

        asb.apkInstall.setOnClickListener(
                (v) -> {
                    startActivity(
                            new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                                    .setData(Uri.parse("package:" + getPackageName())));
                });
        asb.install.setOnClickListener((v) -> {});
    }
}
