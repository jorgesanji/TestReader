package mino.com.sttapp.view.settings;

import android.view.View;

import mino.com.sttapp.ScreenNavigationHandler;
import mino.com.sttapp.core.view.MVPFragment;
import mino.com.sttapp.presenter.settings.SettingsPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SettingsFragment extends MVPFragment<SettingsPresenter, SettingsPresenter.View> implements SettingsPresenter.View, SettingsScreen.Listener {

    private SettingsScreen settingsScreen;

    @Override
    protected View getRootView() {
        settingsScreen = new SettingsScreen(getActivity());
        settingsScreen.setListener(this);
        return settingsScreen;
    }

    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected SettingsPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidStart() {
    }

    // SettingsPresenter.View

    // SettingsScreen.Listener

    @Override
    public void addPressed() {
        getPresenter().onAddPressed();
    }
}
