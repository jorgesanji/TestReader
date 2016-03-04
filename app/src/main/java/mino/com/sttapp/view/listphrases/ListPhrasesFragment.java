package mino.com.sttapp.view.listphrases;

import android.view.View;

import java.util.List;

import mino.com.sttapp.ScreenNavigationHandler;
import mino.com.sttapp.core.view.MVPFragment;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.presenter.listphrases.ListPhrasesPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesFragment extends MVPFragment<ListPhrasesPresenter, ListPhrasesPresenter.View> implements ListPhrasesPresenter.View, ListPhrasesScreen.Listener {

    private ListPhrasesScreen listPhrasesScreen;

    @Override
    protected View getRootView() {
        listPhrasesScreen = new ListPhrasesScreen(getActivity());
        listPhrasesScreen.setListener(this);
        return listPhrasesScreen;
    }

    @Override
    protected ListPhrasesPresenter createPresenter() {
        return new ListPhrasesPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected ListPhrasesPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidStart() {
        getPresenter().getPhraseList();
    }

    // RecognizerPresenter.View

    @Override
    public void setPhrases(List<Phrase> phrases) {
        listPhrasesScreen.setItems(phrases);
    }

    // RecognizerScreen.Listener

    @Override
    public void onSelectedPhrase(Phrase phrase) {
        getPresenter().onSelectedPhrase(phrase);
    }
}
