package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Utils.ScreenAdaptationUtils;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.ScreenAdaptationUtils;

import java.lang.ref.SoftReference;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
 * @Description: ${todo}(引导页adapter)
 * @date 2018/8/25
 * @email ${18011009889@163.com}
 */
public class StartPageAdapter extends PagerAdapter {
    private Context context=null;
    private LayoutInflater layoutInflater;
    private int[] layouts;
    /**
     * 软引用
     */
    private SoftReference<ScreenAdaptationUtils> screenAdaptationUtils=null;
    private StartCallBack startCallBack=null;
    public StartPageAdapter(Context context, int[] layouts, StartCallBack startCallBack){
        this.context=context;
        this.layouts=layouts;
        this.startCallBack=startCallBack;
        screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context. getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layouts[position],container,false);
        container.addView(view);
        ImageView guide_image=view.findViewById(R.id.guide_image);
        RelativeLayout express_re=view.findViewById(R.id.express_re);
        express_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCallBack.launchHomeScreen();
            }
        });
        int height= SingleOverAll.getInstance().deviceHeight(context);
        switch (position) {
            case 0:
                if(screenAdaptationUtils.get()!=null) {
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_first);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 0);
                }else{
                    screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_first);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 0);
                }
                break;
            case 1:
                if(screenAdaptationUtils.get()!=null) {
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_second);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 1);
                }else{
                    screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_second);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 1);
                }
                break;
            case 2:
                if(screenAdaptationUtils.get()!=null) {
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_third);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 2);
                }else{
                    screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_third);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 2);
                }
                break;
            case 3:
                if(screenAdaptationUtils.get()!=null) {
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_fourth);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 3);
                }else{
                    screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
                    if (!screenAdaptationUtils.get().screenAdapter(height))
                        guide_image.setImageResource(R.mipmap.guide_page_fourth);
                    else
                        screenAdaptationUtils.get().screenAdapterDealWith(guide_image, height, 3);
                }
                break;
            default:
                break;
        }
        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
    }
    public interface  StartCallBack{
        void  launchHomeScreen();
    }
}
