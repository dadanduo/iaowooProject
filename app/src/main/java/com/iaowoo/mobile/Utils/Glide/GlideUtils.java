package com.iaowoo.mobile.Utils.Glide;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.LogPrint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}gile图片加载)
 * @date 2018/8/7
 * @email ${18011009889@163.com}
 */
public class GlideUtils {
    public  void glides(Context context, String url, ImageView imageView){
        if(context!=null) {
                if (url.contains(".gif")) {
                    if (context != null) {

                        glidesGif(context, url, imageView);
                    }
                } else {
                    if (context != null) {
                        Glide.with(context)
                                .load(url)
                                .asBitmap()
                                .skipMemoryCache(false)
                                .placeholder(R.mipmap.square_placeholder_figure)
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .dontAnimate()
                                .into(imageView);
                    }
                }
            } else {
                LogPrint.printError("页面被销毁........");
            }
    }

    /**
     * 加载动态图
     * @param context
     * @param url
     * @param imageView
     */
    public  void glidesGif(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .asGif()
                .skipMemoryCache(false)
                .placeholder(R.mipmap.square_placeholder_figure)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public void glideRound(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .asBitmap()
                .skipMemoryCache(false)
                .placeholder(R.mipmap.square_placeholder_figure)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .transform(new GlideCircleTransform(context)).into(imageView);
    }
    /**
     * Glide 获得图片缓存路径
     */
    public  String getImagePath(Context context,String imgUrl) {
        String path = null;
        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(500,500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * 拷贝到指定路径
     * @param oldPath
     * @param newPath
     */
    public  void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
