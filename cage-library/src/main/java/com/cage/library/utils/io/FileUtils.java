package com.cage.library.utils.io;

import android.os.Environment;

import com.cage.library.CageLibrary;
import com.cage.library.infrastructure.log.Log;
import com.cage.library.infrastructure.text.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtils {

    public static String readTextData(String file) throws IOException {
        return readTextData(new FileInputStream(file));
    }

    public static String readTextData(InputStream input) throws IOException {
        if (input == null){
            return "";
        }
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            builder.append(str).append('\n');
        }

        StringUtils.deleteLastChar(builder);

        IOUtils.close(bufferedReader);

        return builder.toString();
    }


    public static void writeTextData(String directory, String fileName, String message) {

        if (message == null) {
            return;
        }

        File file = newFile(directory, fileName);

        if (file == null) {
            return;
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (Exception e) {
            Log.printStackTrace(e);
        } finally {
            IOUtils.close(fos);
        }
    }

    public static File getImageCacheDir(String folder) {
        File file = new File(CageLibrary.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DCIM), folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean fileExist(String directory, String fileName) {
        File f = new File(directory, fileName);
        return f.exists();
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        IOUtils.close(in);
        IOUtils.close(out);
    }

    /**
     * @param path 目录
     * @return 方法执行完毕时是否已存在目录
     */
    public static boolean newDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            return dir.mkdirs();
        } else {
            return true;
        }
    }

    public static File newFile(String path, String name) {

        if (!newDir(path)) {
            return null;
        }

        File file = new File(path, name);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    return file;
                } else {
                    return null;
                }
            } catch (IOException e) {
                Log.printStackTrace(e);
            }
        } else {
            return file;
        }
        return null;
    }

}
