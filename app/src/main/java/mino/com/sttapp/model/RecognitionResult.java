package mino.com.sttapp.model;

import java.io.Serializable;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class RecognitionResult implements Serializable {

    private final String result;
    private final String messageResult;
    private final int numberWords;
    private final int correctWords;
    private final int incorrectWords;
    private final int totalWords;

    public RecognitionResult(String result, String messageResult, int numberWords, int correctWords, int incorrectWords, int totalWords) {
        this.result = result;
        this.messageResult = messageResult;
        this.numberWords = numberWords;
        this.correctWords = correctWords;
        this.incorrectWords = incorrectWords;
        this.totalWords = totalWords;
    }

    public int getNumberWords() {
        return numberWords;
    }

    public int getCorrectWords() {
        return correctWords;
    }

    public int getIncorrectWords() {
        return incorrectWords;
    }

    public String getResult() {
        return result;
    }

    public String getMessageResult() {
        return messageResult;
    }

    public int getTotalWords() {
        return totalWords;
    }
}
