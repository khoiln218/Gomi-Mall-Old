package vn.gomimall.apps.ui.authen.forget.reset;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import vn.gomimall.apps.Application;
import vn.gomimall.apps.BaseViewModel;
import vn.gomimall.apps.R;
import vn.gomimall.apps.data.AccountRepository;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.ResetPwdRequest;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.pojo.Account;
import vn.gomimall.apps.data.source.remote.ResultCode;
import vn.gomimall.apps.utils.Inputs;

/**
 * Created by KHOI LE on 3/16/2020.
 */
public class ResetPasswordViewModel extends BaseViewModel<ResetEvent> {
    private AccountRepository mAccountRepository = AccountRepository.getInstance();

    public MutableLiveData<String> newPassword = new MutableLiveData<>();
    public MutableLiveData<String> newPasswordError = new MutableLiveData<>();
    public MutableLiveData<Boolean> newPasswordErrorEnable = new MutableLiveData<>();
    public MutableLiveData<Boolean> newPasswordRequestFocus = new MutableLiveData<>();

    public MutableLiveData<String> verifyCode = new MutableLiveData<>();
    public MutableLiveData<Boolean> enableBtn = new MutableLiveData<>();

    private String userId;

    public void reset() {
        hideKeyboard();
        submitForm();
    }

    private void submitForm() {
        if (!Inputs.validatePassword(newPassword.getValue())) {
            newPasswordError();
            return;
        } else {
            newPasswordSuccess();
        }
        requestResetPwd();
    }

    private void newPasswordError() {
        newPasswordError.setValue(Application.getInstance().getString(R.string.err_input_password));
        newPasswordRequestFocus.setValue(true);
    }

    private void newPasswordSuccess() {
        newPasswordErrorEnable.setValue(false);
    }

    private void requestResetPwd() {
        showProgressing();
        ResetPwdRequest request = new ResetPwdRequest();
        request.setVerifyCode(verifyCode.getValue());
        request.setNewPassword(newPassword.getValue());
        request.setUserId(userId);
        mAccountRepository.resetPwd(request, new ResultListener<ResponseData<Account>>() {
            @Override
            public void onLoaded(ResponseData<Account> result) {
                loaded();
                if (result.getCode() == ResultCode.CODE_OK) {
                    resetPwdSuccess();
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

    private void resetPwdSuccess() {
        getCmd().call(new ResetEvent(ResetEvent.RESET_SUCCESS));
    }

    private void hideKeyboard() {
        getCmd().call(new ResetEvent(ResetEvent.HIDE_KEYBOARD));
    }

    public void afterTextChanged() {
        if (checklengthVerifyCode() && checkLengthNewPwd())
            enableBtn.setValue(true);
        else enableBtn.setValue(false);
    }

    private boolean checklengthVerifyCode() {
        return !TextUtils.isEmpty(verifyCode.getValue()) && verifyCode.getValue().length() > 3;
    }

    private boolean checkLengthNewPwd() {
        return !TextUtils.isEmpty(newPassword.getValue()) && newPassword.getValue().length() > 3;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }
}
