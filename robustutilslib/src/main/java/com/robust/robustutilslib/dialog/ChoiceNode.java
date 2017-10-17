package com.robust.robustutilslib.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.robust.robustutilslib.R;
import com.robust.robustutilslib.Robust;


/**
 * Created by chenhewen on 2017/10/10.
 */

public class ChoiceNode {

    private ChoiceNode mCancelNode;

    private ChoiceNode mOkNode;

    private View mBody;

    private int mCancelTextRes;

    private int mOkTextRes;

    private int mMaxLength;

    private Runnable mCancelAction;
    private Runnable mOkAction;
    private Runnable mOnShownAction;

    public ChoiceNode getCancelNode() {
        return mCancelNode;
    }

    public void setCancelNode(ChoiceNode cancelNode) {
        if (cancelNode != null) {
            int maxLength = cancelNode.getMaxLength() + 1;
            mMaxLength = Math.max(maxLength, mMaxLength);
            mCancelNode = cancelNode;
        }
    }

    public ChoiceNode getOkNode() {
        return mOkNode;
    }

    public void setOkNode(ChoiceNode okNode) {
        if (okNode != null) {
            int maxLength = okNode.getMaxLength() + 1;
            mMaxLength = Math.max(maxLength, mMaxLength);
            mOkNode = okNode;
        }
    }

    public View getBody() {
        return mBody;
    }

    public void setBody(View body) {
        mBody = body;
    }

    public void setBody(int textResId) {
        Context appContext = Robust.getAppContext();
        LayoutInflater inflater = LayoutInflater.from(appContext);
        TextView textView = (TextView) inflater.inflate(R.layout.dialog_rate_body_text, null);
        textView.setText(textResId);
        mBody = textView;
    }

    public int getCancelTextRes() {
        return mCancelTextRes;
    }

    public void setCancelTextRes(int cancelTextRes) {
        mCancelTextRes = cancelTextRes;
    }

    public int getOkTextRes() {
        return mOkTextRes;
    }

    public void setOkTextRes(int okTextRes) {
        mOkTextRes = okTextRes;
    }

    public int getMaxLength() {
        if (mCancelNode == null && mOkNode == null) {
            //叶子节点
            return 1;
        }

        return mMaxLength;
    }

    public Runnable getCancelAction() {
        return mCancelAction;
    }

    public void setCancelAction(Runnable cancelAction) {
        mCancelAction = cancelAction;
    }

    public Runnable getOkAction() {
        return mOkAction;
    }

    public void setOkAction(Runnable okAction) {
        mOkAction = okAction;
    }

    public Runnable getOnShownAction() {
        return mOnShownAction;
    }

    public void setOnShownAction(Runnable onShownAction) {
        mOnShownAction = onShownAction;
    }
}
