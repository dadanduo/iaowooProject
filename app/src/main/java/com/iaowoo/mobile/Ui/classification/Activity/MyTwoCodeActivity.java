package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.umeng.ShareUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
 * @Description: ${todo}(我的二维码页面)
 * @date 2019/1/3
 * @email ${18011009889@163.com}
 */
public class MyTwoCodeActivity  extends BaseBufferActivity{

    @BindView(R.id.show_time)
    FrameLayout show_time;

    private View show_view;

    private Bitmap bitmap;

    @BindView(R.id.tops)
    LinearLayout tops;


    @Override
    public int getLayoutResId() {
        return R.layout.my_two_code_layout;
    }

    /**
     * 个人信息
     */
    private MYMESSAGE mymessage;

    @Override
    protected void initView() {
        super.initView();
        this.allState();
        this.setViewMarginTop(tops);
        mymessage= (MYMESSAGE) getIntent().getSerializableExtra("myMsg");
        show_view=addView();
        show_time.addView(show_view);
    }
    @Override
    protected void initData() {
        super.initData();
    }


    private View addView() {
        //身份显示图片
        ImageView shen_fen_code;
        //二维码显示图片
        ImageView code_two_image;
        TextView name_code;
        CircleImageView Top_headIcon;
        // TODO 动态添加布局(xml方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater3 = LayoutInflater.from(this);
        View view =  inflater3.inflate(R.layout.code_fenxiang, null,false);
        shen_fen_code=view.findViewById(R.id.shen_fen_code);
        code_two_image=view.findViewById(R.id.code_two_image);
        name_code=view.findViewById(R.id. name_code);
        Top_headIcon=view.findViewById(R.id.Top_headIcon);


        //添加数据
        if(mymessage!=null) {
            if (!TextUtils.isEmpty(mymessage.getBody().getContent().getQrCodeImg())) {
                glideUtils.glides(MyTwoCodeActivity.this, mymessage.getBody().getContent().getQrCodeImg(), code_two_image);
            }
            if (!TextUtils.isEmpty(mymessage.getBody().getContent().getNickname())) {
                name_code.setText("" + mymessage.getBody().getContent().getNickname());
            } else {
                if (!TextUtils.isEmpty(mymessage.getBody().getContent().getName())) {
                    name_code.setText("" + mymessage.getBody().getContent().getName());
                } else {
                    if (!TextUtils.isEmpty(mymessage.getBody().getContent().getMobileNo())) {
                        name_code.setText("" + mymessage.getBody().getContent().getMobileNo());
                    }
                }
            }
            //0.游客，1.会员，2.摊主，3.过期摊主，4.农场主，5.过期农场主
            if (!TextUtils.isEmpty(mymessage.getBody().getContent().getMemberCode())) {
                switch (mymessage.getBody().getContent().getMemberCode()) {
                    case "0":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_tourists_icon);
                        break;
                    case "1":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_members_icon);
                        break;
                    case "2":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_farmer_icon);
                        break;
                    case "3":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_farmer_overdue_icon);
                        break;
                    case "4":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_ranchers_icon);
                        break;
                    case "5":
                        shen_fen_code.setImageResource(R.mipmap.mine_level_ranchers_overdue_icon);
                        break;
                    default:
                        break;
                }
            }
            //账号不为空
            if (!TextUtils.isEmpty(PrefManager.getInstance().getMobile())) {
                LoginInfo loginInfo = (LoginInfo) XutilsDBManage.getInstance().searchName(PrefManager.getInstance().getMobile(), LoginInfo.class);
                if (loginInfo != null) {
                    LoginInfo d = new LoginInfo();
                    d.setName(loginInfo.getName());
                    d.setPassWord(loginInfo.getPassWord());
                    d.setId(loginInfo.getId());
                    //更换头像
                    if (!TextUtils.isEmpty(mymessage.getBody().getContent().getHeadImgUrl())) {
                        d.setHeadImage(mymessage.getBody().getContent().getHeadImgUrl());
                    }
                    d.setGroudId(loginInfo.getGroudId());
                    if (XutilsDBManage.getInstance().saveOrUpdate(d)) {
                    }
                }
            }
            if (TextUtils.isEmpty(mymessage.getBody().getContent().getHeadImgUrl())) {
                Top_headIcon.setImageResource(R.mipmap.mine_default_avatar);
            } else {
                glideUtils.glideRound(mContext, mymessage.getBody().getContent().getHeadImgUrl() + "", Top_headIcon);
            }
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

    /**
     * 把一个view转化成bitmap对象
     * */

    public  Bitmap getViewBitmap(View view) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.AT_MOST);
        /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
         * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
         */
        view.measure(measuredWidth, measuredHeight);
        view.layout(0,0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @OnClick({R.id.code_return,R.id.peng_you_quan,R.id.wechat,R.id.save})
    public void onClick(View view){
        switch (view.getId()){
            //二维码页面返回按钮
            case R.id.code_return:
                finish();
                break;
            //分享到朋友圈
            case  R.id.peng_you_quan:
                bitmap=getViewBitmap(show_view);
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text,"",R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE,bitmap);
                break;
            //分享到微信
            case R.id.wechat:
                bitmap=getViewBitmap(show_view);
                //微信好友的分享
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN,bitmap);
                break;
                //保存
            case R.id.save:
                bitmap=getViewBitmap(show_view);
                String urls1=saveBitmap(this,bitmap,true);
                LogPrint.printError("保存路径："+urls1);
                ToastUtilsAll.getInstance().showShortToast("保存成功");
                break;
        }
    }
}
