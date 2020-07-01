package hanbury.colin.lighthouse.screens.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import hanbury.colin.lighthouse.common.CompositionRoot;
import hanbury.colin.lighthouse.common.LightHouseApp;

public class BaseActivity extends AppCompatActivity {

    private CompositionRoot mCompositionRoot;

    protected CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot(
                    ((LightHouseApp) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return mCompositionRoot;
    }

}
