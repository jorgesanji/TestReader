package mino.com.sttapp.core.view.application;

import android.app.Application;

/**
 * Created by jorgesanmartin on 12/1/15.
 */
public abstract class BaseApplication extends Application implements ApplicationState.Listener {

    private boolean firstLoadApplication = false;

    public abstract void onBackground();

    public abstract void onForeground();

    @Override
    public void onCreate() {
        super.onCreate();
        initLifeCyclerListener();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationState.getInstance().unBind();
    }

    private void initLifeCyclerListener() {
        ApplicationState.init(this).addApplicationStateListener(this);
    }

    @Override
    public void toBackground() {
        onBackground();
    }

    @Override
    public void toForeground() {
        onForeground();
    }

    public boolean isFirstLoadApplication() {
        return firstLoadApplication;
    }

    public void setFirstLoadApplication(boolean firstLoadApplication) {
        this.firstLoadApplication = firstLoadApplication;
    }
}
