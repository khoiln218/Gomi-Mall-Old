package vn.gomimall.apps.data.source.remote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.gomimall.apps.data.AccountDataSource;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.ForgetPwdRequest;
import vn.gomimall.apps.data.model.api.ResetPwdRequest;
import vn.gomimall.apps.data.model.api.ResponseData;
import vn.gomimall.apps.data.model.api.SignInRequest;
import vn.gomimall.apps.data.model.api.SignUpRequest;
import vn.gomimall.apps.data.model.api.VerifyPhoneNumberRequest;
import vn.gomimall.apps.data.model.pojo.Account;

public class AccountRemoteDataSource implements AccountDataSource {
    @Override
    public void signin(SignInRequest request, final ResultListener<ResponseData<Account>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<Account>> call = client.signIn(request);
        call.enqueue(new Callback<ResponseData<Account>>() {
            @Override
            public void onResponse(Call<ResponseData<Account>> call, Response<ResponseData<Account>> response) {
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
            public void onFailure(Call<ResponseData<Account>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void signup(SignUpRequest request, final ResultListener<ResponseData<Account>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<Account>> call = client.signUp(request);
        call.enqueue(new Callback<ResponseData<Account>>() {
            @Override
            public void onResponse(Call<ResponseData<Account>> call, Response<ResponseData<Account>> response) {
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
            public void onFailure(Call<ResponseData<Account>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void verifyPhoneNumber(VerifyPhoneNumberRequest request, final ResultListener<ResponseData<Account>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<Account>> call = client.verifyPhoneNumber(request);
        call.enqueue(new Callback<ResponseData<Account>>() {
            @Override
            public void onResponse(Call<ResponseData<Account>> call, Response<ResponseData<Account>> response) {
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
            public void onFailure(Call<ResponseData<Account>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void forgetPwd(ForgetPwdRequest request, final ResultListener<ResponseData<Account>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<Account>> call = client.forgetPwd(request);
        call.enqueue(new Callback<ResponseData<Account>>() {
            @Override
            public void onResponse(Call<ResponseData<Account>> call, Response<ResponseData<Account>> response) {
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
            public void onFailure(Call<ResponseData<Account>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void resetPwd(final ResetPwdRequest request, final ResultListener<ResponseData<Account>> callback) {
        ApiService client = ApiConfig.getClient();
        Call<ResponseData<Account>> call = client.resetPwd(request);
        call.enqueue(new Callback<ResponseData<Account>>() {
            @Override
            public void onResponse(Call<ResponseData<Account>> call, Response<ResponseData<Account>> response) {
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
            public void onFailure(Call<ResponseData<Account>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }
}
