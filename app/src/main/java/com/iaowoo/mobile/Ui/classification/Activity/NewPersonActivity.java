package com.iaowoo.mobile.Ui.classification.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.iaowoo.mobile.H5toAndroid.modle.NEWACTIVITY;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.H5toAndroid.modle.NEWACTIVITY;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;

import java.io.File;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述网页版七鱼)
 * @date 2018/6/6
 */
public class NewPersonActivity  extends TitleActivity{
    private String Url;
    /**
     * 拍照/选择文件请求码
     */
    private static final int REQ_CAMERA = 1;
    private static final int REQ_CHOOSE = REQ_CAMERA + 1;

    private WebView mWebView;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageArray;

    private static final String PATH = Environment.getExternalStorageDirectory() + "/DCIM";
    private String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        showBackwardView_show(true);
        NEWACTIVITY newactivity=(NEWACTIVITY) getIntent().getSerializableExtra("new_ac");
        if(newactivity!=null){
            setTitle(newactivity.getTitle());
            Url= newactivity.getUrl();
            ConfigH5Url.NEW_PEARSON_ACTIVITY=Url;
        }
        initViews();
        initWebviews();
    }
    private void initWebviews() {
        mWebView.setWebChromeClient(new WebChromeClient() {
            // For Android >=3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                if(acceptType.equals("image/*")) {
                    if (mUploadMessage != null) {
                        mUploadMessage.onReceiveValue(null);
                        return;
                    }
                    mUploadMessage = uploadMsg;
                    checkPermission(NewPersonActivity.this);
                } else {
                    onReceiveValue();
                }
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooser(uploadMsg, "image/*");
            }

            // For Android  >= 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooser(uploadMsg, acceptType);
            }

            // For Android  >= 5.0
            @Override
            @SuppressLint("NewApi")
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (fileChooserParams != null && fileChooserParams.getAcceptTypes() != null
                        && fileChooserParams.getAcceptTypes().length > 0 && fileChooserParams.getAcceptTypes()[0].equals("image/*")) {
                    if (mUploadMessageArray != null) {
                        mUploadMessageArray.onReceiveValue(null);
                    }
                    mUploadMessageArray = filePathCallback;
                    checkPermission(NewPersonActivity.this);
                } else {
                    onReceiveValue();
                }
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          mWebView.loadUrl(url);
                                          return true;
                                      }
                                  }
        );
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDomStorageEnabled(true);
        if (android.os.Build.VERSION.SDK_INT >Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        loadUrl();
    }

    private void selectImage() {
        String[] selectPicTypeStr = {"拍摄", "从相册中选择"};
        new AlertDialog.Builder(this)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onReceiveValue();
                    }
                })
                .setItems(selectPicTypeStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        switch (which) {
                            // 相机拍摄，此处调用系统相机拍摄图片，开发者可根据实际情况选用系统相机还是自己在这之上封装一层从而符合APP风格
                            case 0:
                                openCamera();
                                break;
                            // 手机相册，此处调用系统相册选择图片，开发者可根据实际情况选用系统相册还是自己在这之上封装一层从而符合APP风格
                            case 1:
                                openAlbum();
                                break;
                            default:
                                break;
                        }

                    }
                })
                .show();
    }

    private void openAlbum() {
        if (!hasSDcard()) {
            return;
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//使用以上这种模式，并添加以上两句
            startActivityForResult(intent, REQ_CHOOSE);
        }
    }

    private void openCamera() {
        if (!hasSDcard()) {
            return;
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageName = System.currentTimeMillis() + ".png";
            File file = new File(PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri uri;
           File file1=new File(PATH, imageName);
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(this.getApplicationContext(), "com.iaowoo.mobile.fileprovider", file1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                NewPersonActivity.this.startActivityForResult(intent, REQ_CAMERA);

            } else {
                uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                NewPersonActivity.this.startActivityForResult(intent, REQ_CAMERA);
            }

        }
    }


    private boolean hasSDcard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(this, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT).show();
            onReceiveValue();
        }
        return flag;
    }

    private void loadUrl() {
        mWebView.loadUrl(Url);
    }

    private void initViews() {
        mWebView =findViewById(R.id.webview);
    }

    /**
     * 返回文件选择
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            onReceiveValue();
            return;
        }
        switch (requestCode) {
            case REQ_CAMERA:
                File fileCamera = new File(PATH, imageName);
                handleFile(fileCamera);
                break;
            case REQ_CHOOSE:
                Uri uri = intent.getData();
                String absolutePath = getAbsolutePath(this, uri);
                File fileAlbum = new File(absolutePath);
                handleFile(fileAlbum);
                break;
        }
    }

    private void handleFile(File file) {
        if (file.isFile()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (null == mUploadMessageArray) {
                    return;
                }
                Uri uri = Uri.fromFile(file);
                Uri[] uriArray = new Uri[]{uri};
                mUploadMessageArray.onReceiveValue(uriArray);
                mUploadMessageArray = null;
            } else {
                if (null == mUploadMessage) {
                    return;
                }
                Uri uri = Uri.fromFile(file);
                mUploadMessage.onReceiveValue(uri);
                mUploadMessage = null;
            }
        } else {
            onReceiveValue();
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public String getAbsolutePath(final Context context,
                                  final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void onReceiveValue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mUploadMessageArray != null) {
                mUploadMessageArray.onReceiveValue(null);
                mUploadMessageArray = null;
            }
        } else {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
        }
    }

    /**
     * 检查拍照权限
     */
    private void checkPermission(Activity context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 进入这儿表示没有权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                // 提示已经禁止
                ToastUtilsAll.getInstance().showShortToast("拍照权限禁止");
            } else {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
                selectImage();
            }
        } else {
            selectImage();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
