package com.robust.robustutilslib.dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robust.robustutilslib.util.RobustLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhewen on 2017/10/10.
 */

public class RateDialogContainer {

    private Dialog mDialog;
    private ViewPager mViewPager;
    private TextView mCancelBtn;
    private TextView mOkBtn;
    private ChoiceNode mChoiceNode;
    private List<ChoiceNode> mChoiceNodeList = new ArrayList<ChoiceNode>();
    private PagerAdapterPrivate mPagerAdapterPrivate;

    public RateDialogContainer(Dialog dialog, ViewPager viewPager, TextView cancelBtn, TextView okBtn) {
        mDialog = dialog;
        mViewPager = viewPager;
        mCancelBtn = cancelBtn;
        mOkBtn = okBtn;
    }

    private class PagerAdapterPrivate extends PagerAdapter {

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mChoiceNodeList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ChoiceNode choiceNode = mChoiceNodeList.get(position);
            View body = choiceNode.getBody();
            container.addView(body);
            return body;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    public void onCreate(ChoiceNode root) {
        mChoiceNode = root;
        mChoiceNodeList.add(root);
        mPagerAdapterPrivate = new PagerAdapterPrivate();
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Runnable onShownAction = mChoiceNode.getOnShownAction();
                doOnShownAction(onShownAction);
            }
        });
        mViewPager.setAdapter(mPagerAdapterPrivate);

        updateButtons();
    }

    private void updateButtons() {
        try {
            mCancelBtn.setText(mChoiceNode.getCancelTextRes());
        } catch (Resources.NotFoundException e) {
            RobustLog.log(e.getMessage());
        }
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceNode cancelNode = mChoiceNode.getCancelNode();
                if (cancelNode != null) {
                    showNextNode(cancelNode, mPagerAdapterPrivate);
                } else {
                    doBtnAction(mChoiceNode.getCancelAction());
                }
            }
        });

        try {
            mOkBtn.setText(mChoiceNode.getOkTextRes());
        } catch (Resources.NotFoundException e) {
            RobustLog.log(e.getMessage());
        }
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceNode okNode = mChoiceNode.getOkNode();
                if (okNode != null) {
                    showNextNode(okNode, mPagerAdapterPrivate);
                } else {
                    doBtnAction(mChoiceNode.getOkAction());
                }
            }
        });
    }

    public void onDestroy() {
        mDialog = null;
    }

    private void showNextNode(ChoiceNode node, PagerAdapter pagerAdapter) {
        mChoiceNode = node;
        mChoiceNodeList.add(node);
        pagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        updateButtons();
    }

    private void doBtnAction(Runnable action) {
        if (action != null) {
            action.run();
        } else {
            mDialog.dismiss();
        }
    }

    private void doOnShownAction(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public ViewGroup getView() {
        return mViewPager;
    }
}
