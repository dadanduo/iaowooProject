package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.os.Bundle;

import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.modle.SHOPDETAILS;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.modle.SHOPDETAILS;
import com.iaowoo.mobile.R;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *商户二维码页面
 */
public class CodeDetailsActivity  extends  TitleActivity implements ClickCallBack {
    private SHOPDETAILS shopdetails;
    private  Bitmap bitmap;
    private FrameLayout code_de;
    private View show_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_details);
        shopdetails=(SHOPDETAILS) getIntent().getSerializableExtra("shops");
        code_de=findViewById(R.id.code_de);
        showBackwardView_show(true);
        showForwardView("保存",true);
        if(shopdetails!=null) {
            setTitle(shopdetails.getTitle());
        }
        setOnclick(this);
        //不用生成二维码
//        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo_main);
//        Bitmap bitmap = ZXingUtils.createQRImage("http://www.baidu.com", 300, 300);
//        code_image.setImageBitmap(bitmap);
        show_view=addView();
        code_de.addView(show_view);
    }
    @Override
    public void OnClick() {
        bitmap=getViewBitmap(show_view);
        String url=saveBitmap(CodeDetailsActivity.this,bitmap,true);
        LogPrint.printError("保存路径："+url);
        ToastUtilsAll.getInstance().showShortToast("保存成功");
    }

    /**
     * 把一个view转化成bitmap对象
     * */

    public  Bitmap getViewBitmap(View view) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(SingleOverAll.getInstance().deviceWidth(this), View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(SingleOverAll.getInstance().deviceHeight(this), View.MeasureSpec.AT_MOST);
        /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
         * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
         */
        view.measure(measuredWidth, measuredHeight);
        view.layout(0 ,0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    private View addView() {
        ImageView code_image;
        ImageView log_shop;
        TextView shop_name_details,address_details,de_miaoshu;
        // TODO 动态添加布局(xml方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //LayoutInflater inflater1=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//      LayoutInflater inflater2 = getLayoutInflater();
        LayoutInflater inflater3 = LayoutInflater.from(CodeDetailsActivity.this);
        View view =  inflater3.inflate(R.layout.code, null,false);
        code_image=view.findViewById(R.id.code_image);
        de_miaoshu=view.findViewById(R.id.de_miaoshu);
        log_shop=view.findViewById(R.id.log_shop);
        address_details=view.findViewById(R.id.address_details);
        shop_name_details=view.findViewById(R.id.shop_name_details);
        if(!TextUtils.isEmpty(shopdetails.getMerchantName())&&!TextUtils.isEmpty(shopdetails.getMainImg())&&!TextUtils.isEmpty(shopdetails.getMerchantName())&&!TextUtils.isEmpty(shopdetails.getDetailAddress())) {
            shop_name_details.setText(shopdetails.getMerchantName()+"");
            address_details.setText(shopdetails.getDetailAddress()+"");
            Glide.with(CodeDetailsActivity.this).load(shopdetails.getRqCode()).into(code_image);
            Glide.with(CodeDetailsActivity.this).load(shopdetails.getMainImg()).into(log_shop);
            de_miaoshu.setText(shopdetails.getText());
        }
        view.setLayoutParams(lp);
        return view;
    }
    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @param saveLocal true表示存在相册中 false 表示存在铺连铺app下
     * @return
     */
    public  String saveBitmap(Context context, Bitmap mBitmap, boolean saveLocal) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            if(saveLocal)
                savePath = ConfigFlag.SD_PATH;
            else savePath=ConfigFlag.NO_SD_PATH;
        } else {
            if(saveLocal)
                savePath = context.getApplicationContext().getFilesDir()
                        .getAbsolutePath()
                        + ConfigFlag.IN_PATH;
            else
                savePath = context.getApplicationContext().getFilesDir()
                        .getAbsolutePath()
                        + ConfigFlag.NO_IN_PATH;
        }
        try {
            filePic = new File(savePath + SingleOverAll.getInstance().generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            //最后插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), mBitmap, "", "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bitmap!=null) {
            bitmap.recycle();
            bitmap=null;
        }
    }
}

