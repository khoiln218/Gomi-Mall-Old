package vn.gomimall.apps.ui.main;

import vn.gomimall.apps.event.BaseEvent;

/**
 * Created by KHOI LE on 4/22/2020.
 */
public class MainEvent<T> extends BaseEvent<T> {
    public static final int REQUEST_PERMISSION_LIVE = 0;

    public MainEvent(int code) {
        super(code);
    }
}
