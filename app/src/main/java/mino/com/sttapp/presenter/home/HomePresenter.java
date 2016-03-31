package mino.com.sttapp.presenter.home;

import android.app.Activity;
import android.os.Bundle;

import mino.com.sttapp.Commons.Common;
import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomePresenter extends BasePresenter<HomePresenter.View> {

    private final Actions listener;

    public HomePresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {
        void onSettingsPressed(Activity activity, Bundle bundle);

        void onStartPressed(Activity activity, Bundle bundle);
    }

    //Public methods

    public void settingsPressed() {
        listener.onSettingsPressed(getView().getActivity(), null);
    }

    public void startPressed(int age) {
        Bundle bundle = new Bundle();
        bundle.putInt(Common.KEY_AGE, age);
        listener.onStartPressed(getView().getActivity(), bundle);
    }

}
