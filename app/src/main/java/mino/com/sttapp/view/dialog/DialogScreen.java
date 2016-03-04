package mino.com.sttapp.view.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mino.com.sttapp.R;

/**
 * Created by jorgesanmartin on 10/26/15.
 */
public class DialogScreen extends LinearLayout {


    // Variables

    private RecyclerView.LayoutManager mLayoutManager;

    // Views
    @Bind(R.id.result)
    TextView mResult;

    @Bind(R.id.textFromDictation)
    TextView mText;

    /**
     * @param context
     */
    public DialogScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public DialogScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public DialogScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public DialogScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_response, this);
        ButterKnife.bind(this);
    }

    // Public methods

    public void setText(String text) {
        mText.setText(text);
    }

    public void setResult(String result) {
        mResult.setText(result);
    }
}
