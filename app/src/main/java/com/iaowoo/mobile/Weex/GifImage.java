package com.iaowoo.mobile.Weex;

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
 * @Description: ${todo}()
 * @date 2019/1/11
 * @email ${18011009889@163.com}
 */
import android.content.Context;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by neo
 */
public class GifImage extends WXComponent<GifImageView> {

    GifImageView gifview;
    Context context;
    public GifImage(WXSDKInstance instance, WXVContainer parent, boolean isLazy, BasicComponentData basicComponentData) {
        super(instance, parent, isLazy,basicComponentData);
    }
    @Override
    protected GifImageView initComponentHostView(Context context) {
        gifview = new GifImageView(context);
        this.context = context;
        return gifview;
    }

    @WXComponentProp(name = "src")//该注解，则为weex中调用的方法名
    public void setSrc(String src){
        try{
            GifDrawable gifFromAssets = new GifDrawable( context.getAssets(), src);
            gifview.setImageDrawable(gifFromAssets);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
