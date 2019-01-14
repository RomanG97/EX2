package note_app.roman.ex2.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface RegFragmentView extends MvpView {

    void initBtnReg();

    void initRegEtObservable();

    void initPasEtObservable();

    void checkLogPassToOpenMain();

    void saveIfValid();

}
