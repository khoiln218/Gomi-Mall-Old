package vn.gomimall.apps.ui.authen.forget;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import vn.gomimall.apps.Application;
import vn.gomimall.apps.BaseViewModel;
import vn.gomimall.apps.R;
import vn.gomimall.apps.data.AccountRepository;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.ForgetPwdRequest;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.pojo.Account;
import vn.gomimall.apps.data.source.remote.ResultCode;
import vn.gomimall.apps.utils.Inputs;

/**
 * Created by KHOI LE on 3/16/2020.
 */
public class ForgetPasswordViewModel extends BaseViewModel<ForgetEvent<Account>> {
    private AccountRepository mAppRepository = AccountRepository.getInstance();

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> usernameError = new MutableLiveData<>();
    public MutableLiveData<Boolean> usernameEnableError = new MutableLiveData<>();
    public MutableLiveData<Boolean> usernameRequestFocus = new MutableLiveData<>();

    public MutableLiveData<Boolean> enableBtnForget = new MutableLiveData<>();

    public ForgetPasswordViewModel() {
    }

    public void forgot() {
        hideKeyboard();
        submitForm();
    }

    private void submitForm() {
        if (!Inputs.validateEmail(username.getValue()) && !Inputs.validatePhoneNumber(username.getValue())) {
            usernameError();
            return;
        } else {
            usernameSuccess();
        }

        requestForgotPwd();
    }

    private void usernameSuccess() {
        usernameEnableError.setValue(false);
    }

    private void usernameError() {
        usernameError.setValue(Application.getInstance().getString(R.string.err_input_username));
        usernameRequestFocus.setValue(true);
    }

    private void requestForgotPwd() {
        showProgressing();
        ForgetPwdRequest request = new ForgetPwdRequest();
        request.setUserName(username.getValue());
        mAppRepository.forgetPwd(request, new ResultListener<ResponseData<Account>>() {
            @Override
            public void onLoaded(ResponseData<Account> result) {
                loaded();
                if (result.getCode() == ResultCode.CODE_OK) {
                    forgetSuccess(result.getResult());
                } else {
                    showToast(result.getMessage());
                }
            }

            @Override
            public void onDataNotAvailable(String error) {
                loaded();
                showToast(error);
            }
        });
    }

    private void forgetSuccess(Account account) {
        ForgetEvent<Account> event = new ForgetEvent<>(ForgetEvent.FORGER_SUCCESS);
        event.setData(account);
        getCmd().call(event);
    }

    private void hideKeyboard() {
        getCmd().call(new ForgetEvent<Account>(ForgetEvent.HIDE_KEYBOARD));
    }

    public void afterTextChanged() {
        if (!TextUtils.isEmpty(username.getValue()) && username.getValue().length() > 3)
            enableForgetPwd();
        else
            disableForgetPwd();
    }

    private void enableForgetPwd() {
        enableBtnForget.setValue(true);
    }

    private void disableForgetPwd() {
        enableBtnForget.setValue(false);
    }
}
