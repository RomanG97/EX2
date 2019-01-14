package note_app.roman.ex2.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface LogFragmentView extends MvpView {

    void initBtn();

    void initLogEtObservable();

    void initPasEtObservable();

    void saveIfValid();

    void saveInfoToPref();

}
