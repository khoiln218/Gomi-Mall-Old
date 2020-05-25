package vn.gomimall.apps;

import android.content.Context;

import vn.gomimall.apps.data.source.local.AppPreferences;
import vn.gomimall.apps.utils.LogUtils;

public class Application extends android.app.Application {
    private static Application instance;

    private static AppPreferences appPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG) {
            LogUtils.setLogLevel(LogUtils.LogLevel.LOG_LEVEL_VERBOSE);
        } else {
            LogUtils.setLogLevel(LogUtils.LogLevel.LOG_LEVEL_NONE);
        }
    }

    public static synchronized Application getInstance() {
        return instance;
    }

    public Context getAppContext() {
        return instance.getApplicationContext();
    }

    public static AppPreferences getPreferences() {
        if (appPreferences == null)
            appPreferences = new AppPreferences(instance.getAppContext());

        return appPreferences;
    }
}
