package com.iaowoo.mobile.Weex;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by chenda weex ImagerAdapter
 */
public class ImageAdapter implements IWXImgLoaderAdapter {
  public ImageAdapter(){}
  @Override
  public void setImage(final String url, final ImageView view, WXImageQuality quality, final WXImageStrategy strategy) {
    //实现你自己的图片下载。
    if(TextUtils.isEmpty(url)) return;
    WXSDKManager.getInstance().postOnUiThread(new Runnable() {
      @Override
      public void run() {
        if(view==null||view.getLayoutParams()==null){
          return;
        }
        String temp = url;
        LogPrint.printError("图片路径啦啦啦"+temp);
        if (url.startsWith("//")) {
          temp = "http:" + url;
        }
        if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
          return;
        }
        if(temp.contains(".gif")){
          Glide.with(WXEnvironment.getApplication())
                  .load(temp)
                  .asGif()
                  .skipMemoryCache(false)
//                  .placeholder(R.mipmap.square_placeholder_figure)
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .dontAnimate()
                  .into(view);
        }else{
          Glide.with(WXEnvironment.getApplication())
                  .load(temp)
                  .asBitmap()
                  .skipMemoryCache(false)
//                  .placeholder(R.mipmap.square_placeholder_figure)
                  .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                  .dontAnimate()
                  .into(view);
        }
        if(!TextUtils.isEmpty(strategy.placeHolder)){
//          Picasso.Builder builder=new Picasso.Builder(WXEnvironment.getApplication());
//          Picasso picasso=builder.build();
//          picasso.load(Uri.parse(strategy.placeHolder)).into(view);
//          view.setTag(strategy.placeHolder.hashCode(),picasso);
        }
//        Picasso.with(WXEnvironment.getApplication())
//                .load(temp)
////                .placeholder(R.mipmap.square_placeholder_figure)站位图去掉
//                .into(view, new Callback() {
//                  @Override
//                  public void onSuccess() {
//                    if(strategy.getImageListener()!=null){
//                      strategy.getImageListener().onImageFinish(url,view,true,null);
//                    }
//                    if(!TextUtils.isEmpty(strategy.placeHolder)){
//                      ((Picasso) view.getTag(strategy.placeHolder.hashCode())).cancelRequest(view);
//                    }
//                  }
//                  @Override
//                  public void onError() {
//                    if(strategy.getImageListener()!=null){
//                      strategy.getImageListener().onImageFinish(url,view,false,null);
//                    }
//                  }
//                });
      }
    },0);

  }
}
