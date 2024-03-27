package edu.project.TouristTicketOrder.Account_Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

import edu.project.TouristTicketOrder.Admin_Activity.AdminMainActivity;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.LocalDateTimeConvert;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.R;

// Interface for both the original class and the proxy
interface LoginInterface {
    void login(String mail, String pass);
}

// Original class
class OriginalLogin implements LoginInterface {
    private LoginActivity loginActivity;

    public OriginalLogin(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    public void login(String mail, String pass) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(loginActivity);
        LoginValidation loginValidation = dataBaseHandler.checkCustomer(mail, pass);
        if (loginValidation.isCorrect()) {
            CustomerModel customerModel = loginValidation.getCurUser();

            LoginActivity.cus = customerModel.getId() + "_" + customerModel.getSDT();
            SharedPreferences currentUser = loginActivity.getApplicationContext().getSharedPreferences(LoginActivity.cus, AppCompatActivity.MODE_PRIVATE);
            SharedPreferences.Editor editorCurrentUser = currentUser.edit();

            editorCurrentUser.putInt("cusID", customerModel.getId());
            editorCurrentUser.putString("cusName", customerModel.getTenKH());
            editorCurrentUser.putString("cusPhone", customerModel.getSDT());
            editorCurrentUser.putString("cusMail", customerModel.getMail());
            editorCurrentUser.putString("cusPass", customerModel.getPass());
            editorCurrentUser.putBoolean("autoLogin", false);
            editorCurrentUser.apply();

            SharedPreferences lastUser = loginActivity.getApplicationContext().getSharedPreferences("lastUser", AppCompatActivity.MODE_PRIVATE);
            SharedPreferences.Editor editorLastUser = lastUser.edit();
            editorLastUser.putString("lastCus", LoginActivity.cus);
            editorLastUser.apply();

            Intent intent = new Intent(loginActivity, HomeActivity.class);
            loginActivity.startActivity(intent);
        } else {
            Toast.makeText(loginActivity.getApplicationContext(), "Wrong user name or password", Toast.LENGTH_SHORT).show();
        }
    }
}

// Proxy class
class ProxyLogin implements LoginInterface {
    private OriginalLogin originalLogin;
    private LoginActivity loginActivity;
    private boolean isAdmin = false;

    public ProxyLogin(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.originalLogin = new OriginalLogin(loginActivity);
    }

    @Override
    public void login(String mail, String pass) {
        if (isAdmin) {
            // Perform additional checks or actions for admin login, if needed
            NhanVienModel nhanVienModel = new NhanVienModel();
            nhanVienModel.setEmpMail(mail);
            nhanVienModel.setEmpCCCD("");
            nhanVienModel.setEmpPhone("");
            DataBaseHandler dataBaseHandler = new DataBaseHandler(loginActivity);
            if (dataBaseHandler.CheckConditionNhanVien(nhanVienModel)) {
                Intent intent = new Intent(loginActivity, AdminMainActivity.class);
                loginActivity.startActivity(intent);
            }
        } else {
            originalLogin.login(mail, pass);
        }
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

public class LoginActivity extends AppCompatActivity {
    public static String cus;
    TextView tv_register;
    EditText edt_mail, edt_pass;
    LinearLayout parent;
    Switch sw_user;
    SharedPreferences lastUser, currentUser;
    SharedPreferences.Editor editorLastUser, editorCurrentUser;
    LocalDateTimeConvert localDateTimeConvert = new LocalDateTimeConvert();
    private ProxyLogin proxyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AutoLoginValidate();
        Date date = new Date();
        try {
            String dateTime = localDateTimeConvert.getOtherWeekFromDate(date.toString(), 1);
            Toast.makeText(getApplicationContext(), "" + dateTime, Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        edt_mail = findViewById(R.id.edt_mail);
        edt_pass = findViewById(R.id.edt_pass);

        parent = findViewById(R.id.parent);
        sw_user = findViewById(R.id.sw_user);
        sw_user.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    parent.setBackgroundColor(Color.parseColor("#88ccf1"));
                    sw_user.setText("Admin");
                    proxyLogin.setIsAdmin(true);
                } else {
                    parent.setBackgroundColor(Color.parseColor("#FFF7E1"));
                    sw_user.setText("Customer");
                    proxyLogin.setIsAdmin(false);
                }
            }
        });
        //click on the "Register" to go to RegisterActivity
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        proxyLogin = new ProxyLogin(this);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = edt_mail.getText().toString();
                String pass = edt_pass.getText().toString();
                proxyLogin.login(mail, pass);
            }
        });

        TextView tv_fgtPass = findViewById(R.id.tv_fgtPass);
        tv_fgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AutoLoginValidate() {
        lastUser = getApplicationContext().getSharedPreferences("lastUser", MODE_PRIVATE);
        String user = lastUser.getString("lastCus", null);
        if (user != null) {
            currentUser = getApplicationContext().getSharedPreferences(user, MODE_PRIVATE);
            boolean autoLogin = currentUser.getBoolean("autoLogin", false);
            if (autoLogin) {
                cus = user;
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }
    }
}
