package com.robust.robustutilslib.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.robust.robustutilslib.R;
import com.robust.robustutilslib.Robust;
import com.robust.robustutilslib.RobustGlobal;

/**
 * Created by chenhewen on 2017/9/26.
 */

public class MailUtil {

    public static void sendMail(String emailAddress, String subjectArg, String textArg) {

        Context context = Robust.getAppContext();

        String subject = !TextUtils.isEmpty(subjectArg) ? subjectArg : context.getString(R.string.robust_email_subject);

        String text = !TextUtils.isEmpty(textArg) ? textArg : context.getString(R.string.robust_email_text);
        String chooseApp = context.getString(R.string.robust_email_choose_app);

        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        // i.setType("text/plain"); //模拟器请使用这行
        intent.setType("message/rfc822"); // 真机上使用这行
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        boolean gmailInstalled = AppUtil.isAppInstall(RobustGlobal.PackageName.GMAIL_PACKAGE_NAME);
        if (gmailInstalled) {
            intent.setPackage(RobustGlobal.PackageName.GMAIL_PACKAGE_NAME);
        }
        try {
            if (gmailInstalled) {
                context.startActivity(intent);
            } else {
                context.startActivity(Intent.createChooser(intent, chooseApp));
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.robust_email_no_mail_app, Toast.LENGTH_LONG).show();
        }
    }
}
