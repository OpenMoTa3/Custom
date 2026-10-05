package org.dev.custom.util;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class TemplateUtils {
    public static boolean createTemplate(File path, String projectName) {
        try {
            new File(path,projectName+"/app").mkdirs();
            Yaml y = new Yaml();
            Map<String, Object> settings = new HashMap();
            settings.put("projectName", projectName);
            settings.put("maven", new String[] {"https://repo1.maven.org/maven2/","https://dl.google.com/dl/android/maven2/","https://jcenter.bintray.com/","https://maven.aliyun.com/repository/public/"});
            settings.put("include", new String[] {":app"});
            y.dump(settings, new FileWriter(new File(path, projectName + "/settings.yaml")));
            Map<String, Object> build = new HashMap();
            build.put("type", "android");
            build.put("android", new String[] {"", ""});
            build.put("a", new String[] {":app"});
            y.dump(build, new FileWriter(new File(path, projectName + "/app/build.yaml")));

        } catch (Exception err) {
            System.out.println(err);
            return false;
        }
        return true;
    }
}
