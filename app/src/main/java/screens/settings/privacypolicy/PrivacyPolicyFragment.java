package screens.settings.privacypolicy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hanbury.colin.lighthouse.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrivacyPolicyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivacyPolicyFragment extends Fragment {



    public PrivacyPolicyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivacyPolicyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_policy, container, false);
    }
}