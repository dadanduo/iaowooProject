package com.iaowoo.mobile.Utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.iaowoo.mobile.R;

/**
 * Created by chenda on 2018/4/19.
 */

public class AnimationUtils {

    public static  void  anima(Context context, ImageView imageView)
    {
        Animation operatingAnim = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotate_anim);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            imageView.startAnimation(operatingAnim);
        }
    }
}
