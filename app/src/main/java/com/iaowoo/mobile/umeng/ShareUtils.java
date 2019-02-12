package com.iaowoo.mobile.umeng;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;

/**
 * Author     wildma
 * DATE       2017/07/16
 * Des	      ${友盟分享工具类}
 */
public class ShareUtils {
    public  static  void shareSmallPc(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID,String small_id,String code_url, SHARE_MEDIA platform,Bitmap bitmap){

        final RdioBroadCast   rdioBroadCast=new RdioBroadCast();

        UMMin umMin = new UMMin(WebUrl);
        if(bitmap==null) {
            //兼容低版本的网页链接
            if (TextUtils.isEmpty(imageUrl)) {
                umMin.setThumb(new UMImage(activity, imageID));  //本地缩略图
            } else {
                umMin.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
            }
        }else{
            umMin.setThumb(new UMImage(activity,bitmap));
        }
        // 小程序消息封面图片
        umMin.setTitle(title);
        // 小程序消息title
        umMin.setDescription(description);
        // 小程序消息描述
        umMin.setPath(code_url);
        //小程序页面路径
        umMin.setUserName(small_id);
        // 小程序原始id,在微信平台查询
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(umMin)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }
                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                    Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                    rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOk");
                                }
                            }
                        });
                    }
                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            Log.d("throw", "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                                rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareFaild");
                            }
                        });
                    }

                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                                rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOver");
                            }
                        });
                    }
                })
                .share();

    }
    /**
     * 分享链接
     */
    public static void  shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform,Bitmap bitmap) {
        UMWeb web = new UMWeb(WebUrl);//连接地址
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        final RdioBroadCast   rdioBroadCast=new RdioBroadCast();


        if(bitmap==null) {
            //兼容低版本的网页链接
            if (TextUtils.isEmpty(imageUrl)) {
                web.setThumb(new UMImage(activity, imageID));  //本地缩略图
            } else {

                web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
            }

            new ShareAction(activity)
                    .setPlatform(platform)
                    .withMedia(web)
                    .setCallback(new UMShareListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                        }
                        @Override
                        public void onResult(final SHARE_MEDIA share_media) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                        Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                        rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOk");
                                    }
                                }
                            });
                        }
                        @Override
                        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                            if (throwable != null) {
                                Log.d("throw", "throw:" + throwable.getMessage());
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                                    rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareFaild");
                                }
                            });
                        }

                        @Override
                        public void onCancel(final SHARE_MEDIA share_media) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                                    rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOver");
                                }
                            });
                        }
                    })
                    .share();

        }else{
            UMImage image = new UMImage(activity, bitmap);//本地文件

            new ShareAction(activity)
                    .setPlatform(platform)
                    .withMedia(image)
                    .setCallback(new UMShareListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                        }
                        @Override
                        public void onResult(final SHARE_MEDIA share_media) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                        Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                        rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOk");
                                    }
                                }
                            });
                        }
                        @Override
                        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                            if (throwable != null) {
                                Log.d("throw", "throw:" + throwable.getMessage());
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                                    rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareFaild");
                                }
                            });
                        }

                        @Override
                        public void onCancel(final SHARE_MEDIA share_media) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                                    rdioBroadCast.sendData(activity,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"shareOver");
                                }
                            });
                        }
                    })
                    .share();

        }

    }
}
