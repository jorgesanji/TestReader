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
        return android.R.color.holo_red_dark;
    }

    @Override
    public int getSucessColor() {
        return android.R.color.holo_green_light;
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


    public void showErrorPhrase() {
        showError(R.string.add_phrase_error_message);
    }

    public void showSuccesPhrase() {
        showSuccess(R.string.add_phrase_success_message);
    }
}
