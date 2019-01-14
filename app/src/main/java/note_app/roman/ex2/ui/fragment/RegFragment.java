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
import note_app.roman.ex2.mvp.presenter.RegFragmentPresenter;
import note_app.roman.ex2.mvp.view.RegFragmentView;
import note_app.roman.ex2.ui.activity.LogRegActivity;
import note_app.roman.ex2.utils.RxEditText;

public class RegFragment extends BaseFragment implements RegFragmentView {

    private Observable<String> observableUsername;
    private Observable<String> observablePassword;
    private CompositeDisposable disposableCredentials;
    private Button btnReg;

    View view;

    @InjectPresenter
    RegFragmentPresenter regFragmentPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_reg, container, false);

        regFragmentPresenter.initUi();

        return view;
    }

    @Override
    public void initBtnReg() {
        btnReg = view.findViewById(R.id.btnReg);
        //btnReg.setEnabled(false);
        btnReg.setOnClickListener(view -> checkLogPassToOpenMain());
    }

    @Override
    public void initRegEtObservable() {
        observableUsername = RxEditText.getTextObservable(view.findViewById(R.id.etReg))
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
                regFragmentPresenter.isValidUsername(observableUsername);
        Observable<Boolean> passwordObservable =
                regFragmentPresenter.isValidPassword(observablePassword);

        disposableCredentials = new CompositeDisposable();
        disposableCredentials.add(
                Observable
                        .combineLatest(loginObservable, passwordObservable,
                                (isValidUsername, isValidPassword)
                                        -> isValidUsername && isValidPassword)
                        .subscribe(enabled -> btnReg.setEnabled(enabled)));
    }


    @Override
    public void checkLogPassToOpenMain() {
        ((LogRegActivity) Objects.requireNonNull(getActivity()))
                .saveLastUser(regFragmentPresenter.getLogin(),
                        regFragmentPresenter.getPassword());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposableCredentials.dispose();
    }
}
