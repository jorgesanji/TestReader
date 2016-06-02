package mino.com.sttapp.view.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mino.com.sttapp.R;
import mino.com.sttapp.core.utils.PermissionsManager;
import mino.com.sttapp.core.view.BaseActivity;
import mino.com.sttapp.view.home.HomeActivity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_SCREEN_DURATION = 2000;
    private static final int DELAY_SECONDS = 1;

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, DELAY_SECONDS * SPLASH_SCREEN_DURATION);
    }

    @Override
    public View getrootView() {
        if (rootView == null) {
            rootView = getLayoutInflater().inflate(R.layout.lay_recognizer, null);
        }
        return rootView;
    }

    @Override
    public View getView() {
        return rootView.findViewById(R.id.container);
    }

    @Override
    public void postAddView() {
        Fragment fragment = Fragment.instantiate(this, SplashFragment.class.getName());
        fragment.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, fragment);
        transaction.commit();
    }

    @Override
    public boolean canLaunchPermission() {
        return true;
    }

    @Override
    public int getStatusColor() {
        return R.color.colorAccent;
    }

    @Override
    public List<PermissionsManager.Permission> getRequestPermission() {
        List<PermissionsManager.Permission> permissionList = new ArrayList<>();
        permissionList.add(PermissionsManager.Permission.AUDIO);

        return permissionList;
    }
}
