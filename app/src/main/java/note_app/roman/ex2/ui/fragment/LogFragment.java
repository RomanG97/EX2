package note_app.roman.ex2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import note_app.roman.ex2.BaseFragment;
import note_app.roman.ex2.R;
import note_app.roman.ex2.mvp.presenter.LogFragmentPresenter;
import note_app.roman.ex2.mvp.view.LogFragmentView;
import note_app.roman.ex2.ui.activity.LogRegActivity;
import note_app.roman.ex2.utils.RxEditText;

public class LogFragment extends BaseFragment implements LogFragmentView {


    private Observable<String> observableUsername;
    private Observable<String> observablePassword;
    private CompositeDisposable disposableCredentials;
    private Button btnLog;

    private View view;

    @InjectPresenter
    LogFragmentPresenter logFragmentPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_log, container, false);

        logFragmentPresenter.initUi();

        return view;
    }

    @Override
    public void initBtn() {
        btnLog = view.findViewById(R.id.btnLog);
        btnLog.setEnabled(false);
        btnLog.setOnClickListener(view -> saveInfoToPref());
    }


    @Override
    public void initLogEtObservable() {
        observableUsername = RxEditText.getTextObservable(view.findViewById(R.id.etLog))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void initPasEtObservable() {
        observablePassword = RxEditText.getTextObservable(view.findViewById(R.id.etPas))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveIfValid() {
        Observable<Boolean> loginObservable =
                logFragmentPresenter.isValidUsername(observableUsername);
        Observable<Boolean> passwordObservable =
                logFragmentPresenter.isValidPassword(observablePassword);

        disposableCredentials = new CompositeDisposable();
        disposableCredentials.add(
                Observable
                        .combineLatest(loginObservable, passwordObservable,
                                (isValidUsername, isValidPassword)
                                        -> isValidUsername && isValidPassword)
                        .subscribe(enabled -> btnLog.setEnabled(enabled)));
    }

    @Override
    public void saveInfoToPref() {
        ((LogRegActivity) Objects.requireNonNull(getActivity()))
                .saveAllInfoToPref(logFragmentPresenter.getLogin(),
                        logFragmentPresenter.getPassword());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        disposableCredentials.dispose();
    }
}
