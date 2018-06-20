package com.luyunfeng.outsource.slotwin.utils;

import android.os.Environment;

import com.luyunfeng.outsource.slotwin.MyApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtils {

    //数据存放路径
    //Environment.getExternalStorageDir() 在用户移除SD卡或者根本没有SD卡(模拟器)时得不到文件路径,
    //导致文件读写失败,并和文件无权限访问冲突,向用户提示错误信息。
    //故依然写在手机内部储存当中。
    public final static String DATA_DIR
            = MyApplication.getContext().getFilesDir().getAbsolutePath() + File.separator
            + "slotwin" + File.separator
            + "data" + File.separator;

    public static String readTextData(InputStream input) throws IOException {
        if (input == null){
            return "";
        }
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(reader, 12840);
        StringBuilder builder = new StringBuilder();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            builder.append(str).append('\n');
        }

        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }

        IOUtils.close(bufferedReader);

        return builder.toString();
    }

    public static File getImageCacheDir(String folder) {
        File file = new File(MyApplication.getContext().getExternalFilesDir(
                Environment.DIRECTORY_DCIM), folder);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void writeTextData(String directory, String fileName, String message) {

        if (message == null) return;

        String path = DATA_DIR + directory;

        File file = FileUtils.newFile(path, fileName);

        if (file == null) return;

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (Exception e) {

        } finally {
            IOUtils.close(fos);
        }
    }

    /**
     * 检查指定目录下的文件是否存在
     * @param directory {@link #DATA_DIR} 下的目录名
     * @param fileName 文件名
     * @return
     */
    public static boolean fileExist(String directory, String fileName) {
        String path = DATA_DIR + directory;
        File f = new File(path, fileName);
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

            }
        } else {
            return file;
        }
        return null;
    }

}
