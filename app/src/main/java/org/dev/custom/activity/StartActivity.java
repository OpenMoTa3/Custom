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
import android.view.View;
import android.webkit.DownloadListener;
import java.io.File;
import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.dev.custom.databinding.ActivityStartBinding;
import org.dev.custom.util.DownloadUtils;
import org.dev.custom.util.FilePathUtils;
import org.dev.custom.util.FileUtils;
import org.dev.custom.util.PathUtils;

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
        asb.notify.setOnClickListener(
                (v) -> {
                    openNSettings(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                });

        asb.storage.setOnClickListener(
                (v) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        openSettings(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                        requestStorage();
                    } else {
                        requestStorage();
                    }
                });

        asb.apkInstall.setOnClickListener(
                (v) -> {
                    openSettings(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                });
        asb.install.setOnClickListener(
                (v) -> {
                    asb.process.setVisibility(View.VISIBLE);
                    DownloadUtils.setProcessListener(
                            (current, totul) -> {
                                runOnUiThread(
                                        () -> {
                                            asb.process.setMax(totul);
                                            asb.process.setProgress(current);
                                        });
                            });
                    ExecutorService es = Executors.newFixedThreadPool(8);
                    es.execute(
                            () -> {
                                if (DownloadUtils.download(
                                        PathUtils.dataURL, PathUtils.packageData)) {
                                    if (FileUtils.unCompress(
                                            FilePathUtils.packageData, FilePathUtils.data)) {
                                                FilePathUtils.packageData.delete();
                                        try {
                                            FilePathUtils.isInstall.createNewFile();
                                        } catch (Exception err) {
                                        }
                                        DownloadUtils.setProcessListener(null);
                                        intoMain();
                                    }
                                }
                            });
                    es.shutdown();
                });
        intoMain();
    }

    public void intoMain() {
        if (checkPermission() == true & isInstall() == true) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public boolean isInstall() {
        return FilePathUtils.isInstall.exists();
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        boolean storage;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            storage = Environment.isExternalStorageManager();
        } else {
            storage =
                    checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_GRANTED
                            && checkSelfPermission(
                                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_GRANTED;
        }
        return storage && getPackageManager().canRequestPackageInstalls();
    }

    public void openSettings(String a) {
        startActivity(new Intent(a).setData(Uri.parse("package:" + getPackageName())));
    }

    public void openNSettings(String a) {
        startActivity(new Intent(a).putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName()));
    }

    public void requestStorage() {
        requestPermissions(
                new String[] {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                101);
    }
}
