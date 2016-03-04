package mino.com.sttapp.model;

import java.io.Serializable;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class RecognitionResult implements Serializable {

    private final String result;
    private final int numberWords;
    private final int correctWords;
    private final int incorrectWords;

    public RecognitionResult(String result, int numberWords, int correctWords, int incorrectWords) {
        this.result = result;
        this.numberWords = numberWords;
        this.correctWords = correctWords;
        this.incorrectWords = incorrectWords;
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
}
