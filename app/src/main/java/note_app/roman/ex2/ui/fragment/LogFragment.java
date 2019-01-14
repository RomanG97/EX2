package note_app.roman.ex2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Objects;

import note_app.roman.ex2.BaseFragment;
import note_app.roman.ex2.R;
import note_app.roman.ex2.mvp.presenter.LogFragmentPresenter;
import note_app.roman.ex2.mvp.view.LogFragmentView;
import note_app.roman.ex2.ui.activity.LogRegActivity;

public class LogFragment extends BaseFragment implements LogFragmentView {

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
        Button btnLog = view.findViewById(R.id.btnLog);
        btnLog.setOnClickListener(view -> saveInfoToPref());
    }

    @Override
    public void initLogEt() {
        EditText etLog = view.findViewById(R.id.etLog);
        etLog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                logFragmentPresenter.setLogin(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPasEt() {
        EditText etPas = view.findViewById(R.id.etPas);
        etPas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                logFragmentPresenter.setPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void saveInfoToPref() {
        ((LogRegActivity) Objects.requireNonNull(getActivity()))
                .saveAllInfoToPref(logFragmentPresenter.getLogin(),
                logFragmentPresenter.getPassword());
    }


}
