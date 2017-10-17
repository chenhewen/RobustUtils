package com.robust.robustutilslib.view.roundcorner;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.view.View;

/**
 * 用于实现圆角效果的辅助类<br>
 * 由于实际布局中可能用到各种(View)layout, 可以利用这个类实现<br>
 * 我就不实现这么多了，目前以下layout实现:<br>
 * 1. @see {@link RoundFrameLayout} <br>
 * 2. @see {@link RoundLinearLayout}<br>
 * 3. @see {@link RoundRelativeLayout}<br>
 * 通常情况下任意布局外层加多个FrameLayout就可以得到斜角效果，<br>
 * 但如果你不想外局加多额外的layout, 那么就根据你使用的layout实现一个对应的View(layout)吧.<br>
 * 
 * @author laojiale
 *
 */
public class RoundRectViewDecorator {

	private Paint mMaskPaint;
	private Xfermode mXfermode = new PorterDuffXfermode(Mode.DST_IN);
	private Bitmap mMaskBitmap;
	private final Paint mLayerPaint = new Paint();
	// 圆角的半径
	private float mRoundRadius;

	private RoundRectView mView;

	private Paint mBorderPaint;
	private float mStrokeWidth;
	private int mStrokeColor;
	private RectF mBorderRectF;

	public RoundRectViewDecorator(RoundRectView view) {
		if (!View.class.isInstance(view)) {
			throw new IllegalArgumentException(
					"view is not a View - -b, you know what I mean, don\'t you?");
		}
		mView = view;
		asView().setWillNotDraw(false);

		mMaskPaint = new Paint();
		mMaskPaint.setAntiAlias(true);
		mMaskPaint.setFilterBitmap(true);
		mMaskPaint.setColor(0xFFFFFFFF);

		final float density = asView().getResources().getDisplayMetrics().density;
		mRoundRadius = density * 10; // 默认10dp


		mStrokeColor = 0xFFAAB2BD;
		mStrokeWidth = 0;

		mBorderPaint = new Paint();
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setColor(mStrokeColor);
		mBorderPaint.setStrokeWidth(mStrokeWidth);
		mBorderPaint.setStrokeJoin(Paint.Join.ROUND);
	}

	private void updateMask() {
		final View view = asView();
		if (view.getWidth() == 0 || view.getHeight() == 0) {
			return;
		}
		if (mRoundRadius <= 0) {
			return;
		}
		if (mMaskBitmap != null && !mMaskBitmap.isRecycled()) {
			mMaskBitmap.recycle();
			mMaskBitmap = null;
		}
		try {
			mMaskBitmap = Bitmap.createBitmap(view.getWidth(),
					view.getHeight(), Config.ARGB_8888);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (mMaskBitmap != null) {
			final Canvas canvas = new Canvas(mMaskBitmap);
			canvas.drawRoundRect(new RectF(0, 0, mMaskBitmap.getWidth(),
					mMaskBitmap.getHeight()), mRoundRadius, mRoundRadius,
					mMaskPaint);
			//外边框
			float inner = mStrokeWidth / 2;
			mBorderRectF = new RectF(inner, inner, mMaskBitmap.getWidth() - inner, mMaskBitmap.getHeight() - inner);
		}
	}

	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		updateMask();
	}

	public void draw(Canvas canvas) {
		if (mMaskBitmap != null) {
			final View view = asView();
			final int sc = canvas.saveLayer(0, 0, view.getWidth(),
					view.getHeight(), mLayerPaint, Canvas.ALL_SAVE_FLAG);
			mView.superDraw(canvas);
			mMaskPaint.setXfermode(mXfermode);
			canvas.drawBitmap(mMaskBitmap, 0, 0, mMaskPaint);
			mMaskPaint.setXfermode(null);

			if (mBorderRectF != null) {
				float roundRadius = mRoundRadius - mStrokeWidth / 2;
				canvas.drawRoundRect(mBorderRectF, roundRadius, roundRadius, mBorderPaint);
			}

			canvas.restoreToCount(sc);
		} else {
			mView.superDraw(canvas);
		}
	}

	private View asView() {
		return (View) mView;
	}

	/**
	 * 设置圆角半径
	 * 
	 * @param radius
	 */
	public void setRoundRadius(float radius) {
		if (mRoundRadius == radius) {
			return;
		}
		mRoundRadius = radius;
		updateMask();
		asView().invalidate();
	}

	public void setStroke(float width, int color) {
		if (mStrokeWidth == width && mStrokeColor == color) {
			return;
		}

		mStrokeWidth = width;
		mStrokeColor = color;
		mBorderPaint.setStrokeWidth(width);
		mBorderPaint.setColor(color);
		updateMask();
		asView().invalidate();
	}

	/**
	 * RoundRectView
	 * 
	 * @author laojiale
	 */
	public interface RoundRectView {

		public void superDraw(Canvas canvas);

		/**
		 * 设置圆角半径
		 * 
		 * @param radius
		 */
		public void setRoundRadius(float radius);

		/**
		 * 设置border
		 * @param strokeWidth
		 * @param strokeColor
		 */
		public void setStroke(float strokeWidth, int strokeColor);
	}

}
