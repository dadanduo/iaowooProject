package com.iaowoo.mobile.Ui.classification.View.gallerlib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;

import java.util.List;

public class GallerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner.BodyBean.ContentBean.ListBean> banners;
    public GallerAdapter(Context context,List<Banner.BodyBean.ContentBean.ListBean> banners){
        this.context=context;
        this.banners=banners;
    }

    @Override
    public int getCount() {
        return 10000;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= banners.size();
        if (position<0) {
            position = banners.size()+ position;
        }
        View  view= LayoutInflater.from(context).inflate(R.layout.item_image, null);
        ImageView imageView=view.findViewById(R.id.image);
        GlideUtils g=new GlideUtils();
        g.glides(context,banners.get(position).getBannerImg(),imageView);
        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



}
