package note_app.roman.ex2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class BaseActivity extends MvpAppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        fragmentManager = getSupportFragmentManager();
    }

    public FragmentManager getBaseFragmentManager() {
        return fragmentManager;
    }
}
