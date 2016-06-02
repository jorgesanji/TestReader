package mino.com.sttapp.presenter.settings;

import android.content.Intent;
import android.os.Bundle;

import mino.com.sttapp.Commons.Common;
import mino.com.sttapp.application.SttApplication;
import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.view.dialog.addphrase.AddPhraseDialogFragment;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SettingsPresenter extends BasePresenter<SettingsPresenter.View> {

    private final Actions listener;

    public SettingsPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {

    }

    // BasePresenter
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Common.ADD_PHRASE_CODE) {
            Bundle extras = data.getExtras();
            String phraseStr = extras.getString(Common.ADD_PHRASE_KEY);
            if (phraseStr.length() < Common.MINIMUN_LENGHT_PHRASE) {
                statusView().showErrorPhrase();
            } else {
                Phrase phrase = new Phrase("", phraseStr);
                SttApplication.getApp().getPhrases().add(phrase);
                statusView().showSuccesPhrase();
            }
        }
    }

    //Public methods

    public void onAddPressed() {
        getView().getFragment().addDialogFragment(AddPhraseDialogFragment.class, Common.ADD_PHRASE_CODE);
    }

}
