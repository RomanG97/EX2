package note_app.roman.ex2.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface LogFragmentView extends MvpView {

    void initBtn();

    void initLogEt();

    void initPasEt();

    void saveInfoToPref();

}
