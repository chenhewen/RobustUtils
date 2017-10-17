package com.robust.robustutilslib.dialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.robust.robustutilslib.SimpleAnimationListener;

/**
 * 评分引导的星星对应的自定义View
 * Created by chenhewen on 2017/10/10.
 */

public class RateStarView extends LinearLayout {

    private boolean mInited;

    private int mRateIndex = -1;

    private View[] mRateStars;

    public RateStarView(Context context) {
        super(context);
    }

    public RateStarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RateStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        if (mInited) {
            return;
        }

        int childCount = getChildCount();
        mRateStars = new View[childCount];
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            mRateStars[i] = child;
        }
        mInited = true;
    }

    private Animation createAppearAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(600);
        animationSet.setFillAfter(true);
        ScaleAnimation animation1 = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ScaleAnimation animation2 = new ScaleAnimation(1f, 0.7f, 1f, 0.7f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation2.setStartOffset(300);
        animationSet.addAnimation(animation1);
        animationSet.addAnimation(animation2);

        return animationSet;
    }

    private void startStarAnimation(final View view, int index) {
        float fraction = 0.3f;
        Animation animation = createAppearAnimation();
        long duration = animation.getDuration();
        float startOffset = duration * index * fraction;
        animation.setStartOffset((long) startOffset);
        //由于OnAnimationStart同时启动， 这里使用post方法实现
        postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setSelected(true);
            }
        }, animation.getStartOffset());
        if (index == mRateStars.length - 1) {
            //最后一个
            animation.setAnimationListener(new SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    //将所有星星至灰
                    updateStars(-1, 500);
                }
            });
        }

        view.startAnimation(animation);
    }

    /**
     * 展示五星动画
     */
    public void show() {
        init();
        for (int i = 0; i < mRateStars.length; i++) {
            View rateStar = mRateStars[i];
            rateStar.setSelected(false);
            rateStar.setTag(i);
            rateStar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRateIndex = (Integer) v.getTag();
                    updateStars(mRateIndex, 0);
                }
            });
            startStarAnimation(rateStar, i);
        }
    }

    private void updateStars(final int index, long delayMillis) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mRateStars.length; i++) {
                    View rateStar = mRateStars[i];
                    if (i <= index) {
                        rateStar.setSelected(true);
                    } else {
                        rateStar.setSelected(false);
                    }

                }
            }
        }, delayMillis);
    }

    /**
     * 获取评分数
     * @return (0, 1, 2, 3, 4, 5)中一个
     */
    public int getRating() {
        return mRateIndex + 1;
    }
}
