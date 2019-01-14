package note_app.roman.ex2.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface LogRegView extends MvpView {
    void initBtns();

    void initRegFragment();

    void initLogFragment();

    void showRegFragment();

    void showLogFragment();

    void saveAllInfoToPref(String login, String password);

    void saveLastUser(String login, String password);

}
