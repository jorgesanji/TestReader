package mino.com.sttapp.application;

import java.util.List;

import mino.com.sttapp.core.view.application.BaseApplication;
import mino.com.sttapp.managers.LoadPhrasesManager;
import mino.com.sttapp.model.assets.Phrase;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SttApplication extends BaseApplication {

    private static SttApplication app;

    private LoadPhrasesManager loadPhrasesManager;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        loadPhrasesManager = new LoadPhrasesManager(getApplicationContext());
        loadPhrasesManager.loadPhrases();
    }

    @Override
    public void onBackground() {

    }

    @Override
    public void onForeground() {

    }

    public static SttApplication getApp() {
        return app;
    }

    public List<Phrase> getPhrases() {
        return loadPhrasesManager.getPhrasesList();
    }
}
