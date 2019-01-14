package note_app.roman.ex2.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface RegFragmentView extends MvpView {

    void initBtnReg();

    void initRegEt();

    void initPasEt();

    void checkLogPassToOpenMain();

}
