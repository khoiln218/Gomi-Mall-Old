package vn.gomimall.apps.data;

import java.util.List;

import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.pojo.Location;
import vn.gomimall.apps.data.source.remote.LocationRemoteDataSource;

/**
 * Created by KHOI LE on 3/18/2020.
 */
public class LocationRepository implements LocationDataSource {
    private volatile static LocationRepository INSTANCE = null;
    private LocationDataSource mRemoteDataSource;

    private LocationRepository() {
        mRemoteDataSource = new LocationRemoteDataSource();
    }

    public static LocationRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (LocationRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationRepository();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getLocationCountry(ResultListener<ResponseData<List<Location>>> callback) {
        mRemoteDataSource.getLocationCountry(callback);
    }
}
