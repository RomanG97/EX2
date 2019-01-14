package note_app.roman.ex2.mvp.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Observable;
import note_app.roman.ex2.mvp.view.LogFragmentView;

@InjectViewState
public class LogFragmentPresenter extends MvpPresenter<LogFragmentView> {

    private String login;
    private String password;

    public void initUi() {
        LogFragmentView viewState = getViewState();
        viewState.initBtn();
        viewState.initLogEtObservable();
        viewState.initPasEtObservable();
        viewState.saveIfValid();
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Observable<Boolean> isValidUsername(Observable<String> observableUsername) {
        return observableUsername.flatMap(login -> {
            if (!TextUtils.isEmpty(login)) {
                this.login = login;
                return Observable.fromCallable(() -> true);
            }

            return Observable.fromCallable(() -> false);
        });
    }

    public Observable<Boolean> isValidPassword(Observable<String> observablePassword) {
        return observablePassword.flatMap(password -> {
            if (!TextUtils.isEmpty(password)) {
                this.password = password;
                return Observable.fromCallable(() -> true);
            }

            return Observable.fromCallable(() -> false);
        });
    }
}
