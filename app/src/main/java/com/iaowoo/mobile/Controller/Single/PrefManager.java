package com.iaowoo.mobile.Controller.Single;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iaowoo.mobile.Application.AppConst;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Utils.LogPrint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenda on 2018/3/22.
 */

public class PrefManager {
    public static PrefManager prefManager=null;
    public final  static  String NAME_PR="PLP";
    private Context mContext;
    private  SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    public static PrefManager getInstance() {
        if(prefManager==null){
            synchronized (PrefManager.class){
                if(prefManager==null){
                    prefManager=new PrefManager();
                }
            }
        }
        return prefManager;
    }
    public  PrefManager(){
        LogPrint.printError("存储数据执行了");
        mContext= ZApplication.getContext();
        sharedPreferences= mContext.getSharedPreferences(
                NAME_PR, Context.MODE_PRIVATE);
        editor =   sharedPreferences.edit();
    }
    /**
     * 存String数据
     * @param key
     * @param value
     * @return
     */
    private  boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }
    /**
     * 取String数据
     * @param key
     * @param defaultValue
     * @return
     */
    private String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
    /**
     * @param key
     * @return
     */
    private String getString(String key){
        return getString(key,"");
    }
    /**
     * 存boolean类型数据
     * @param key
     * @param value
     * @return
     */
    public  boolean putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }
    /**
     * @param key
     * @return
     */
    private boolean getBoolean(String key) {
        return getBoolean(key, true);
    }
    /**
     * @param key
     * @param defaultValue
     * @return
     */
    private boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    /**
     * @param key
     * @param value
     * @return
     */
    private boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }
    /**
     * @param key
     * @param defaultValue
     * @return
     */
    private  int getInt(String key, int defaultValue) {

        return sharedPreferences.getInt(key, defaultValue);
    }
    /**
     * @param key
     * @return
     */
    private   int getInt(String key) {
        return getInt(key, 0);
    }
    /**
     * @param key
     * @param value
     * @return
     */
    private   boolean putLong(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }
    /**
     * @param key
     * @return
     */
    private   long getLong(String key) {
        return getLong(key, 0);
    }
    /**
     * @param key
     * @param defaultValue
     * @return
     */
    private  long getLong(String key, long defaultValue) {

        return sharedPreferences.getLong(key, defaultValue);
    }
    /**
     * @param key
     * @param value
     * @return
     */
    private boolean putFloat(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }
    /**
     * @param key
     * @return
     */
    private float getFloat(String key) {
        return getFloat(key, 0);
    }
    /**
     * @param key
     * @param defaultValue
     * @return
     */
    private float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }
    /**
     * @param key
     */
    public void remove(String key) {
        sharedPreferences.edit().remove(key).commit();
    }
    /**
     *
     */
    public void clearAll() {
        sharedPreferences .edit().clear().commit();
    }
    /*****************************************数据操作********************************************/


    /**
     * 保存IntegralRatio
     * @param
     */
    public void  setIntegralRatio(Float IntegralRatio){
        putFloat("IntegralRatio",IntegralRatio);
    }

    /**
     * 获取IntegralRatio
     * @return
     */
    public Float getIntegralRatio(){
        return  getFloat("IntegralRatio");
    }


    /**
     * 保存groudId
     * @param
     */
    public void  setGroudId(int groudId){
        putInt("groudId",groudId);
    }

    /**
     * 获取groudId
     * @return
     */
    public int getGroudId(){
        return  getInt("groudId");
    }



    /**
     * 设置密码登录  true 密码登录 false 验证码登录
     * @param
     */
    public void  setPassWordLogin(boolean passWordLogin){
        putBoolean("passWordLogin",passWordLogin);
    }

    /**
     * 获取登录状态
     * @return
     */
    public boolean getPassWordLogin(){
        return  getBoolean("passWordLogin");
    }

    /**
     * 保存登录token
     * @param token
     */
    public  void setToken(String token) {
        putString("token", token);
    }

    /**
     * 获取登录token
     * @return
     */
    public String getToken() {
        return getString("token");
    }

    /**
     * 保存融云token
     * @param token
     */
    public  void setRongIMToken(String token) {
        putString(AppConst.RONG_YUN_TOKEN, token);
    }

    /**
     * 获取融云token
     * @return
     */
    public String getRongIMToken() {
        return getString(AppConst.RONG_YUN_TOKEN);
    }

    /**
     * 保存当前用户在融云的昵称
     * @param token
     */
    public  void setRongIMLoginName(String token) {
        putString(AppConst.RONG_IM_LOGIN_NAME, token);
    }

    /**
     * 获取当前用户在融云的昵称
     * @return
     */
    public String getRongIMLoginName() {
        return getString(AppConst.RONG_IM_LOGIN_NAME);
    }

    /**
     * 保存当前用户在融云的userId
     * @param token
     */
    public  void setRongIMUserId(String token) {
        putString(AppConst.RONG_IM_USER_ID, token);
    }

    /**
     * 获取当前用户在融云的userId
     * @return
     */
    public String getRongIMUserId() {
        return getString(AppConst.RONG_IM_USER_ID);
    }

    /**
     * 保存当前用户在融云的头像
     * @param token
     */
    public  void setRongIMPortrait(String token) {
        putString(AppConst.RONG_IM_LOGIN_PORTRAIT, token);
    }

    /**
     * 获取当前用户在融云的头像
     * @return
     */
    public String getRongIMPortrait() {
        return getString(AppConst.RONG_IM_LOGIN_PORTRAIT);
    }


    /**
     * 保存账号密码
     * @param PassWord
     */
    public  void setPassWord(String PassWord) {
        putString("PassWord", PassWord);
    }

    /**
     * 获取账号密码
     * @return
     */
    public String getPassWord() {
        return getString("PassWord");
    }
    /**
     * 保存用户id
     * @param userId
     */
    public  void setUserId(String userId) {
        putString("userId", userId);
    }

    /**
     * 获取用户id
     * @return
     */
    public String getUserId() {
        return getString("userId");
    }

    /**
     * 保存用户是否设置过支付密码
     * @param password
     */
    public  void setPayPassword(String password) {
        putString("pay_password",password);
    }

    /**
     * 获取用户是否设置过支付密码
     * @return
     */
    public String getPayPassword() {
        return getString("pay_password");
    }


    /**
     * 保存用戶是否被邀请过的状态
     * @param userMsg
     */
    public  void setUserYaoQing(String userMsg) {
        putString("userYaoQ",userMsg);
    }

    /**
     * 获取用戶是否被邀请过的状态
     * @return
     */
    public String getUserYaoQing() {
        return getString("userYaoQ");
    }
    /**
     * 保存用戶的所有信息
     * @param userMsg
     */
    public  void setUserMsg(String userMsg) {
        putString("userMsg",userMsg);
    }

    /**
     * 获取用戶的所有信息
     * @return
     */
    public String getUserMsg() {
        return getString("userMsg");
    }

    /**
     * 保存邀请码
     * @return
     */
    public  void  setInvite(String invite) {
        putString("invite", invite);
    }
    /**
     * 获取邀请码
     * @return
     */
    public  String  getInvite(){
        return getString("invite");
    }
    /**
     * 保存引导页启动状态
     * @param firstLaunch
     */
    public  void setFirstLaunch(boolean firstLaunch) {
        putBoolean("firstLaunch", firstLaunch);
    }
    /**
     * 获取引导页启动状态
     * @return
     */
    public boolean getFirstLaunch() {
        return getBoolean("firstLaunch");
    }
    /**
     * 保存微信同步的信息
     */
    public   void setWechateJson(String json) {
        putString("wechat",json);
    }

    /**
     * 获取微信同步的信息
     * @return
     */
    public   String getWechateJson()
    {
        return  getString("wechat");
    }


    /**
     * 全局设置控件是否显示的问题
     * @param review
     */
    public  void setIsReview(String review) {
        putString("isreview",review);
    }

    /**
     * @return
     */
    public  String getIsReview()
    {
        return getString("isreview");
    }



    /**
     * 广告页图片路径
     * @param review
     */
    public   void setStartUrl(String review) {
        putString("surl",review);
    }

    /**
     * @return
     */
    public   String getStartUrl()
    {
        return  getString("surl");
    }

    /**
     * 广告页面是否显示
     * @param review
     */
    public  void setAdShow(String review) {
        putString("ashow",review);
    }

    /**
     * @return
     */
    public  String getAdShow()
    {
        return  getString("ashow");
    }

    /**
     * 保存手机号
     * @param Mobile
     */
    public  void saveMobile(String Mobile) {
        putString("Mobile",Mobile);
    }

    /**
     * @return
     */
    public  String getMobile()
    {
        return  getString("Mobile");
    }
    /**
     * 保存支付状态
     * @param PayType
     */
    public  void savePayType(String PayType) {
        putString("PayType",PayType);
    }

    /**
     * @return
     */
    public  String getPayType()
    {
        return  getString("PayType");
    }
    /**
     * 保存极光消息
     * @param Msm
     */
    public  void saveJGMsm(String Msm) {
        putString("Msm",Msm);
    }

    /**
     * @return
     */
    public  String getJGMsm()
    {
        return  getString("Msm");
    }

    /**
     * 保存用户头像
     * @param headIcon
     */
    public  void saveHeadIcon(String headIcon) {
        putString("headIcon",headIcon);
    }

    /**
     * @return
     */
    public  String getHeadIcon()
    {
        return  getString("headIcon");
    }


    /**
     * 是否实名
     * @return
     */
    public  String getIsTrueName()
    {
        return  getString("trueName");
    }


    /**
     * 保存是否实名
     * @param IsTrueName
     */
    public  void saveIsTrueName(String IsTrueName) {
        putString("trueName",IsTrueName);
    }

    /**
     * 获取城市名字
     * @return
     */
    public  String getCitysName()
    {
        return  getString("cityName");
    }


    /**
     * 保存城市名字
     * @param cityName
     */
    public  void savecityName(String cityName) {
        putString("cityName",cityName);
    }


    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        putString(tag,strJson);

    }


    /**
     * 获取List
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> datalist=new ArrayList<T>();
        String strJson = getString(tag);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }

    /**
     * 获取经度
     * @return
     */
    public  Double getLo()
    {
        if(TextUtils.isEmpty(getString("lo"))){
            return  null;
        }else {
            double d = new Double(getString("lo"));
            return d;
        }
    }


    /**
     * 保存经度
     * @param Lo
     */
    public  void saveLo(String Lo) {
        putString("lo",Lo);
    }


    /**
     * 获取维度
     * @return
     */
    public  Double getLa()
    {
        if(TextUtils.isEmpty(getString("la"))){
            return  null;
        }else {
            double d = new Double(getString("la"));
            return d;
        }    }


    /**
     * 保存维度
     * @param La
     */
    public  void saveLa(String La) {
        putString("la",La);
    }



    /*********************************************************************************************/

}
