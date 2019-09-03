package com.iaowoo.mobile.Utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class UtilsTimer {
    private  int  nValue=0;
    private   Timer timer;
    private   TimerTask task;
    /**
     * 宏定义，表示是什么消息
     */
    public final   int REFRESH = 0x01;
    public final  int MESSAGECYCLE=0X02;
    private int time3;
    private Button textView;
    private boolean isHaveB=false;
    private  UtilsTimerCallBack utilsTimerCallBack;
    public UtilsTimer(UtilsTimerCallBack utilsTimerCallBack) {
        this.utilsTimerCallBack=utilsTimerCallBack;
    }
    public UtilsTimer() {

    }
    public UtilsTimer(Button show,UtilsTimerCallBack utilsTimerCallBack) {
        this.textView=show;
        this.utilsTimerCallBack=utilsTimerCallBack;
        isHaveB=true;
    }

    public void setInterface(UtilsTimerCallBack utilsTimerCallBack) {
        this.utilsTimerCallBack=utilsTimerCallBack;
    }
    public void goOn(int time1,int time2,int time3) {
        startTime();
        this.time3=time3;
        //开启定时器
        timer.schedule(task,time1,time2);
    }
    private void startTime() {
        //定时器设置
        timer = new Timer();
        task = new TimerTask()
        {
            @Override
            public void run()
            {
                nValue++;
                Message message = new Message();
                message.what = REFRESH;
                mhandler.sendMessage(message);
            }
        };
    }
    /**
     * 处理消息
     */
    @SuppressLint("HandlerLeak")
    private  Handler mhandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case REFRESH:
                    if(isHaveB) {
                        textView.setText("跳过 " + (time3-nValue-1));
                    }
                    if(nValue>=time3)
                    {
                        utilsTimerCallBack.timeOver();
                        destoryTimer();
                    }
                    break;
                case MESSAGECYCLE:
                    utilsTimerCallBack.timeOver();
                default:
                    break;
            }
        }
    };

    public  void destoryTimer() {
        if(task!=null){
            task.cancel();
            LogPrint.printError("时间线程task关闭");
        }
        if(timer!=null){
            timer.cancel();
            timer.purge();
            LogPrint.printError("时间线程关闭");
        }
    }

    public void cycle(int time1,int time2) {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                Message message = new Message();
                message.what =MESSAGECYCLE;
                mhandler.sendMessage(message);
            }
        }, time1,time2);

    }

    class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000); //睡2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
//            tv.setText("异步更新TextView内容");
        }

    }

    public interface  UtilsTimerCallBack{
        void timeOver();
    }
}
