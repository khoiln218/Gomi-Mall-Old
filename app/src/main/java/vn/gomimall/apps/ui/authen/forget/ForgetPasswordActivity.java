package vn.gomimall.apps.ui.authen.forget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import vn.gomimall.apps.BaseActivity;
import vn.gomimall.apps.R;
import vn.gomimall.apps.data.model.pojo.Account;
import vn.gomimall.apps.databinding.ActivityForgetPasswordBinding;
import vn.gomimall.apps.ui.authen.forget.reset.ResetPasswordActivity;
import vn.gomimall.apps.utils.GomiConstants;
import vn.gomimall.apps.utils.Utils;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordViewModel, ActivityForgetPasswordBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setupToolbar();
        setupCmd();
    }

    private void setupCmd() {
        getViewModel().getCmd().observe(this, new Observer<ForgetEvent>() {
            @Override
            public void onChanged(ForgetEvent event) {
                switch (event.getCode()) {
                    case ForgetEvent.FORGER_SUCCESS:
                        fogetSuccess((Account) event.getData());
                        break;
                    case ForgetEvent.HIDE_KEYBOARD:
                        Utils.hideSoftKeyboard(ForgetPasswordActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);
        getBinding().setViewModel(getViewModel());
        binding.setLifecycleOwner(this);
    }

    private void fogetSuccess(Account account) {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        intent.putExtra(GomiConstants.EXTRA_ID, account.getUserId());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.forget_password));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
