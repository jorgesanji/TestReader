package mino.com.sttapp.presenter.listphrases;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import mino.com.sttapp.application.SttApplication;
import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.view.recognizer.RecognizerActivity;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesPresenter extends BasePresenter<ListPhrasesPresenter.View> {

    private final Actions listener;

    public ListPhrasesPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setPhrases(List<Phrase> list);
    }

    public interface Actions {
        void onSelectedPhrase(Activity activity, Bundle bundle);
    }

    //Public methods

    public void getPhraseList() {
        List<Phrase> list = SttApplication.getApp().getPhrases();
        getView().setPhrases(list);
    }

    public void onSelectedPhrase(Phrase phrase) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(RecognizerActivity.ITEM_SELECTED, phrase);

        listener.onSelectedPhrase(getView().getActivity(), bundle);
    }
}
