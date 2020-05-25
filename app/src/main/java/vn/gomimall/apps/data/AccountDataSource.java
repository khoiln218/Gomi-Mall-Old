package vn.gomimall.apps.data;

import vn.gomimall.apps.data.model.api.ForgetPwdRequest;
import vn.gomimall.apps.data.model.api.ResetPwdRequest;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.api.SignInRequest;
import vn.gomimall.apps.data.model.api.SignUpRequest;
import vn.gomimall.apps.data.model.api.VerifyPhoneNumberRequest;
import vn.gomimall.apps.data.model.pojo.Account;

public interface AccountDataSource {
    void signin(SignInRequest request, ResultListener<ResponseData<Account>> callback);

    void signup(SignUpRequest request, ResultListener<ResponseData<Account>> callback);

    void verifyPhoneNumber(VerifyPhoneNumberRequest request, ResultListener<ResponseData<Account>> callback);

    void forgetPwd(ForgetPwdRequest request, ResultListener<ResponseData<Account>> callback);

    void resetPwd(ResetPwdRequest request, ResultListener<ResponseData<Account>> callback);
}
