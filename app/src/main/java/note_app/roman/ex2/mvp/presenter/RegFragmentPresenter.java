package note_app.roman.ex2.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import note_app.roman.ex2.mvp.view.RegFragmentView;

@InjectViewState
public class RegFragmentPresenter extends MvpPresenter<RegFragmentView> {

    private String login;
    private String password;

    public void initUi() {
        RegFragmentView viewState = getViewState();
        viewState.initBtnReg();
        viewState.initRegEt();
        viewState.initPasEt();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
