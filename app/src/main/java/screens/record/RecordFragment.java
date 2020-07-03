package screens.record;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import screens.common.controllers.BaseFragment;


public class RecordFragment extends BaseFragment implements IRecordView.Listener{

    public static RecordFragment newInstance(){ //String questionId) {
        //Bundle args = new Bundle();
        //args.putString(ARG_QUESTION_ID, questionId);
        RecordFragment fragment = new RecordFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    private IRecordView mIRecordView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mIRecordView = getCompositionRoot().getLightHouseViewFactory().getRecordView(container);
        return mIRecordView.getRootView();
    }


    @Override
    public void onBackClicked() {
        //back
    }

    @Override
    public void onLogoutClicked() {
        //logout
    }
}