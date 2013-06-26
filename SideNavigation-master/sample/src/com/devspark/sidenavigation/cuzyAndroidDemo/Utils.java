package com.devspark.sidenavigation.cuzyAndroidDemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: apple
 * Date: 13-6-15
 * Time: 下午11:21
 * To change this template use File | Settings | File Templates.
 */
public class Utils  {
    private Utils(){

    }




    public static File appExternalDirPath(){
        File result = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            result = Environment.getExternalStorageDirectory();
        }
//        Log.d("CuzyAdSDK","appExternalDirPath:" + result.getAbsolutePath());
        return result;
    }
    public static String uniqueIdentifier(Context context){
//        return "android";


        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = ""
                    + android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(),
                    ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 图片资源缓存
     */
    private static Map<String, Bitmap> bitmapCache = new HashMap<String, Bitmap>();

    /**
     * 获取网落图片资源
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
        //先从缓存里找
        Bitmap bitmap = bitmapCache.get(url);
        if (bitmap != null) {
            return bitmap;
        }

        //从网络上下载
        URL myFileURL;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap != null) {
            bitmapCache.put(url, bitmap);
        }

        return bitmap;

    }




    public static int getWindowWith(Activity acitivty)
    {

        int screenWidth ;
        int screenHeight ;
        DisplayMetrics dm = new DisplayMetrics();
        dm = acitivty.getResources().getDisplayMetrics();
        // 获取屏幕密度（方法3）
        dm = new DisplayMetrics();
        acitivty.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;


        density  = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;


        int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）


        screenWidth  = (int)(dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）
        screenHeight = (int)(dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）
        return screenWidth;
    }
    public static float getDensity(Activity acitivity)
    {
        int screenWidth ;
        int screenHeight ;
        DisplayMetrics dm = new DisplayMetrics();
        dm = acitivity.getResources().getDisplayMetrics();
        // 获取屏幕密度（方法3）
        dm = new DisplayMetrics();
        acitivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density  = dm.density;
        return density;
    }
}
