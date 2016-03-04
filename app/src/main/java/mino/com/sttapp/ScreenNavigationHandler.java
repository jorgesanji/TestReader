package mino.com.sttapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import mino.com.sttapp.presenter.listphrases.ListPhrasesPresenter;
import mino.com.sttapp.presenter.recognizer.RecognizerPresenter;
import mino.com.sttapp.view.recognizer.RecognizerActivity;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public final class ScreenNavigationHandler implements ListPhrasesPresenter.Actions, RecognizerPresenter.Actions {

    //Instance
    private static ScreenNavigationHandler instance = null;

    private ScreenNavigationHandler() {
    }

    /**
     * @return the instance of ScreenNavigationHandler
     */
    public static ScreenNavigationHandler getInstance() {
        if (instance == null) {
            instance = new ScreenNavigationHandler();
        }
        return instance;
    }

    // ---------------------------- LAUNCH INTENT -------------------------------

    private static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void startActivityWithResult(Activity context, Intent intent, int code) {
        context.startActivityForResult(intent, code);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static Intent newTask(@NonNull Activity context, @NonNull Class clazz) {
        return (newTask(context, clazz, false));
    }

    private static Intent newTask(@NonNull Activity context, @NonNull Class clazz, boolean clearTop) {
        Intent openIntent = new Intent(context, clazz);
        if (clearTop) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return openIntent;
    }

    // ------------------------ ACTIONS DEFINITION -----------------------------------

    private static Intent phrase(@NonNull Activity context) {
        return newTask(context, RecognizerActivity.class);
    }

    @Override
    public void onSelectedPhrase(@NonNull Activity activity, Bundle bundle) {
        Intent intent = phrase(activity);
        intent.putExtras(bundle);
        startActivity(activity, intent);
    }
}
