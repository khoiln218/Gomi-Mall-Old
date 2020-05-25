package vn.gomimall.apps.data;

import vn.gomimall.apps.data.model.api.ForgetPwdRequest;
import vn.gomimall.apps.data.model.api.ResetPwdRequest;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.api.SignInRequest;
import vn.gomimall.apps.data.model.api.SignUpRequest;
import vn.gomimall.apps.data.model.api.VerifyPhoneNumberRequest;
import vn.gomimall.apps.data.model.pojo.Account;
import vn.gomimall.apps.data.source.remote.AccountRemoteDataSource;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class AccountRepository implements AccountDataSource {

    private static volatile AccountRepository instance;

    private AccountDataSource mAccountDataSource;

    private AccountRepository() {
        this.mAccountDataSource = new AccountRemoteDataSource();
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    @Override
    public void signin(SignInRequest request, ResultListener<ResponseData<Account>> callback) {
        mAccountDataSource.signin(request, callback);
    }

    @Override
    public void signup(SignUpRequest request, ResultListener<ResponseData<Account>> callback) {
        mAccountDataSource.signup(request, callback);
    }

    @Override
    public void verifyPhoneNumber(VerifyPhoneNumberRequest request, ResultListener<ResponseData<Account>> callback) {
        mAccountDataSource.verifyPhoneNumber(request, callback);
    }

    @Override
    public void forgetPwd(ForgetPwdRequest request, ResultListener<ResponseData<Account>> callback) {
        mAccountDataSource.forgetPwd(request, callback);
    }

    @Override
    public void resetPwd(ResetPwdRequest request, ResultListener<ResponseData<Account>> callback) {
        mAccountDataSource.resetPwd(request, callback);
    }
}
