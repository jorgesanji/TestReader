package mino.com.sttapp.core.presenter;

import android.content.Intent;

import mino.com.sttapp.view.AppStatusManager;

/**
 * Implements the common functionalities of the presenters.
 */
public abstract class BasePresenter<V extends Presenter.View> implements Presenter<V> {

    V view;
    AppStatusManager mStatusManager;

    public BasePresenter() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void attachView(V view) {
        this.view = view;
        this.mStatusManager = new AppStatusManager(view);
    }

    @Override
    public V getView() {
        return view;
    }

    public AppStatusManager statusView() {
        return mStatusManager;
    }
}
