package vn.gomimall.apps.utils;

import android.app.Activity;
import android.content.Intent;

import vn.gomimall.apps.ui.authen.signin.SignInActivity;
import vn.gomimall.apps.ui.main.MainActivity;

/**
 * Created by KHOI LE on 3/19/2020.
 */
public class Intents {

    public static void startMainActivity(Activity activity) {
        startNewTaskActivity(activity, MainActivity.class);
    }

    public static void startLoginActivity(Activity activity) {
        startNewTaskActivity(activity, SignInActivity.class);
    }

    private static void startNewTaskActivity(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.finish();
    }
}
