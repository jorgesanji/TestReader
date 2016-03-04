package mino.com.sttapp.core.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import mino.com.sttapp.core.utils.PermissionsManager;
import mino.com.sttapp.core.view.application.BaseApplication;

/**
 * Created by jorgesanmartin on 12/1/15.
 */
public abstract class BaseActivity<A extends BaseApplication> extends AppCompatActivity {

    private A application;
    private PermissionsManager permissionsManager;
    private boolean active;

    public abstract boolean canLaunchPermission();

    public abstract List<PermissionsManager.Permission> getRequestPermission();

    public abstract int getStatusColor();

    public abstract View getrootView();

    public abstract View getView();

    public abstract void postAddView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getrootView());
        application = (A) getApplication();
        initStatusBar();
        postAddView();
        initPermissionsManager();

    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getStatusColor());
        }
    }

    private void initPermissionsManager() {
        permissionsManager = new PermissionsManager(this);
        if (canLaunchPermission()) {
            permissionsManager.requestPermissions(getRequestPermission());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
