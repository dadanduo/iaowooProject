package com.iaowoo.mobile.Ui.classification.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PictureUtil;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PictureUtil;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 拍照and选择相册
 */
public class PhotoActivity  extends  TitleActivity implements ClickCallBack {
    private String type;
    private String  tag;
    /**
     * 需要裁剪的宽度
     */
    private int OUTPUT_X=200;
    /**
     * 需要裁剪的高度
     */
    private int OUTPUT_Y=200;
    /**
     * 是否需要裁剪
     */
    private String  TAG_CUT_OK="YES";

    private static final String TAG = "PhotoUtils";
    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static final int CODE_RESULT_REQUEST = 0xa2;
    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    public static File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    public static File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    public static Uri imageUri;
    public static Uri cropImageUri;
    private SoftReference<RdioBroadCast> rdioBroadCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_show);
        type=getIntent().getStringExtra("type");
        tag=getIntent().getStringExtra("tag");
        setTitle("选择方式");
        showBackwardView_show(true);
        setBoreturn();
        setOnclick(this);
        LogPrint.printError("tag"+tag);
        rdioBroadCast=new SoftReference<>(new RdioBroadCast());
        requestPermission1(2);
        PictureUtil.mkdirMyPetRootDirectory();
    }


    /**
     * 拍照
     * @param view
     */
    public void carm(View view)
    {
        requestPermission1(0);
    }
    /**
     * 图册
     * @param view
     */
    public void photo(View view)
    {
        requestPermission1(1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                       imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, "com.iaowoo.mobile.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        takePicture(this, imageUri,CODE_CAMERA_REQUEST);
//                        imageCapture();
                    } else {
                        ToastUtilsAll.getInstance().showShortToast("设备没有SD卡！");
                    }
                } else {
                    ToastUtilsAll.getInstance().showShortToast("请允许打开相机权限！！");
                }
                break;
            }
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPic(this,CODE_GALLERY_REQUEST);
                } else {
                    ToastUtilsAll.getInstance().showShortToast("请允许打开操作SDCard！！");
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //拍照完成回调
                case CODE_CAMERA_REQUEST:
                   cropImageUri = Uri.fromFile(fileCropUri);
                    //裁剪图片
                    if(!TextUtils.isEmpty(tag)) {
                        if (tag.equals(TAG_CUT_OK)) {
                            cropImageUri(this,imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                        } else {
                            Bitmap bitmaps = getBitmapFromUri(imageUri, this);
                            if(bitmaps!=null){
                                String url = saveBitmap(PhotoActivity.this, bitmaps, true);
                                LogPrint.printError("url"+url);
                                XutilsHttp.getInstance().uploadPicture(url, type,ZApplication.getContext(), 0);
                                finish();
                            }else{
                                ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片不存在");
                            }
                        }
                    }else{
                        Bitmap bitmaps =getBitmapFromUri(imageUri, this);
                        if(bitmaps!=null){
                            String url =saveBitmap(PhotoActivity.this, bitmaps, true);
                            XutilsHttp.getInstance().uploadPicture(url, type,  ZApplication.getContext(), 0);
                            finish();
                        }else{
                            ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片不存在");
                        }
                    }
                    break;
                //访问相册完成回调
                case CODE_GALLERY_REQUEST:
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        //不为空不让进来
                        if(getPath(this, data.getData())!=null) {
                            Uri newUri = Uri.parse(getPath(this, data.getData()));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                newUri = FileProvider.getUriForFile(this, "com.iaowoo.mobile.fileprovider", new File(newUri.getPath()));
                            }
                            if (!TextUtils.isEmpty(tag)) {
                                //裁剪图片
                                if (tag.equals(TAG_CUT_OK)) {
                                    cropImageUri(this, newUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                                } else {
                                    Bitmap bitmap_picture = getBitmapFromUri(newUri, this);
                                    if (bitmap_picture != null) {
                                        String url_p = saveBitmap(PhotoActivity.this, bitmap_picture, false);
                                        XutilsHttp.getInstance().uploadPicture(url_p, type,  ZApplication.getContext(), 0);
                                        finish();
                                    } else {
                                        ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片不存在");
                                    }
                                }
                            } else {
                                Bitmap bitmap_picture = getBitmapFromUri(newUri, this);
                                if (bitmap_picture != null) {
                                    String url_p = saveBitmap(PhotoActivity.this, bitmap_picture, false);
                                    XutilsHttp.getInstance().uploadPicture(url_p, type,  ZApplication.getContext(), 0);
                                    finish();
                                } else {
                                    ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片似乎不存在，请拍照试试。");
                                }
                            }
                        }
                        else {
                            ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片不存在");
                        }
                    } else {
                        ToastUtilsAll.getInstance().showShortToast("设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        XutilsHttp.getInstance().uploadPicture(cropImageUri.toString().substring(5,cropImageUri.toString().length()),type, ZApplication.getContext(),0);
                        finish();
                    }else{
                        ToastUtilsAll.getInstance().showShortToast("亲！您选择的照片不存在");
                    }
                    break;
                default:
            }
        }
    }
    @Override
    public void OnClick() {
        if(rdioBroadCast.get()!=null){
            rdioBroadCast.get().sendData(PhotoActivity.this,RdioBroadCast.DATA,"",RdioBroadCast.BOARD,"cancel");
            finish();
        }else{
            rdioBroadCast=new SoftReference<>(new RdioBroadCast());
            rdioBroadCast.get().sendData(PhotoActivity.this,RdioBroadCast.DATA,"",RdioBroadCast.BOARD,"cancel");
        }
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(rdioBroadCast.get()!=null) {
            rdioBroadCast.get().sendData(PhotoActivity.this, RdioBroadCast.DATA, "", RdioBroadCast.BOARD, "cancel");
        }else{
            rdioBroadCast=new SoftReference<>(new RdioBroadCast());
            rdioBroadCast.get().sendData(PhotoActivity.this, RdioBroadCast.DATA, "", RdioBroadCast.BOARD, "cancel");
        }
        return super.onKeyDown(keyCode, event);
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
        UMShareAPI.get(this).release();
    }

    /**
     * 访问权限 0  相机  1 读取
     * @param id
     */
    public  void requestPermission1(final int id) {
        if(id==0){
            PermissionsUtil.requestPermission(ZApplication.getContext(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                        autoObtainCameraPermission();
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    LogPrint.printError("没有权限");
                }
            }, Manifest.permission.CAMERA);
        }else if(id==1){
            PermissionsUtil.requestPermission(ZApplication.getContext(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                        autoObtainStoragePermission();
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    LogPrint.printError("没有权限");
                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }



    /**
     * 自动获取相机权限
     */
    private   void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(PhotoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(PhotoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(PhotoActivity.this, Manifest.permission.CAMERA)) {
                ToastUtilsAll.getInstance().showShortToast("你已经被拒绝过一次了");
            }
            ActivityCompat.requestPermissions(PhotoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                //api<24版本之前获得Uri
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(PhotoActivity.this, "com.iaowoo.mobile.fileprovider", fileUri);
                }
                takePicture(PhotoActivity.this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtilsAll.getInstance().showShortToast("设备没有sd卡");
            }
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(PhotoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PhotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            openPic(PhotoActivity.this, CODE_GALLERY_REQUEST);
        }

    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * @param activity    当前activity
     * @param imageUri    拍照后照片存储路径
     * @param requestCode 调用系统相机请求码
     */
    public void takePicture(Activity activity, Uri imageUri, int requestCode) {
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这一句非常重要
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //将拍照结果保存至photo_file的Uri中，不保留在相册中
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, requestCode);
        } else {
            //调用系统相机
            Intent intentCamera = new Intent();
            intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            //将拍照结果保存至photo_file的Uri中，不保留在相册中
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intentCamera, requestCode);
        }
    }

    /**
     * @param activity    当前activity
     * @param requestCode 打开相册的请求码
     */
    public  void openPic(Activity activity, int requestCode) {
        // ACTION_PICK ACTION_GET_CONTENT
        //使用Intent.ACTION_GET_CONTENT打开图库
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            activity.startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    requestCode);
        }
        //使用Intent.ACTION_OPEN_DOCUMENT打开图库
        else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * @param activity    当前activity
     * @param orgUri      剪裁原图的Uri
     * @param desUri      剪裁后的图片的Uri
     * @param aspectX     X方向的比例
     * @param aspectY     Y方向的比例
     * @param width       剪裁图片的宽度
     * @param height      剪裁图片高度
     * @param requestCode 剪裁图片的请求码
     */
    public  void cropImageUri(Activity activity, Uri orgUri, Uri desUri, int aspectX, int aspectY, int width, int height, int requestCode) {
        try{
            Intent intent = new Intent("com.android.camera.action.CROP");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.setDataAndType(orgUri, "image/*");
            //发送裁剪信号
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", aspectX);
            intent.putExtra("aspectY", aspectY);
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);
            intent.putExtra("scale", true);
            //将剪切的图片保存到目标Uri中
            intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
            //1-false用uri返回图片
            //2-true直接用bitmap返回图片（此种只适用于小图片，返回图片过大会报错）
            intent.putExtra("return-data", false);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, requestCode);
        }catch (ActivityNotFoundException a){
            a.getMessage();
        }

    }

    /**
     * 读取uri所在的图片
     *
     * @param uri      图片对应的Uri
     * @param mContext 上下文对象
     * @return 获取图像的Bitmap
     */
    public Bitmap getBitmapFromUri(Uri uri, Context mContext) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param context 上下文对象
     * @param uri     当前相册照片的Uri
     * @return 解析后的Uri对应的String
     */
    @SuppressLint("NewApi")
    public  String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        String pathHead = "file:///";
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return pathHead + Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);

                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return pathHead + getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return pathHead + getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return pathHead + getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return pathHead + uri.getPath();
        }
        return null;
    }
    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private  String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private  boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private  boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private  boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    private static final String IMAGE_FILE_NAME = "user_head_icon.jpg";

    private void imageCapture() {
        Intent intent;
        Uri pictureUri;
        //getMyPetRootDirectory()得到的是Environment.getExternalStorageDirectory() + File.separator+"MyPet"
        //也就是我之前创建的存放头像的文件夹（目录）
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这一句非常重要
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //""中的内容是随意的，但最好用package名.provider名的形式，清晰明了
            pictureUri = FileProvider.getUriForFile(this,
                    "com.iaowoo.mobile.fileprovider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到oictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        LogPrint.printError(TAG+"before take photo"+pictureUri.toString());
        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }
}
