package com.iaowoo.mobile.Utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.PICTURE;
import com.iaowoo.mobile.H5toAndroid.modle.PICTUREUP;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.PICTURE;
import com.iaowoo.mobile.H5toAndroid.modle.PICTUREUP;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import com.taobao.weex.WXSDKInstance;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.x;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 网络请求类
 */
public class XutilsHttp {
	public  final static String MOBILENO="mobileNo";
	public  final static  String CHANNEL="xuanhao";
	public  final static String PRODUCT="iaowoo_Android";
	public  final static String VAC_CODE="validCode";
	public final  static  String PASSWORD="password";

	public static XutilsHttp xutilsHttp=null;
	/**
	 *
	 */
	public XutilsHttp() {
	}
	/**
	 * 单列模式
	 *
	 * @return 双重校验锁
	 */
	public static XutilsHttp getInstance() {
		if (xutilsHttp == null) {
			synchronized (XutilsHttp.class) {
				if (xutilsHttp== null) {
					xutilsHttp = new XutilsHttp();

				}
			}
		}
		return xutilsHttp;
	}

	/**
	 * 注册
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param smsCode
	 * @param inviteCode
	 * @param channel
	 * @param product
	 */
	public  void postEncodedRegister(OkhttpCallBack okhttpCallBack, String mobilePhone, String password, String smsCode, String inviteCode, String channel, String product) {
		String url= UtilsOkHttpAll.REGISTERTEST+"?"+MOBILENO+"="+mobilePhone+"&"+PASSWORD+"="+password+"&"+VAC_CODE+"="+smsCode+"&inviteCode="+inviteCode+"&channel="+channel+"&product="+product;
		LogPrint.printError(url);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url,okhttpCallBack, pm);
	}

	/**
	 * 设置密码
	 * @param okhttpCallBack
	 * @param token
	 * @param newPassword
	 * @param comfirmPassword
	 * @param channel
	 * @param product
	 */
	public  void postEncodedSetPassword(OkhttpCallBack okhttpCallBack, String token, String newPassword, String comfirmPassword, String channel, String product) {
		LogPrint.print("token:"+token,4);
		String url= UtilsOkHttpAll.SETPASSWORD+"?loginToken="+token+"&newPassword="+newPassword+"&comfirmPassword="+comfirmPassword+"&channel="+channel+"&product="+product;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 登出
	 * @param okhttpCallBack
	 * @param token
	 */
	public  void postEncodedLogout( OkhttpCallBack okhttpCallBack, String token) {
		LogPrint.print("token:"+token,4);
		String url= UtilsOkHttpAll.LOGOUT+"?token="+token;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 通过获取登录短信验证码接口登录
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param smsCode
	 * @param channel
	 * @param product
	 */
	public  void postEncodedGetLoginBysms(OkhttpCallBack okhttpCallBack, String mobilePhone, String smsCode, String channel, String product) {
		String url= UtilsOkHttpAll.GETLOGINSMS+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product+"&"+VAC_CODE+"="+smsCode;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 获取短信验证码
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param channel
	 * @param product
	 */
	public  void postEncodedsendGetsms(OkhttpCallBack okhttpCallBack, String mobilePhone, String channel, String product) {
		String url=UtilsOkHttpAll.GETSMS+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}


	/**
	 * 通过密码登录
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param password
	 * @param channel
	 * @param product
	 */
	public  void postEncodedByPasswordLogin(OkhttpCallBack okhttpCallBack, String mobilePhone, String password, String channel, String product) {
		try {
			String newPs=password.replaceAll("\n", "");
			String password_new = URLEncoder.encode(newPs,"UTF-8");
			LogPrint.printError(">>>>>>>加密后："+password_new);

			String url=UtilsOkHttpAll.BYPASSWORDLOGIN+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product+"&password="+password_new;
			RequestParams pm = new RequestParams();
			pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
			postSend(url, okhttpCallBack, pm);
		}catch (Exception e){
		}
	}

	/**
	 * 检测到图形 验证码
	 * @param okhttpCallBack
	 * @param key
	 * @param operation
	 */
	public  void postEncodedGetImag( OkhttpCallBack okhttpCallBack, String key, String operation) {
		String url=UtilsOkHttpAll.GETIMAGCODE+"?"+"key="+key+"&operation="+operation;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 忘记密码确定页面
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param newPassword
	 * @param comfirmPassword
	 * @param smsCode
	 * @param channel
	 * @param product
	 */
	public  void postEncodedForgetOk(OkhttpCallBack okhttpCallBack, String mobilePhone, String newPassword, String comfirmPassword, String smsCode, String channel, String product) {
		try {
			String newPassword1=newPassword.replaceAll("\n", "");
			String comfirmPassword1=comfirmPassword.replaceAll("\n", "");
			String newPassword_new = URLEncoder.encode(newPassword1, "UTF-8");
			String comfirmPassword_new=URLEncoder.encode(comfirmPassword1, "UTF-8");
			String url=UtilsOkHttpAll.FORGETPASSWORD+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product+"&newPassword="+newPassword_new+"&comfirmPassword="+comfirmPassword_new+"&"+VAC_CODE+"="+smsCode;
			LogPrint.print(url,0);
			RequestParams pm = new RequestParams();
			pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
			postSend(url, okhttpCallBack, pm);
		} catch (Exception e){
		}
	}

	/**
	 * 发送忘记密码的验证码
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param channel
	 * @param product
	 */
	public  void postEncodedSendForgetsmsCode(OkhttpCallBack okhttpCallBack, String mobilePhone, String channel, String product) {
		String url=UtilsOkHttpAll.FORGETPASSWORDBYSMS+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 发送登录的验证码
	 * @param okhttpCallBack
	 * @param mobilePhone
	 * @param channel
	 * @param product
	 */
	public  void postEncodedSendLoginSms( OkhttpCallBack okhttpCallBack, String mobilePhone, String channel, String product) {
		String url=UtilsOkHttpAll.SENDLOGINCODE+"?"+MOBILENO+"="+mobilePhone+"&channel="+channel+"&product="+product;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}
	/**
	 * 登陆成功后把registerid给后台
	 * @param okhttpCallBack
	 * @param registerid
	 * @param token
	 */
	public  void postRegisterIdSendService(OkhttpCallBack okhttpCallBack, String registerid,String token) {
		LogPrint.printError("re:"+registerid+"toke:"+token);
		String url=UtilsOkHttpAll.SENDREGISTERID+"?"+"loginToken="+token+"&registrationId="+registerid;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}
	/**
	 * 获取分类列表
	 * @param okhttpCallBack
	 */
	public  void postGetFenLei(OkhttpCallBack okhttpCallBack) {
		String url=UtilsOkHttpAll.FEN_LEI_LIE_BIAO;
		LogPrint.print(url,0);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}
	/**
	 * 获取自动更新
	 * @param okhttpCallBack
	 */
	public  void getAndroidVersion(final OkhttpCallBack okhttpCallBack) {
		String url=UtilsOkHttpAll.UPDATE_ANDROID;
//		LogAll.printError(url);
//		RequestParams pm = new RequestParams();
//		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		ZApplication.http.send(HttpRequest.HttpMethod.POST,url,pm,new RequestCallBack<String>() {
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//				// TODO Auto-generated method stub
//				LogAll.print("shop："+responseInfo.result.toString(),4);
//				okhttpCallBack.OnSuccess(responseInfo.result.toString());
//			}
//			@Override
//			public void onFailure(HttpException error, String msg) {
//				// TODO Auto-generated method stub
//				okhttpCallBack.OnFaild(msg);
//			}
//		});
		org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(url);	// 网址(请替换成实际的网址)
		x.http().post(params, new Callback.CommonCallback<String>() {
			@Override
			public void onCancelled(CancelledException arg0) {
			}
			// 注意:如果是自己onSuccess回调方法里写了一些导致程序崩溃的代码，也会回调道该方法，因此可以用以下方法区分是网络错误还是其他错误
			// 还有一点，网络超时也会也报成其他错误，还需具体打印出错误内容比较容易跟踪查看
			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				okhttpCallBack.OnFaild("网络异常："+ex.getMessage().toString());
			}
			// 不管成功或者失败最后都会回调该接口
			@Override
			public void onFinished() {
			}
			@Override
			public void onSuccess(String arg0) {
				LogPrint.printError("请求成功了："+arg0);
				okhttpCallBack.OnSuccess(arg0);
			}
		});
	}
	/**
	 * @param client
	 * @param apiVersion
	 * @param okhttpCallBack
	 */
	public  void getViewShowOrHidden(String client,String apiVersion,final OkhttpCallBack okhttpCallBack) {
		String url=UtilsOkHttpAll.ISOR_SHOW_VIEW+"?"+"client="+client+"&apiVersion="+apiVersion;
		LogPrint.printError("url"+url);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}

	/**
	 * 上传图片到数据库
	 * @param url
	 * @param type
	 * @param context
	 * @param tag   0表示图片上传，1表示微信同步
	 */
	public void uploadPicture(final String url,String type,Context context,int tag) {
		String uploadHost=UtilsOkHttpAll.UPFITE;  //server接收地址
		LogPrint.printError("url"+uploadHost);
		if(!TextUtils.isEmpty(url)) {
			File file = new File(url);
			if (file.exists()) {
				LogPrint.printError("文件存在");
			}
			RequestParams params = new RequestParams();
			params.addBodyParameter("type", type);
			params.addBodyParameter("image", new File(url), "multipart/form-data");  //filePath是手机获取的图片地址
			uploadMethod(params, uploadHost, context,tag);
		}
	}

	/**
	 * @param params
	 * @param uploadHost
	 * @param context
	 */
	public  void uploadMethod(final RequestParams params, final String uploadHost, final Context context,final  int tag) {
		ZApplication.getInstance().getHttp().configCurrentHttpCacheExpiry(1000 * 60);// 超时时间 Long配置当前Http缓存到期
		// 设置超时时间
		ZApplication.getInstance().getHttp().configTimeout(60* 1000);// 连接超时  //指的是连接一个url的连接等待时间。
		ZApplication.getInstance().getHttp().configSoTimeout(60 * 1000);// 获取数据超时  //指的是连接上一个url，获取response的返回等待时间
		ZApplication.getInstance().getHttp().send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				//上传開始
				LogPrint.printError("上传开始");
			}
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				//上传中
				LogPrint.printError("上传中"+current);
			}
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//上传成功，这里面的返回值，就是server返回的数据
				String result = responseInfo.result;
				LogPrint.printError("上传成功"+result);
				PICTUREUP pictureup =new PICTUREUP();
				pictureup = JSON.parseObject(result, PICTUREUP.class);
				if(pictureup.getCode().equals(OkhttpManager.SUCESS)) {
					PICTURE.ResponeBean picture = new PICTURE.ResponeBean();
					picture.setImageUrl(pictureup.getBody().getContent().getImageUrl());
					picture.setPrefix(pictureup.getBody().getContent().getPrefix());
					String json = ZApplication.gson.toJson(picture);
					LogPrint.printError("图片上传：" + json);
					String data=pictureup.getBody().getContent().getPrefix()+pictureup.getBody().getContent().getImageUrl();
					RdioBroadCast   rdioBroadCast=new RdioBroadCast();

					EventBus.getDefault().post(new EventBusMessageWeex("upImage",json));
					if(tag==0) {
						rdioBroadCast.sendData(context, RdioBroadCast.DATA, json, RdioBroadCast.BOARD, "uploadingSucess");
					}else{
						rdioBroadCast.sendData(context, RdioBroadCast.DATA,data , RdioBroadCast.BOARD, "wechatSynchronization");
					}
				}
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				//上传失败
				LogPrint.printError("上传失败"+error.getExceptionCode()+error.toString());
				ToastUtilsAll.getInstance().showShortToast("上传失败");
				 RdioBroadCast   rdioBroadCast=new RdioBroadCast();
				rdioBroadCast.sendData(context,RdioBroadCast.DATA,"", RdioBroadCast.BOARD,"uploadingFaild");
			}
		});
	}
	/**
	 * @param url
	 * @param okhttpCallBack
	 * @param pm
	 */
	private  void postSend(String url,final OkhttpCallBack okhttpCallBack,RequestParams pm){
		ZApplication.getInstance().getHttp().configTimeout(60* 1000);// 连接超时  //指的是连接一个url的连接等待时间。
		ZApplication.getInstance().getHttp().send(HttpRequest.HttpMethod.POST,url,pm,new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				LogPrint.printError("xutils请求回调数据:"+responseInfo.result.toString());
				okhttpCallBack.OnSuccess(responseInfo.result.toString());
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				LogPrint.printError("出问题了："+msg);
				okhttpCallBack.OnFaild(msg);
			}
		});
	}

	/**
	 //	 * @param okhttpCallBack
	 //	 */
	public  void getStartPage(int  location,String queryType,final OkhttpCallBack okhttpCallBack) {
		String url=UtilsOkHttpAll.BANNER+"?"+"location="+location+"&queryType="+queryType;
		LogPrint.printError("url"+url);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}
	/**
	 //	 * @param okhttpCallBack
	 //	 */
	public  void getBanner(int  pageNum,int  pageSize,final OkhttpCallBack okhttpCallBack) {
		String url=UtilsOkHttpAll.HTTP_BASE+UtilsOkHttpAll.BANNER+"?"+"pageNum="+pageNum+"&pageSize="+pageSize;
		LogPrint.printError("url"+url);
		RequestParams pm = new RequestParams();
		pm.addHeader("Content-Type", "application/x-www-form-urlencoded");
		postSend(url, okhttpCallBack, pm);
	}
}
