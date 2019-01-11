package com.iaowoo.mobile.Ui.classification.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.LogPrint;

import java.util.Random;

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
 * @Description: ${todo}(点赞)
 * @date 2018/10/31
 * @email ${18011009889@163.com}
 */
public class Love extends RelativeLayout {
    private Context mContext;

    private int count = 0;//点击次数
    private long firstClick = 0;//第一次点击时间
    private long secondClick = 0;//第二次点击时间
    private final int totalTime = 500;
    private LoveCallBack loveCallBack;

    public interface LoveCallBack{
        void ClickLove();
    }

    public void setOnItemClick(LoveCallBack loveCallBack) {
        this.loveCallBack=loveCallBack;
    }

    /**
     * 两次点击时间间隔，单位毫秒
     */

    float[] num = {-30, -20, 0, 20, 30};//随机心形图片角度

    public Love(Context context) {
        super(context);
        initView(context);
    }

    public Love(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Love(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        ImageView imageView = new ImageView(mContext);
        LayoutParams params = new LayoutParams(100, 100);
        params.leftMargin = getWidth() - 200;
        params.topMargin = getHeight() / 2 - 300;
//        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.logo));
        imageView.setLayoutParams(params);
        addView(imageView);

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "这里是点击爱心的动画，待展示", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            count++;
            if (1 == count) {
                firstClick = System.currentTimeMillis();//记录第一次点击时间
            } else if (2 == count) {
                secondClick = System.currentTimeMillis();//记录第二次点击时间
                if (secondClick - firstClick < totalTime) {//判断二次点击时间间隔是否在设定的间隔时间之内
                    LogPrint.printError("双击");
                    loveCallBack.ClickLove();
                    final ImageView imageView = new ImageView(mContext);
                    LayoutParams params = new LayoutParams(300, 300);
                    params.leftMargin = (int) event.getX() - 150;
                    params.topMargin = (int) event.getY() - 300;
                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.give_love_icon));
                    imageView.setLayoutParams(params);
                    addView(imageView);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(scale(imageView, "scaleX", 2f, 0.9f, 100, 0))
                            .with(scale(imageView, "scaleY", 2f, 0.9f, 100, 0))
                            .with(rotation(imageView, 0, 0, num[new Random().nextInt(4)]))
                            .with(alpha(imageView, 0, 1, 100, 0))
                            .with(scale(imageView, "scaleX", 0.9f, 1, 50, 150))
                            .with(scale(imageView, "scaleY", 0.9f, 1, 50, 150))
                            .with(translationY(imageView, 0, -600, 800, 400))
                            .with(alpha(imageView, 1, 0, 300, 400))
                            .with(scale(imageView, "scaleX", 1, 3f, 700, 400))
                            .with(scale(imageView, "scaleY", 1, 3f, 700, 400));

                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            removeViewInLayout(imageView);
                        }
                    });
                    count = 0;
                    firstClick = 0;
                } else {
                    firstClick = secondClick;
                    count = 1;
                }
                secondClick = 0;
            }
        }
        return super.onTouchEvent(event);
    }

    public static ObjectAnimator scale(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , propertyName
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator translationX(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationX"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator translationY(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationY"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "alpha"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator rotation(View view, long time, long delayTime, float... values) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", values);
        rotation.setDuration(time);
        rotation.setStartDelay(delayTime);
        rotation.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        return rotation;
    }
}
