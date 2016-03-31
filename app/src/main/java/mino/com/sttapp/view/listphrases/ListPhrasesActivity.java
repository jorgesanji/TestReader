package mino.com.sttapp.view.listphrases;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mino.com.sttapp.R;
import mino.com.sttapp.core.utils.PermissionsManager;
import mino.com.sttapp.core.view.BaseActivity;

public class ListPhrasesActivity extends BaseActivity {


    View rootView;

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
        Fragment fragment = Fragment.instantiate(this, ListPhrasesFragment.class.getName());
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
