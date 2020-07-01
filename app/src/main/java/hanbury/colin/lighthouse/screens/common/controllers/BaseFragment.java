package hanbury.colin.lighthouse.screens.common.controllers;

import androidx.fragment.app.Fragment;

import hanbury.colin.lighthouse.common.CompositionRoot;
import hanbury.colin.lighthouse.common.LightHouseApp;

public class BaseFragment extends Fragment {

    private CompositionRoot mCompositionRoot;

    protected CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot(
                    ((LightHouseApp) requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return mCompositionRoot;
    }
}
