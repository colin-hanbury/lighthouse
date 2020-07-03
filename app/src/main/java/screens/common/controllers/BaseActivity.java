package screens.common.controllers;

import androidx.fragment.app.FragmentActivity;

import common.CompositionRoot;

public class BaseActivity extends FragmentActivity {

    private CompositionRoot mCompositionRoot;

    protected CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot(this);
        }
        return mCompositionRoot;
    }
}
