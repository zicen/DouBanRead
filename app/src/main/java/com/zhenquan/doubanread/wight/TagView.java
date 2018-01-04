package com.zhenquan.doubanread.wight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.zhenquan.doubanread.R;

/**
 * Created by ry on 2018/1/4.
 */

public class TagView extends android.support.v7.widget.AppCompatTextView {
    public TagView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.setTextColor(getResources().getColor(R.color.white));
        this.setBackgroundColor(getResources().getColor(R.color.book_tag_color));
        this.setPadding(20,20,20,20);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);
        this.setLayoutParams(lp);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
}
