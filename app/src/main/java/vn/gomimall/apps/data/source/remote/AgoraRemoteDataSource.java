package vn.gomimall.apps.data.source.remote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.gomimall.apps.data.AgoraDataSource;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.AgoraResponseData;
import vn.gomimall.apps.data.model.pojo.AgoraChannels;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class AgoraRemoteDataSource implements AgoraDataSource {
    @Override
    public void getListChannel(String appId, final ResultListener<AgoraResponseData<AgoraChannels>> callback) {
        AgoraService client = ApiConfig.getAgoraClient();
        Call<AgoraResponseData<AgoraChannels>> call = client.getListChannel(appId);
        call.enqueue(new Callback<AgoraResponseData<AgoraChannels>>() {
            @Override
            public void onResponse(Call<AgoraResponseData<AgoraChannels>> call, Response<AgoraResponseData<AgoraChannels>> response) {
                try {
                    if (response.body().isSuccess())
                        callback.onLoaded(response.body());
                    else
                        callback.onDataNotAvailable(response.body().getMessage());
                } catch (Exception e) {
                    callback.onDataNotAvailable(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AgoraResponseData<AgoraChannels>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }
}
