package screens.common.controllers;

import androidx.fragment.app.FragmentActivity;

import common.CompositionRoot;
import common.LightHouseApp;

public class BaseActivity extends FragmentActivity {

    private CompositionRoot mCompositionRoot;

    protected CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot(this);
                    //((LightHouseApp) getApplication()).getCompositionRoot();
        }
        return mCompositionRoot;
    }
}
