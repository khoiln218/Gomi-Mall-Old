package vn.gomimall.apps.ui.main;

import vn.gomimall.apps.Application;
import vn.gomimall.apps.BaseViewModel;
import vn.gomimall.apps.data.AccountRepository;
import vn.gomimall.apps.data.source.local.AppPreferences;

/**
 * Created by KHOI LE on 5/15/2020.
 */
public class MainViewModel extends BaseViewModel<MainEvent> {

    private AccountRepository mAccountRepository;
    private AppPreferences mAppPreferences;

    public MainViewModel() {
        mAccountRepository = AccountRepository.getInstance();
        mAppPreferences = Application.getPreferences();
    }

    void requestSignOut() {
//        SignOutRequest request = new SignOutRequest();
//        mAccountRepository.logout(request, new ResultListener<ResponseData<Account>>() {
//            @Override
//            public void onLoaded(ResponseData<Account> result) {
//                if (result.getCode() == ResultCode.CODE_OK) {
//                    showToast(Application.getInstance().getString(R.string.logout));
//                } else {
//                    showToast(result.getMessage());
//                }
//            }
//
//            @Override
//            public void onDataNotAvailable(String error) {
//                showToast(error);
//            }
//        });

        mAppPreferences.clear();
    }
}
