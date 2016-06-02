package mino.com.sttapp.view.listphrases;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import mino.com.sttapp.R;
import mino.com.sttapp.ScreenNavigationHandler;
import mino.com.sttapp.application.SttApplication;
import mino.com.sttapp.core.view.MVPFragment;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.presenter.listphrases.ListPhrasesPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesFragment extends MVPFragment<ListPhrasesPresenter, ListPhrasesPresenter.View> implements ListPhrasesPresenter.View, ListPhrasesScreen.Listener {

    private ListPhrasesScreen listPhrasesScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            getPresenter().onAddPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


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
        listPhrasesScreen.setItems(SttApplication.getApp().getPhrases());
    }

    // RecognizerPresenter.View

    @Override
    public void addPhrase(Phrase phrase) {
        listPhrasesScreen.addItem(phrase);
    }

    // RecognizerScreen.Listener

    @Override
    public void onSelectedPhrase(Phrase phrase) {
        getPresenter().onSelectedPhrase(phrase);
    }
}
