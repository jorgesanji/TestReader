package mino.com.sttapp.view;

import mino.com.sttapp.R;
import mino.com.sttapp.core.presenter.Presenter;
import mino.com.sttapp.core.presenter.StatusMessageManager;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class AppStatusManager extends StatusMessageManager {

    @Override
    public int getErrorColor() {
        return R.color.colorAccent;
    }

    @Override
    public int getSucessColor() {
        return R.color.colorPrimary;
    }

    @Override
    public int getWarningColor() {
        return R.color.colorPrimaryDark;
    }

    public AppStatusManager(Presenter.View view) {
        super(view);
    }

    public void showRecognitionError() {
        showError(R.string.recognition_error);
    }


}
