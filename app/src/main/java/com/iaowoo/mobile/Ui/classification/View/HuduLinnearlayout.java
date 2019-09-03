package com.iaowoo.mobile.Ui.classification.View;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.iaowoo.mobile.R;

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
 * @Description: ${todo}(圆弧控件自定义)
 * @date 2019/1/17
 * @email ${18011009889@163.com}
 */
public class HuduLinnearlayout extends View {
    private Paint mPaint;
    /**
     * 圆的宽度
     */
    private int mCircleWidth = 8;

    public HuduLinnearlayout(Context context) {
        super(context);
    }

    public HuduLinnearlayout(Context context, AttributeSet attrs) {
        super(context);
    }

    public HuduLinnearlayout(Context context, AttributeSet attrs, int defStyle) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();

        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(Color.RED);

        /**
         * 这是一个居中的圆
         */
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF oval = new RectF( x, y, getWidth() - x, getHeight() - y);

        canvas.drawArc(oval,360,140,true,mPaint);
    }
}
