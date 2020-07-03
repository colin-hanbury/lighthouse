package screens.common.controllers;

import androidx.fragment.app.Fragment;

import common.CompositionRoot;

public class BaseFragment extends Fragment {

    private CompositionRoot mCompositionRoot;

    protected CompositionRoot getCompositionRoot() {
        if (mCompositionRoot == null) {
            mCompositionRoot = new CompositionRoot(requireActivity());
        }
        return mCompositionRoot;
    }
}
