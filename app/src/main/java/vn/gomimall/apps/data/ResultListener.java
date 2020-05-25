package vn.gomimall.apps.data;

/**
 * Created by KHOI LE on 3/12/2020.
 */
public interface ResultListener<T> {
    void onLoaded(T result);

    void onDataNotAvailable(String error);
}
