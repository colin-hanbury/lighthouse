package hanbury.colin.lighthouse.common;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import hanbury.colin.lighthouse.screens.common.LightHouseViewFactory;


public class CompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mActivity;

    public CompositionRoot(CompositionRoot compositionRoot, FragmentActivity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    private FragmentActivity getActivity() {
        return mActivity;
    }

    private Context getContext() {
        return mActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public LightHouseViewFactory getLightHouseViewFactory() {
        return new LightHouseViewFactory(getLayoutInflater() );//, getNavDrawerHelper());
    }

    //    private NavDrawerHelper getNavDrawerHelper() {
//        return (NavDrawerHelper) getActivity();
//    }
//
//    public FetchPhuks getFetchPhuks() {
//        return new FetchPhuks();
//    }
//    public FetchPhukProfiler getFetchPhukProfiler() {
//        return new FetchPhukProfiler();
//    }
//    public FetchMatches getFetchMatches() {
//        return new FetchMatches();
//    }
//
//
//    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase() {
//        return new FetchLastActiveQuestionsUseCase(getStackoverflowApi());
//    }
//
//    public QuestionsListController getQuestionsListController() {
//        return new QuestionsListController(
//                getFetchLastActiveQuestionsUseCase(),
//                getScreensNavigator(),
//                getDialogsManager(),
//                getDialogsEventBus()
//        );
//    }
//
//    public ToastHelper getToastsHelper() {
//        return new ToastHelper(getContext());
//    }
//
//    public FetchGoogleSignInClient getFetchGoogleSignInClient(){
//        return new FetchGoogleSignInClient(getContext());
//    }
//    public FetchSilentUserAuth getFetchSilentUserAuth(){
//        return new FetchSilentUserAuth();
//    }
//    public FetchUserAuth getFetchUserAuth(){
//        return new FetchUserAuth(getContext());
//    }
//    public FetchFirebaseUser getFetchFirebaseUser(){
//        return new FetchFirebaseUser();
//    }
//    public FetchMatchAttributes getFetchMatchAttributes() {
//        return new FetchMatchAttributes();
//    }
//
//    public FetchLogout getFetchLogout(){
//        return new FetchLogout(getContext());
//    }
//
//    public ScreensNavigator getScreensNavigator() {
//        return new ScreensNavigator(getFragmentFrameHelper());
//    }
//
//    private FragmentFrameHelper getFragmentFrameHelper() {
//        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
//    }
//
//    private FragmentFrameWrapper getFragmentFrameWrapper() {
//        return (FragmentFrameWrapper) getActivity();
//    }
//
//    public BackPressDispatcher getBackPressDispatcher() {
//        return (BackPressDispatcher) getActivity();
//    }
//
//
//

//    public DialogsManager getDialogsManager() {
//        return new DialogsManager(getContext(), getFragmentManager());
//    }
//
//    public DialogsEventBus getDialogsEventBus() {
//        return mCompositionRoot.getDialogsEventBus();
//    }
}