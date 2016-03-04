package mino.com.sttapp.presenter.recognizer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;
import mino.com.sttapp.model.RecognitionResult;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.recognizerengine.RecognizerEngine;
import mino.com.sttapp.utils.AsyncLoader;
import mino.com.sttapp.view.dialog.DialogFragment;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerPresenter extends BasePresenter<RecognizerPresenter.View> {

    private static final int INIT_DELAY = 500;

    private Actions listener;
    private RecognizerEngine engine;

    public RecognizerPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        Phrase getPhrase();
        void initCount();
    }

    public interface Actions {

    }

    @Override
    public void attachView(View view) {
        super.attachView(view);
        engine = new RecognizerEngine(getView().getActivity());
    }

    //Public methods

    public void startRecognizer() {
        getView().initCount();
        engine.startRecoginizer();
    }

    public void stopRecognizer() {
        engine.stopRecognizer();
    }

    public void destroyRecognizer() {
        engine.destroy();
    }

    public void showResults(final String result) {
        final String phrase = getView().getPhrase().getText();
        AsyncLoader<RecognitionResult> asyncLoader = new AsyncLoader<RecognitionResult>() {
            @Override
            public RecognitionResult doInBackground() {

                String[] resultsArray = result.split(" ");
                String[] phraseArray = phrase.split(" ");

                int numberwords = resultsArray.length;
                int correctWords = 0;
                int incorrectWords = 0;

                for (int index = 0; index < resultsArray.length; index++) {
                    String resultString = resultsArray[index];
                    String phraseString = phraseArray[index];
                    if (resultString.equalsIgnoreCase(phraseString)) {
                        correctWords++;
                    } else {
                        incorrectWords++;
                    }
                }

                return new RecognitionResult(result,numberwords, correctWords, incorrectWords);
            }

            @Override
            public void postProcess(RecognitionResult result) {
                Bundle args = new Bundle();
                args.putSerializable(DialogFragment.ITEM_RESULT, result);
                addDialogFragment(DialogFragment.class, 0, args);
            }
        };

        asyncLoader.start();
    }
}
