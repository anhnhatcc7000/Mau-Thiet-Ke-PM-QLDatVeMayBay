package edu.project.TouristTicketOrder.HomePage;

import static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.project.TouristTicketOrder.BottomNavFragment.SettingFragment;
import edu.project.TouristTicketOrder.BottomNavFragment.SearchFragment;
import edu.project.TouristTicketOrder.BottomNavFragment.ManageFragment.ManageFragment;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.R;
public class HomeActivity extends AppCompatActivity {
    DataBaseHandler dataBaseHandler = new DataBaseHandler(HomeActivity.this);
    EditText edt_search;
    public static int maKH;
    Fragment selectedFragment = new SearchFragment();
    private ThemeState themeState;
    SwitchCompat switchMode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor changState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edt_search = findViewById(R.id.edt_search);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
        String username = pref.getString("cusName", null); // null is the default value if the key is not found
        maKH = pref.getInt("cusID", -1);

        TextView tv_userName = findViewById(R.id.tv_userName);
        tv_userName.setText(username);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //show SearchFragment as default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        onSearching();


        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);
        themeState = nightMode ? new DarkMode() : new LightMode(); // Khởi tạo trạng thái ban đầu
        themeState.apply(this); // Áp dụng theme

        SwitchCompat switchMode = findViewById(R.id.switchMode);
        switchMode.setChecked(nightMode);
        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            themeState = isChecked ? new DarkMode() : new LightMode();
            themeState.apply(HomeActivity.this);

            // Lưu trạng thái vào sharedPreferences
            changState = sharedPreferences.edit();
            changState.putBoolean("nightMode", isChecked);
            changState.apply();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
        String username = pref.getString("cusName", null); // null is the default value if the key is not found

        TextView tv_userName = findViewById(R.id.tv_userName);
        String curDisplayUserName = tv_userName.getText().toString();
        if(!curDisplayUserName.equals(username))
        {
            tv_userName.setText(username);

            finish();
            overridePendingTransition(0,0);
            startActivity(getIntent());
            overridePendingTransition(0,0);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item ->
    {
        Fragment selectedFragment = new SearchFragment();

        switch (item.getItemId()) {
            case R.id.mn_search:
                selectedFragment = new SearchFragment();
                edt_search.setHint("Tìm tuyến bay...");
                edt_search.setVisibility(View.VISIBLE);
                break;
            case R.id.mn_manage:
                selectedFragment = new ManageFragment();
                edt_search.setHint("Tìm hoá đơn...");
                edt_search.setVisibility(View.VISIBLE);
                break;
            case R.id.mn_account:
                selectedFragment = new SettingFragment();
                edt_search.setVisibility(View.GONE);
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };

    public void onSearching()
    {
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selectedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (selectedFragment instanceof SearchFragment) {
                    // The selected fragment is a SearchFragment
                    SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    String searchQuery = edt_search.getText().toString();
                    searchFragment.onLoading(searchQuery);
                } else if (selectedFragment instanceof ManageFragment) {
                    // The selected fragment is a ManageFragment
                    ManageFragment manageFragment = (ManageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    String searchQuery = edt_search.getText().toString();
                    manageFragment.onLoading(searchQuery);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}