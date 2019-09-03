package com.iaowoo.mobile.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(下载图片到本地)
 * @date 2018/6/26
 */
public class HttpImgThread {
    private Bitmap mBitmap;
    private String mFileName;
    public  final static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/download_test/";
    /**
     * 微信头像保存名
     */
    public static  String webchatUrl="wechatIcon.jpg";
    private UpdateCallBack updateCallBack;
    private String url_d;
    public HttpImgThread(String url){
        this.url_d=url;
    }

    public void startDownload(UpdateCallBack updateCallBack) {
        this.updateCallBack=updateCallBack;
        new Thread(connectNet).start();
    }

    /**
     * Get image from newwork
     * @param path The path of image
     * @return byte[]
     * @throws Exception
     */
    public byte[] getImage(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            return readStream(inStream);
        }
        return null;
    }
    /**
     * Get image from newwork
     * @param path The path of image
     * @return InputStream
     * @throws Exception
     */
    public InputStream getImageStream(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            return conn.getInputStream();
        }
        return null;
    }
    /**
     * Get data from stream
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /*
     * 连接网络
     * 由于在4.0中不允许在主线程中访问网络，所以需要在子线程中访问
     */
    private Runnable connectNet = new Runnable(){
        @Override
        public void run() {
            try {
                String filePath = url_d;
                mFileName = "wechatIcon.jpg";

                //以下是取得图片的两种方法
                //////////////// 方法1：取得的是byte数组, 从byte数组生成bitmap
                byte[] data = getImage(filePath);
                if(data!=null){
                    mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
                }else{
                    ToastUtilsAll.getInstance().showShortToast("Image error!");
                }
                ////////////////////////////////////////////////////////

                //******** 方法2：取得的是InputStream，直接从InputStream生成bitmap ***********/
//                mBitmap = BitmapFactory.decodeStream(getImageStream(filePath));
                //********************************************************************/

                // 发送消息，通知handler在主线程中更新UI
                connectHanlder.sendEmptyMessage(0);
                LogPrint.printError("set image ...");
            } catch (Exception e) {
                ToastUtilsAll.getInstance().showShortToast("无法链接网络！");
                e.printStackTrace();
            }

        }

    };

    private Handler connectHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 更新UI，显示图片
            if (mBitmap != null) {
                LogPrint.printError("开始下载");
                updateCallBack.update(mBitmap);
            }
        }
    };

    public interface  UpdateCallBack{
        void update(Bitmap url);
    }

}
