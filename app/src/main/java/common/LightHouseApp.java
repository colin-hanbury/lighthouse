package common;

import android.app.Application;

public class LightHouseApp extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}