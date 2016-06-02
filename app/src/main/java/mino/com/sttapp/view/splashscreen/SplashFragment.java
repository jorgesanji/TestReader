package mino.com.sttapp.view.splashscreen;

import android.view.View;

import mino.com.sttapp.ScreenNavigationHandler;
import mino.com.sttapp.core.view.MVPFragment;
import mino.com.sttapp.presenter.splash.SplashPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SplashFragment extends MVPFragment<SplashPresenter, SplashPresenter.View> implements SplashPresenter.View, SplashScreen.Listener {

    private SplashScreen splashScreen;

    @Override
    protected View getRootView() {
        splashScreen = new SplashScreen(getActivity());
        splashScreen.setListener(this);
        return splashScreen;
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected SplashPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidStart() {
    }

    // RecognizerPresenter.View

    //endregion
}
