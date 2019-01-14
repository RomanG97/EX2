package note_app.roman.ex2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import note_app.roman.ex2.BaseActivity;
import note_app.roman.ex2.R;
import note_app.roman.ex2.mvp.presenter.LogRegPresenter;
import note_app.roman.ex2.mvp.view.LogRegView;
import note_app.roman.ex2.ui.fragment.LogFragment;
import note_app.roman.ex2.ui.fragment.RegFragment;
import note_app.roman.ex2.utils.Preference;

public class LogRegActivity extends BaseActivity implements LogRegView {

    private FragmentManager fragmentManager;
    private LinearLayout llFragmentSlotReg;
    private LinearLayout llFragmentSlotLog;

    @InjectPresenter
    LogRegPresenter logRegPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);

        fragmentManager = getSupportFragmentManager();

        logRegPresenter.initUi();

        llFragmentSlotLog = findViewById(R.id.llFragmentSlotLog);
        llFragmentSlotReg = findViewById(R.id.llFragmentSlotReg);

    }

    @Override
    public void initBtns() {
        Button btnLog = findViewById(R.id.btnLog);
        Button btnReg = findViewById(R.id.btnReg);
        btnLog.setOnClickListener(view -> showLogFragment());
        btnReg.setOnClickListener(view -> showRegFragment());
    }

    @Override
    public void initRegFragment() {
        RegFragment regFragment = new RegFragment();

        fragmentManager
                .beginTransaction()
                .add(R.id.llFragmentSlotReg, regFragment)
                .commit();

        llFragmentSlotReg.setVisibility(View.GONE);
    }

    @Override
    public void initLogFragment() {
        LogFragment logFragment = new LogFragment();

        fragmentManager
                .beginTransaction()
                .add(R.id.llFragmentSlotLog, logFragment)
                .commit();

        llFragmentSlotReg.setVisibility(View.GONE);
    }

    @Override
    public void showRegFragment() {
        llFragmentSlotLog.setVisibility(View.GONE);
        llFragmentSlotReg.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLogFragment() {
        llFragmentSlotReg.setVisibility(View.GONE);
        llFragmentSlotLog.setVisibility(View.VISIBLE);
    }

    @Override
    public void saveAllInfoToPref(String login, String password) {
        if (!Preference.getLastUser(this).equals(login)) {
            Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Preference.getLastPassword(this).equals(password)) {
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
        this.finish();
    }

    @Override
    public void saveLastUser(String login, String password) {
        Preference.setLastUser(this, login);
        Preference.setLastPassword(this, password);

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

    }

}
