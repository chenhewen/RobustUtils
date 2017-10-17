package com.robust.robustutilslib.dialog;

import android.app.Dialog;
import android.view.ViewGroup;

/**
 * Created by chenhewen on 2017/10/10.
 */

public abstract class AbsDialogConfig {

    ///////////////////////////////////////////////////////////////////////////
    // createNode的命名规则
    // createNode0 表示根节点
    // createNode00 表示根节点的叶子节点
    // createNode01 表示根节点额叶子节点
    // ... ...
    // createNode000 表示... ...
    // createNode001 ... ...
    // ... ...
    // ... ...
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 创建节点树
     * @param dialog
     * @param bodyContainer
     * @return 返回根节点
     */
    public abstract ChoiceNode makeNodes(Dialog dialog, ViewGroup bodyContainer);
}
