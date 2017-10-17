package com.robust.robustutilslib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robust.robustutilslib.R;


/**
 * 五星评分引导的对话框
 * Created by chenhewen on 2017/10/9.
 */

public abstract class AbsDialog extends Dialog {

    private ImageView mIcon;
    private TextView mTitle;
    private ViewGroup mBody;
    private TextView mCancelBtn;
    private TextView mOkBtn;
    private ViewPager mBodyViewPager;
    private RateDialogContainer mRateDialogContainer;
    //    private RateStarView mStarView;

    public AbsDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AbsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public AbsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_rate);
        mIcon = (ImageView) findViewById(R.id.dialog_icon);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mBody = (ViewGroup) findViewById(R.id.dialog_body);
        mCancelBtn = (TextView) findViewById(R.id.dialog_btn_cancel);
        mOkBtn = (TextView) findViewById(R.id.dialog_btn_ok);
        mBodyViewPager = (ViewPager) findViewById(R.id.dialog_body_view_pager);
        initBody(this);

        mIcon.setVisibility(View.GONE);
    }

    private View initBody(Dialog dialog) {
        mRateDialogContainer = new RateDialogContainer(dialog, mBodyViewPager, mCancelBtn, mOkBtn);
        ViewGroup bodyView = mRateDialogContainer.getView();
        AbsDialogConfig config = getDialogConfig();
        ChoiceNode root = config.makeNodes(dialog, bodyView);
        mRateDialogContainer.onCreate(root);
        return bodyView;
    }

    public abstract AbsDialogConfig getDialogConfig();

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mRateDialogContainer.onDestroy();
    }
}
