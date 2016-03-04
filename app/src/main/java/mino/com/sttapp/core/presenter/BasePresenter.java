package mino.com.sttapp.core.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;

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

    protected AppCompatDialogFragment addDialogFragment(Class clazz, int code) {
        return addDialogFragment(clazz, code, null);
    }

    protected AppCompatDialogFragment addDialogFragment(Class clazz, int code, Bundle bundle) {
        FragmentTransaction fragmentTransaction = getView().getFragment().getFragmentManager().beginTransaction();
        AppCompatDialogFragment fragment = (AppCompatDialogFragment) AppCompatDialogFragment.instantiate(getView().getContext(), clazz.getName());
        fragment.setTargetFragment(getView().getFragment(), code);
        fragment.setArguments(bundle);
        fragment.show(fragmentTransaction, clazz.toString());

        return fragment;
    }
}
