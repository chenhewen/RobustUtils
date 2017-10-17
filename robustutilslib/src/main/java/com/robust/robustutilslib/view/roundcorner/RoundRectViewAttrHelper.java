package com.robust.robustutilslib.view.roundcorner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.robust.robustutilslib.R;

/**
 * Created by chenhewen on 2017/9/21.
 */

public class RoundRectViewAttrHelper {

    private RoundRectViewDecorator.RoundRectView mRoundRectView;

    public RoundRectViewAttrHelper(RoundRectViewDecorator.RoundRectView roundRectView) {
        mRoundRectView = roundRectView;
    }

    public void helpSetAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedValue typedValue = new TypedValue();
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RobustRoundView);
            typedArray.getValue(R.styleable.RobustRoundView_radius, typedValue);
            if (typedValue.type == TypedValue.TYPE_DIMENSION) {
                float dimension = typedValue.getDimension(context.getResources().getDisplayMetrics());
                mRoundRectView.setRoundRadius(dimension);
            } else if (typedValue.type == TypedValue.TYPE_FRACTION) {
                final float fraction = typedValue.getFraction(1, 1);
                asView().post(new Runnable() {
                    @Override
                    public void run() {
                        float f = Math.min(fraction, 0.5f);
                        float radius = asView().getHeight() * f;
                        mRoundRectView.setRoundRadius(radius);
                    }
                });
            }

            float borderStroke = typedArray.getDimension(R.styleable.RobustRoundView_borderWidth, 0);
            int borderColor = typedArray.getColor(R.styleable.RobustRoundView_borderColor, 0x00000000);
            mRoundRectView.setStroke(borderStroke, borderColor);

            typedArray.recycle();
        }
    }

    private View asView() {
        return (View) mRoundRectView;
    }
}
