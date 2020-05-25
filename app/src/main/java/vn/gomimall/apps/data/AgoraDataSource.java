package vn.gomimall.apps.data;

import vn.gomimall.apps.data.model.api.AgoraResponseData;
import vn.gomimall.apps.data.model.pojo.AgoraChannels;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public interface AgoraDataSource {
    void getListChannel(String appId, ResultListener<AgoraResponseData<AgoraChannels>> callback);
}
