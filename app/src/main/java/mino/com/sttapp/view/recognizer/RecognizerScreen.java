package mino.com.sttapp.view.recognizer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import mino.com.sttapp.Commons.Common;
import mino.com.sttapp.R;
import mino.com.sttapp.utils.DurationUtils;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerScreen extends RelativeLayout {

    private static final int TOTAL_TIME_COUNT_IN_MILLISECONDS = (60 * Common.MILLIS_SECONDS) * 1;

    public interface Listener {
        void destroyRecognizer();
    }

    // Vars
    private Listener listener;
    private CountDownTimer mCountDownTimer;

    // Views
    @Bind(R.id.text)
    TextView mText;

    @Bind(R.id.description)
    TextView mDescription;

    @Bind(R.id.counter)
    TextView mCounter;

    /**
     * @param context
     */
    public RecognizerScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public RecognizerScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RecognizerScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RecognizerScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_recognizerfr, this);
        ButterKnife.bind(this);
        mCounter.setText(getContext().getString(R.string.initial_time));
    }

    private void initCountDown() {

        mCountDownTimer = new CountDownTimer(DurationUtils.getCurrentDuration(getContext()) * 60, Common.MILLIS_SECONDS) {

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(leftTimeInMilliseconds) % 60;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds);
                String text = ((minutes < 10) ? "0" : "") + minutes + " : " + ((seconds < 10) ? "0" : "") + seconds;
                mCounter.setText(text);
            }

            @Override
            public void onFinish() {
                listener.destroyRecognizer();
                mCounter.setText(getContext().getString(R.string.initial_time));
            }

        };
    }

    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setText(String text) {
        mText.setText(text);
    }

    public void setDescription(String description) {
        mDescription.setText(description);
    }

    public void startCountDown() {
        mCounter.setText(getContext().getString(R.string.initial_time));
        if (mCountDownTimer == null) {
            initCountDown();
        }

        mCountDownTimer.start();
    }

    public void stopCountDown() {
        mCounter.setText(getContext().getString(R.string.initial_time));
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

}
