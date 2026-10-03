package org.dev.custom.util;

import java.io.File;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class FileUtils {
    public static boolean read() {
        return false;
    }

    public static boolean write() {
        return false;
    }

    public static boolean reName() {
        return false;
    }

    public static boolean unCompress(File f, File out) {
        try {
            TarArchiveInputStream tais =
                    new TarArchiveInputStream(
                            new GzipCompressorInputStream(
                                    new BufferedInputStream(new FileInputStream(f))));
            TarArchiveEntry entry;
            while ((entry = tais.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(out.getAbsolutePath(), entry.getName()).mkdirs();
                } else {
                    File outFile = new File(out.getAbsolutePath(), entry.getName());
                    outFile.getParentFile().mkdirs();
                    try (OutputStream os =
                            new BufferedOutputStream(new FileOutputStream(outFile))) {
                        byte[] buffer = new byte[8192];
                        int len;
                        while ((len = tais.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            return false;
        }
        return true;
    }
}
