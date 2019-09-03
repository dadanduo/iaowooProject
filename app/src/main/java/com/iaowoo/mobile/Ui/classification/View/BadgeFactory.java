package com.iaowoo.mobile.Ui.classification.View;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import com.iaowoo.mobile.R;

/**
 * Created by Allen Liu on 2016/7/15.
 */
public class BadgeFactory {
    public static BadgeView createDot1(Context context){
        return  new BadgeView(context).setWidthAndHeight(0,0).setTextSize(0).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_CIRCLE);
    }
    public static BadgeView createDot(Context context){
        return  new BadgeView(context).setWidthAndHeight(10,10).setTextSize(0).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_CIRCLE);
    }
    public static BadgeView createCircle(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(8).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_CIRCLE);
    }
    public static BadgeView createRectangle(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(8).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_RECTANGLE);
    }
    public static BadgeView createOval(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(8).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_OVAL);
    }
    public static BadgeView createSquare(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(8).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_SQUARE);
    }
    public static BadgeView createRoundRect(Context context){
        return  new BadgeView(context).setWidthAndHeight(15 ,15).setTextSize(8).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPTE_ROUND_RECTANGLE);
    }
    public static BadgeView createOvalBig(Context context){
        return  new BadgeView(context).setWidthAndHeight(20,20).setTextSize(13).setBadgeGravity(Gravity.RIGHT| Gravity.BOTTOM).setShape(3);
    }
    public static BadgeView createOvalBig1(Context context){
        return  new BadgeView(context).setWidthAndHeight(20,20).setTextSize(13).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(1);
    }
    public static BadgeView createOvalMy(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(8).setBadgeBackground(ContextCompat.getColor(context,R.color.my_image)).setTextColor(ContextCompat.getColor(context,R.color.white)).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(BadgeView.SHAPE_OVAL);
    }
    public static BadgeView createOvalBig2(Context context){
        return  new BadgeView(context).setWidthAndHeight(15,15).setTextSize(10).setBadgeGravity(Gravity.RIGHT| Gravity.TOP).setShape(1);
    }
    public static BadgeView create(Context context){
        return  new BadgeView(context);
    }

}
