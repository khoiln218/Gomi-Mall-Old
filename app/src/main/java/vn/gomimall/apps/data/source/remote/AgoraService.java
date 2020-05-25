package vn.gomimall.apps.data.source.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import vn.gomimall.apps.data.model.api.AgoraResponseData;
import vn.gomimall.apps.data.model.pojo.AgoraChannels;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public interface AgoraService {
    @Headers({"Accept:application/json", "Content-Type:application/json;charset=utf-8",
            "Authorization:Basic N2NiYzc1NTE3ZTY0NGRjMTk4N2NmYjMxNTBiZjM5MTg6OTY4Y2ZjODM2NTkxNDE5MWEzYmMyY2RiZjViZTk4OWM="})
    @GET("channel/{appId}")
    Call<AgoraResponseData<AgoraChannels>> getListChannel(@Path("appId") String appId);
}
