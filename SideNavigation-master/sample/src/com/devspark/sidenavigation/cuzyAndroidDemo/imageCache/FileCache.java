package com.example.taotaokanAndroid.imageCache;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 13-6-20
 * Time: PM3:04
 * To change this template use File | Settings | File Templates.
 */

import java.io.File;
import java.text.DecimalFormat;

import android.content.Context;

public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {
        // 如果有SD卡则在SD卡中建一个LazyList的目录存放缓存的图片
        // 没有SD卡就放在系统的缓存目录中
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    "TaotaoKan");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url) {
        // 将url的hashCode作为缓存的文件名
        String filename = String.valueOf(url.hashCode());
        // Another possible solution
        // String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    public String getSize()
    {
        long size = 0;
        for (File file : cacheDir.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            }
            else
            {

            }

        }

        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (size < 1024)
        {
            fileSizeString = df.format((double) size) + "B";
        }
        else if (size < 1048576)
        {
            fileSizeString = df.format((double) size / 1024) + "K";
        }
        else if (size < 1073741824)
        {
            fileSizeString = df.format((double) size / 1048576) + "M";
        }
        else
        {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }

        return fileSizeString;
    }


}