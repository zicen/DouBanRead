package com.zhenquan.doubanread.wight;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zhenquan.doubanread.R;
import com.zhenquan.doubanread.base.BaseActivity;


public class MyProgressDialog extends Dialog {

    private Context context;
    private String progressText;
    public boolean cancelable = true;
    public boolean isShowed = false;//是否显示过，没有显示过的时候还没有调用onCreate()方法

    public MyProgressDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    public MyProgressDialog(Context context, String progressText) {
        super(context, R.style.dialog);
        this.context = context;
        this.progressText = progressText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
//        ImageView iv_progress = (ImageView) findViewById(R.id.iv_progress);
//        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
//        rotateAnimation.setDuration(3000);
//        rotateAnimation.setInterpolator(new LinearInterpolator());
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        iv_progress.startAnimation(rotateAnimation);
        GifView gif = (GifView) findViewById(R.id.iv_progress);
        gif.setMovieResource(R.drawable.loading);
        TextView title = (TextView) findViewById(R.id.custom_imageview_progress_title);
        title.setText(progressText == null ? "加载中..." : progressText);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0 && isShowing() && !cancelable){
            ((BaseActivity)context).finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setMessage(String message) {
        if(!isShowed){
            onCreate(onSaveInstanceState());
        }
        TextView title = (TextView) findViewById(R.id.custom_imageview_progress_title);
        title.setText(message);
    }

    @Override
    public void show() {
        if (this!=null && !isShowing() && context != null) {
            isShowed = true;
            super.show();
        }
    }

}
