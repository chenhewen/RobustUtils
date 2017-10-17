package com.robust.robustutilslib.view.roundcorner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * 提供圆角效果的FrameLayout<br>
 * 
 * @author laojiale
 *
 */
public class RoundFrameLayout extends FrameLayout implements RoundRectViewDecorator.RoundRectView {

	private RoundRectViewDecorator mDecorator;

	private RoundRectViewAttrHelper mAttrHelper;

	public RoundFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public RoundFrameLayout(Context context) {
		super(context);
		init(null);
	}

	public RoundFrameLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	private void init(AttributeSet attributeSet) {
		mDecorator = new RoundRectViewDecorator(this);
		mAttrHelper = new RoundRectViewAttrHelper(this);
		mAttrHelper.helpSetAttr(getContext(), attributeSet);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mDecorator.onSizeChanged(w, h, oldw, oldh);
	}


	@Override
	public void draw(Canvas canvas) {
		mDecorator.draw(canvas);
	}

	@Override
	public void superDraw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	public void setRoundRadius(float radius) {
		mDecorator.setRoundRadius(radius);
	}

	@Override
	public void setStroke(float strokeWidth, int strokeColor) {

	}
}
