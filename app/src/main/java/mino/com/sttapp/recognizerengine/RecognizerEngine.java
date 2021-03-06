package mino.com.sttapp.recognizerengine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.List;

import de.greenrobot.event.EventBus;
import mino.com.sttapp.R;
import mino.com.sttapp.events.RecognizerEvent;

/**
 * Created by jorgesanmartin on 2/22/16.
 */
public class RecognizerEngine implements RecognitionListener {

    private static final long SPEECH_TIME_MAX = 60000;
    private static final long MAX_RESULTS = 5;
    private static final String DEFAULT_LANGUAGE = "es-ES";

    private SpeechRecognizer mSpeechRecognizer;
    private final Context context;

    public RecognizerEngine(Context context) {
        this.context = context;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int error) {
        String mError = "";
        switch (error) {
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                mError = " network timeout";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                mError = " network";
                return;
            case SpeechRecognizer.ERROR_AUDIO:
                mError = " audio";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                mError = " server";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                mError = " client";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                mError = " speech time out";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                mError = " no match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                mError = " recogniser busy";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                mError = " insufficient permissions";
                break;
        }
        EventBus.getDefault().post(new RecognizerEvent(null, true, true));
    }

    @Override
    public void onResults(Bundle results) {
        List<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        EventBus.getDefault().post(new RecognizerEvent(result, false, true));
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    public synchronized void startRecoginizer() {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, context.getString(R.string.recognition_start));
            intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, SPEECH_TIME_MAX);
            intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, SPEECH_TIME_MAX);
            intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, DEFAULT_LANGUAGE);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULTS);

            this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
            mSpeechRecognizer.setRecognitionListener(this);
            mSpeechRecognizer.startListening(intent);
        } else {
            EventBus.getDefault().post(new RecognizerEvent(null, true, false));
        }
    }

    public synchronized void stopRecognizer() {
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.cancel();
        }
    }

    public synchronized void destroy() {
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.cancel();
            mSpeechRecognizer.destroy();
            mSpeechRecognizer = null;
        }
    }
}
