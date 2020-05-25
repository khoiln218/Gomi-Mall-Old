package vn.gomimall.apps.ui.main.live.video;

import vn.gomimall.apps.event.BaseEvent;

/**
 * Created by KHOI LE on 5/21/2020.
 */
class LiveMainEvent<T> extends BaseEvent<T> {
    static final int USER_OFFLINE = 0;
    static final int RENDER_REMOTE_USER = 1;
    static final int FINISH = 2;
    static final int RECEIVE_MESSAGE = 3;

    LiveMainEvent(int code) {
        super(code);
    }
}
