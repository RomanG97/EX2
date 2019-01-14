package note_app.roman.ex2.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import note_app.roman.ex2.mvp.view.LogRegView;

@InjectViewState
public class LogRegPresenter extends MvpPresenter<LogRegView> {

    private LogRegView viewState;

    public void initUi() {
        viewState = getViewState();
        viewState.initBtns();
        viewState.initLogFragment();
        viewState.initRegFragment();

    }


}
