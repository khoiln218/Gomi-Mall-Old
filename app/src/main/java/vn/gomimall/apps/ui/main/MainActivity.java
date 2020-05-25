package vn.gomimall.apps.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import vn.gomimall.apps.Application;
import vn.gomimall.apps.BaseActivity;
import vn.gomimall.apps.R;
import vn.gomimall.apps.databinding.ActivityMainBinding;
import vn.gomimall.apps.ui.main.live.LiveFragment;
import vn.gomimall.apps.ui.main.live.video.LiveActivity;
import vn.gomimall.apps.utils.AlertDialogs;
import vn.gomimall.apps.utils.Intents;
import vn.gomimall.apps.utils.LogUtils;
import vn.gomimall.apps.utils.PermissionHelper;
import vn.gomimall.apps.utils.ToastUtils;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    private PermissionHelper permissionHelper;
    private boolean dialogShowing = false;

    private boolean isExit = false;
    private Handler handler;
    private Runnable exitApp;

    private LiveFragment liveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initCmd();

        if (!Application.getPreferences().isLogin()) {
            LogUtils.d("TAG", "user isn't login");
            Intents.startLoginActivity(this);
            finish();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        permissionHelper = new PermissionHelper(this, PermissionHelper.photo_permissions);

        handler = new Handler();
        exitApp = new Runnable() {
            @Override
            public void run() {
                isExit = false;
            }
        };

        liveFragment = new LiveFragment();

        loadFragment(liveFragment);

        EventBus.getDefault().register(this);
    }

    private void initCmd() {
        getViewModel().getCmd().observe(this, new Observer<MainEvent>() {
            @Override
            public void onChanged(MainEvent event) {

            }
        });
    }

    @Override
    protected void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment == null) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            confirmSignOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment != null)
            fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe
    public void onMessageEvent(MainEvent event) {
        if (event.getCode() == MainEvent.REQUEST_PERMISSION_LIVE) {
            requestPermission(event.getCode());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (isExit) {
            handler.removeCallbacks(exitApp);
            finish();
        } else {
            isExit = true;
            ToastUtils.showToast(getString(R.string.exit_app));
            handler.removeCallbacks(exitApp);
            handler.postDelayed(exitApp, 2000);
        }
    }

    private void confirmSignOut() {
        AlertDialogs.show(this, "", getString(R.string.logout_msg), getString(R.string.btn_cancel), getString(R.string.btn_confirm), new AlertDialogs.OnClickListener() {
            @Override
            public void onNegativeButtonClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

            @Override
            public void onPositiveButtonClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getViewModel().requestSignOut();
                Intents.startLoginActivity(MainActivity.this);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null)
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Check Photo Permissions
     */
    public void requestPermission(final int type) {
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                if (type == MainEvent.REQUEST_PERMISSION_LIVE) {
                    viewLive();
                }
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                showPermissionDialog();
            }

            @Override
            public void onPermissionDenied() {
                showPermissionDialog();
            }

            @Override
            public void onPermissionDeniedBySystem() {
                showPermissionDialog();
            }
        });
    }

    private void viewLive() {
        startActivity(new Intent(this, LiveActivity.class));
    }

    private void showPermissionDialog() {
        if (!dialogShowing) {
            dialogShowing = true;

            AlertDialogs.show(this, String.format(getString(R.string.title_need_camera_permission), getString(R.string.app_name)), String.format(getString(R.string.msg_need_camera_permission), getString(R.string.app_name)), getString(R.string.btn_cancel), getString(R.string.btn_setting), new AlertDialogs.OnClickListener() {
                @Override
                public void onNegativeButtonClick(DialogInterface dialog, int which) {
                    dialogShowing = false;
                    dialog.dismiss();
                }

                @Override
                public void onPositiveButtonClick(DialogInterface dialog, int which) {
                    dialogShowing = false;
                    permissionHelper.launchAppDetailsSettings();
                    dialog.dismiss();
                }
            });
        }
    }
}
