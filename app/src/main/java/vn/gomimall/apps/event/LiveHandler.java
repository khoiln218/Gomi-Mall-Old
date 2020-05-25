package vn.gomimall.apps.event;

import vn.gomimall.apps.data.model.pojo.Channel;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public interface LiveHandler {
    void onView(Channel channel);
}
