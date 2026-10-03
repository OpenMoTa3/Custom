package org.dev.custom.util;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.*;
import java.io.*;

public class DownloadUtils {
    public static boolean download(String url, String out) {
		HttpURLConnection conn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(10000);
			conn.setInstanceFollowRedirects(true); // 自动跟随重定向
			conn.addRequestProperty("User-Agent", "Mozilla/5.0");
			conn.connect();
			int code = conn.getResponseCode();
			if (code != 200) {
				return false;
			}
			is = conn.getInputStream();

			File f = new File(out);
			File parent = f.getParentFile();
			if (parent != null)
				parent.mkdirs();

			fos = new FileOutputStream(f);
			byte[] buffer = new byte[8192];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			return true;

		} catch (Exception e) {
            System.out.println(e);
			return false;
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ignored) {
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception ignored) {
			}
			if (conn != null)
				conn.disconnect();
		}
	}

	public static String checkLink(String[] url) {
		ExecutorService es = Executors.newFixedThreadPool(Math.min(url.length, 20));

		try {
			List<Future<String>> futures = new ArrayList<>();
			for (String link : url) {
				futures.add(es.submit(() -> isLinkOk(link)));
			}
			for (Future<String> f : futures) {
				try {
					String ok = f.get();
					if (ok != null)
						return ok;
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				} catch (ExecutionException e) {
				}
			}
			return null;
		} finally {
			es.shutdown();
		}
	}
	public static String isLinkOk(String link) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) new URL(link).openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setInstanceFollowRedirects(true);
			conn.addRequestProperty("User-Agent", "Mozilla/5.0");
			conn.connect();
			int code = conn.getResponseCode();
			return (code == 200) ? link : null;
		} catch (Exception e) {
			return null;
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}
}
