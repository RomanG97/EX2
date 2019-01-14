package note_app.roman.ex2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Objects;

import note_app.roman.ex2.BaseActivity;
import note_app.roman.ex2.R;
import note_app.roman.ex2.mvp.presenter.LogRegPresenter;
import note_app.roman.ex2.mvp.view.LogRegView;
import note_app.roman.ex2.ui.fragment.LogFragment;
import note_app.roman.ex2.ui.fragment.RegFragment;
import note_app.roman.ex2.utils.Preference;

public class LogRegActivity extends BaseActivity implements LogRegView {

    private LogFragment logFragment;
    private RegFragment regFragment;
    private FragmentManager fragmentManager;

    @InjectPresenter
    LogRegPresenter logRegPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);

        fragmentManager = getSupportFragmentManager();

        logRegPresenter.initUi();

    }

    @Override
    public void initBtns() {
        Button btnLog = findViewById(R.id.btnLog);
        Button btnReg = findViewById(R.id.btnReg);
        btnLog.setOnClickListener(view -> showLogFragment());
        btnReg.setOnClickListener(view -> showRegFragment());
    }

    @Override
    public void initSupportFragmentManager() {

    }

    @Override
    public void initRegFragment() {
        regFragment = new RegFragment();
    }

    @Override
    public void initLogFragment() {
        logFragment = new LogFragment();
    }

    @Override
    public void showRegFragment() {
        fragmentManager
                .beginTransaction()
                .remove(logFragment)
                .replace(R.id.llFragmentSlot, regFragment)
                .commit();
    }

    @Override
    public void showLogFragment() {
        fragmentManager
                .beginTransaction()
                .remove(regFragment)
                .replace(R.id.llFragmentSlot, logFragment)
                .commit();
    }

    @Override
    public void saveAllInfoToPref(String login, String password) {
        if(!Preference.getLastUser(this).equals(login)){
            Toast.makeText(this,"Wrong login", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Preference.getLastPassword(this).equals(password)){
            Toast.makeText(this,"Wrong password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Preference.getLastUser(this).isEmpty() &&
                Preference.getLastPassword(this).isEmpty()){
            Toast.makeText(this,"Empty login or password", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
        this.finish();
    }

    @Override
    public void checkInfoValidAndOpenMain(String login, String password) {
        Preference.setLastUser(this, login);
        Preference.setLastPassword(this, password);

        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show();

    }

}
