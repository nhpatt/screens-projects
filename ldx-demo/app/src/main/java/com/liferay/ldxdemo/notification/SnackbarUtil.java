package com.liferay.ldxdemo.notification;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author Javier Gamarra
 */
public class SnackbarUtil {

    public static void showMessage(Activity activity, String message) {
        View content = activity.findViewById(android.R.id.content);
        Snackbar.make(content, message, Snackbar.LENGTH_LONG).show();
    }
}
