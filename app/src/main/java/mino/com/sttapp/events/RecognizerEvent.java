package mino.com.sttapp.events;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerEvent {
    private final List<String> result;
    private final boolean error;
    private final boolean recognizerActivated;

    public RecognizerEvent(List<String> result, boolean error, boolean recognizerActivated) {
        this.result = result;
        this.error = error;
        this.recognizerActivated = recognizerActivated;
    }

    public List<String> getResult() {
        return result;
    }

    public boolean hasError() {
        return error;
    }

    public boolean isRecognizerActivated() {
        return recognizerActivated;
    }
}
