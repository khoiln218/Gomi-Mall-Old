package vn.gomimall.apps.data.source.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.gomimall.apps.data.LocationDataSource;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.pojo.Location;

/**
 * Created by KHOI LE on 3/18/2020.
 */
public class LocationRemoteDataSource implements LocationDataSource {
    @Override
    public void getLocationCountry(final ResultListener<ResponseData<List<Location>>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<List<Location>>> call = client.getLocationCountry();
        call.enqueue(new Callback<ResponseData<List<Location>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<Location>>> call, Response<ResponseData<List<Location>>> response) {
                try {
                    if (response.body().isStatus())
                        callback.onLoaded(response.body());
                    else
                        callback.onDataNotAvailable(response.body().getMessage());
                } catch (Exception e) {
                    callback.onDataNotAvailable(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<Location>>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }
}
