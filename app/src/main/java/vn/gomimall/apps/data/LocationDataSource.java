package vn.gomimall.apps.data;

import java.util.List;

import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.pojo.Location;

/**
 * Created by KHOI LE on 3/18/2020.
 */
public interface LocationDataSource {
    void getLocationCountry(ResultListener<ResponseData<List<Location>>> callback);
}
