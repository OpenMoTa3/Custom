package org.dev.custom.build;

import java.io.File;
import org.dev.custom.util.FilePathUtils;
import org.dev.custom.util.PathUtils;

public class CommadUtils {
    ProcessBuilder pb = new ProcessBuilder();

    public CommadUtils() {
        pb.environment().clear();
        pb.directory(new File(PathUtils.JREData, "/bin"));
        pb.environment()
                .put("PATH", PathUtils.JREData + "/bin");
        pb.environment().put("LD_LIBRARY_PATH", PathUtils.JREData + "/lib");
    }

    public int runCommand(String[] s) {
        try {
            Process p=pb.command(s).start();
            System.out.println(new String(p.getInputStream().readAllBytes(),"utf-8"));
            System.out.println(new String(p.getErrorStream().readAllBytes(),"utf-8"));
            System.out.println(p.waitFor());
            
            return 0;
        } catch (Exception err) {
            System.out.println(err);
            return -1;
        }
    }
}
