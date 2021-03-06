package mino.com.sttapp.presenter.listphrases;

import android.app.Activity;
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
public class ListPhrasesPresenter extends BasePresenter<ListPhrasesPresenter.View> {

    private final Actions listener;

    public ListPhrasesPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void addPhrase(Phrase phrase);
    }

    public interface Actions {
        void onSelectedPhrase(Activity activity, Bundle bundle);
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
                getView().addPhrase(phrase);
            }
        }
    }

    //Public methods

    public void onAddPressed() {
        getView().getFragment().addDialogFragment(AddPhraseDialogFragment.class, Common.ADD_PHRASE_CODE);
    }

    public void onSelectedPhrase(Phrase phrase) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Common.ITEM_SELECTED, phrase);
        bundle.putInt(Common.KEY_AGE, getView().getFragment().getArguments().getInt(Common.KEY_AGE));
        listener.onSelectedPhrase(getView().getActivity(), bundle);
    }
}
