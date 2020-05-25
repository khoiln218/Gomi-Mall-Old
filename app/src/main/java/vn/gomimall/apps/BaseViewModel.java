package vn.gomimall.apps;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import vn.gomimall.apps.event.BaseEvent;
import vn.gomimall.apps.event.MultableLiveEvent;
import vn.gomimall.apps.utils.ConnectionHelper;
import vn.gomimall.apps.utils.ToastUtils;

/**
 * Created by KHOI LE on 4/6/2020.
 */
public class BaseViewModel<E extends BaseEvent> extends ViewModel {

    private MultableLiveEvent<E> cmd = new MultableLiveEvent<>();
    public MutableLiveData<Boolean> isProgressing = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> refreshing = new MutableLiveData<>();

    protected void showProgressing() {
        isProgressing.setValue(true);
    }

    protected void hideProgressing() {
        isProgressing.setValue(false);
    }

    protected void setErrorMessage(String error) {
        errorMessage.setValue(error);
    }

    private void refreshed() {
        refreshing.setValue(false);
    }

    protected void loaded() {
        hideProgressing();
        refreshed();
    }

    protected void checkConnection(final String error) {
        ConnectionHelper.getInstance().checkNetwork(new ConnectionHelper.OnCheckNetworkListener() {
            @Override
            public void onCheck(boolean isOnline) {
                if (!isOnline)
                    errorMessage.setValue(Application.getInstance().getString(R.string.network_error));
                else errorMessage.setValue(error);
            }
        });
    }

    protected void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    public MultableLiveEvent<E> getCmd() {
        return cmd;
    }
}
