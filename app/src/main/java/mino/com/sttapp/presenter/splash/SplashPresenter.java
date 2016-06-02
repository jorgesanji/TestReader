package mino.com.sttapp.presenter.splash;

import android.app.Activity;
import android.os.Bundle;

import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SplashPresenter extends BasePresenter<SplashPresenter.View> {

    private final Actions listener;

    public SplashPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {
        void onLaunchHome(Activity activity, Bundle bundle);

    }

    //Public methods

}
