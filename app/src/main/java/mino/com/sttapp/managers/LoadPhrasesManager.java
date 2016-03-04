package mino.com.sttapp.managers;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mino.com.sttapp.model.assets.ListParameterizedType;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.utils.AppAssetsManager;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class LoadPhrasesManager {

    private final Context context;
    private List<Phrase> phrasesList;

    public LoadPhrasesManager(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void loadPhrases() {
        final Type type = new ListParameterizedType(Phrase.class);
        phrasesList = new ArrayList<>();
        try {
            phrasesList = new Gson().fromJson(AppAssetsManager.getAssetFromFileName(context, "phrases.json"), type);
        } catch (IOException ex) {
        }
    }

    public List<Phrase> getPhrasesList() {
        return phrasesList;
    }
}
