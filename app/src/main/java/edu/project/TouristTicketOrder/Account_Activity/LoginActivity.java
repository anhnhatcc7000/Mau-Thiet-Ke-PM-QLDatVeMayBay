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
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

import edu.project.TouristTicketOrder.Admin_Activity.AdminMainActivity;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.LocalDateTimeConvert;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.R;

public class LoginActivity extends AppCompatActivity {
    public static String cus;
    boolean isAdmin = false;
    TextView tv_register;
    EditText edt_mail, edt_pass;
    SharedPreferences lastUser, currentUser;
    LinearLayout parent;
    Switch sw_user;
    SharedPreferences.Editor editorLastUser, editorCurrentUser;
    LocalDateTimeConvert localDateTimeConvert = new LocalDateTimeConvert();
    private DataBaseHandler dataBaseHandler;

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
                    isAdmin = true;
                } else {
                    parent.setBackgroundColor(Color.parseColor("#FFF7E1"));
                    sw_user.setText("Customer");
                    isAdmin = false;
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

        // Khởi tạo DataBaseHandler theo Singleton Pattern
        dataBaseHandler = DataBaseHandler.getInstance(LoginActivity.this);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = edt_mail.getText().toString();
                String pass = edt_pass.getText().toString();

                if (isAdmin) {
                    // Kiểm tra điều kiện cho Admin ở đây
                } else {
                    LoginValidation loginValidation = dataBaseHandler.checkCustomer(mail, pass);
                    if (loginValidation.isCorrect()) {
                        CustomerModel customerModel = loginValidation.getCurUser();

                        cus = customerModel.getId() + "_" + customerModel.getSDT();
                        currentUser = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
                        editorCurrentUser = currentUser.edit();

                        editorCurrentUser.putInt("cusID", customerModel.getId());
                        editorCurrentUser.putString("cusName", customerModel.getTenKH());
                        editorCurrentUser.putString("cusPhone", customerModel.getSDT());
                        editorCurrentUser.putString("cusMail", customerModel.getMail());
                        editorCurrentUser.putString("cusPass", customerModel.getPass());
                        editorCurrentUser.putBoolean("autoLogin", false);
                        editorCurrentUser.apply();

                        lastUser = getApplicationContext().getSharedPreferences("lastUser", MODE_PRIVATE);
                        editorLastUser = lastUser.edit();
                        editorLastUser.putString("lastCus", cus);
                        editorLastUser.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong user name or password", Toast.LENGTH_SHORT).show();
                    }
                }
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

    public static class DataBaseHandler {
        private static DataBaseHandler instance;
        private static SQLiteDatabase database;

        // Tên của database
        private static final String DATABASE_NAME = "YourDatabaseName.db";
        // Phiên bản của database
        private static final int DATABASE_VERSION = 1;

        // Khai báo constructor là private để ngăn việc tạo đối tượng từ bên ngoài lớp
        private DataBaseHandler(Context context) {
            // Khởi tạo database ở đây
            database = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
                @Override
                public void onCreate(SQLiteDatabase db) {
                    // Tạo bảng và các cấu trúc khác cho database
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    // Cập nhật database khi phiên bản thay đổi
                }
            }.getWritableDatabase();
        }

        // Phương thức getInstance để lấy ra phiên bản duy nhất của DataBaseHandler
        public static synchronized DataBaseHandler getInstance(Context context) {
            if (instance == null) {
                instance = new DataBaseHandler(context.getApplicationContext());
            }
            return instance;
        }

        // Các phương thức xử lý cơ sở dữ liệu khác ở đây

        // Thêm phương thức để kiểm tra điều kiện cho Admin
        public boolean CheckConditionNhanVien(NhanVienModel nhanVienModel) {
            // Viết logic kiểm tra ở đây
            return false;
        }

        // Thêm phương thức để kiểm tra người dùng
        public LoginValidation checkCustomer(String mail, String pass) {
            // Viết logic kiểm tra ở đây và trả về đối tượng LoginValidation
            return null;
        }
    }
}
