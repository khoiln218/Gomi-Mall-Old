package vn.gomimall.apps.data;

import vn.gomimall.apps.data.model.api.AgoraResponseData;
import vn.gomimall.apps.data.model.pojo.AgoraChannels;
import vn.gomimall.apps.data.source.remote.AgoraRemoteDataSource;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class AgoraRepository implements AgoraDataSource {
    private static volatile AgoraRepository instance;

    private AgoraDataSource mAgoraDataSource;

    private AgoraRepository() {
        this.mAgoraDataSource = new AgoraRemoteDataSource();
    }

    public static AgoraRepository getInstance() {
        if (instance == null) {
            instance = new AgoraRepository();
        }
        return instance;
    }

    @Override
    public void getListChannel(String appId, ResultListener<AgoraResponseData<AgoraChannels>> callback) {
        mAgoraDataSource.getListChannel(appId, callback);
    }
}
