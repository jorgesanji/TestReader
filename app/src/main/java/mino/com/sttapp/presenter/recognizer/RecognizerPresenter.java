package mino.com.sttapp.presenter.recognizer;

import android.os.Bundle;

import mino.com.sttapp.core.presenter.BasePresenter;
import mino.com.sttapp.core.presenter.Presenter;
import mino.com.sttapp.model.RecognitionResult;
import mino.com.sttapp.model.Result;
import mino.com.sttapp.model.assets.Phrase;
import mino.com.sttapp.recognizerengine.RecognizerEngine;
import mino.com.sttapp.result.ResultEngine;
import mino.com.sttapp.utils.AsyncLoader;
import mino.com.sttapp.view.dialog.result.DialogFragment;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerPresenter extends BasePresenter<RecognizerPresenter.View> {

    private static final int INIT_DELAY = 500;

    private Actions listener;
    private RecognizerEngine engine;
    private ResultEngine mResultEngine;

    public RecognizerPresenter(Actions actions) {
        this.listener = actions;
        this.mResultEngine = new ResultEngine();
    }

    public interface View extends Presenter.View {
        Phrase getPhrase();

        void initCount();

        String getUserAge();
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
        final String phrase = getView().getPhrase().getText().replaceAll("([a-z]+)[?:!.,;]*", "$1");
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

                Result optimResult = mResultEngine.getResultByAgeAndCorrectWords(getView().getUserAge(), correctWords);
                return new RecognitionResult(result, getView().getFragment().getString(optimResult.getTextResult()), numberwords, correctWords, incorrectWords);
            }

            @Override
            public void postProcess(RecognitionResult result) {
                Bundle args = new Bundle();
                args.putSerializable(DialogFragment.ITEM_RESULT, result);
                getView().getFragment().addDialogFragment(DialogFragment.class, 0, args);
            }
        };

        asyncLoader.start();
    }
}
