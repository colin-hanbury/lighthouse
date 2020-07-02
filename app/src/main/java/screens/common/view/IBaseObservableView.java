package screens.common.view;

public interface IBaseObservableView<ListenerType> extends IBaseView {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}

