package org.dev.custom.util;

public class PathUtils {
    public static final String packageName = "org.dev.custom";
    public static final String data = "/data/user/0/" + packageName;
    public static final String isInstall = "/data/user/0/" + packageName + "/isInstall";
    public static final String dataURL =
            "https://github.com/OpenMoTa3/Custom/releases/download/Environment/Environment.tar.gz";
    public static final String packageData = data + "/Environment.tar.gz";
    public static final String storage = "/storage/emulated/0";
    public static final String storageData = "/storage/emulated/0/Custom";
    public static final String mavenStorage = storageData + "/Maven";
    public static final String projectStorage = storageData + "/Project";
}
