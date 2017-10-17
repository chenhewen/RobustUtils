package com.robust.robustutilslib.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.robust.robustutilslib.view.WrapContentHeightViewPager;

/**
 * Created by chenhewen on 2017/10/10.
 */

public class DialogViewPager extends WrapContentHeightViewPager {

    public DialogViewPager(Context context) {
        super(context);
    }

    public DialogViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
