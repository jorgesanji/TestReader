package mino.com.sttapp.utils;

import android.content.Context;

import mino.com.sttapp.Commons.Common;

/**
 * Created by jorgesanmartin on 3/31/16.
 */
public class DurationUtils {

    public static int getCurrentDuration(Context context) {
        float progress = UserPreferences.getDuration(context);
        float percentProgress = progress * Common.MILLIS_SECONDS;
        return (int) percentProgress;
    }
}
