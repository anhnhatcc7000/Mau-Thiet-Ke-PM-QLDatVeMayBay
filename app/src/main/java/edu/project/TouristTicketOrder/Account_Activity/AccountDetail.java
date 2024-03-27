package edu.project.TouristTicketOrder.Account_Activity;

import static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;
import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.R;

public class AccountDetail extends AppCompatActivity {

    TextView edit_name, check_name, edit_mail, check_mail, edit_phone, check_phone;
    EditText edt_cusName, edt_cusMail, edt_cusPhone;
    String name, mail, phone;
    CustomerModel customerModel;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        dataBaseHandler = DataBaseHandler.getInstance(getApplicationContext());
        assignVariable();
        onClickListener();

    }

    public void goBack(View view) {
        finish();
    }

    public void assignVariable() {
        customerModel = new CustomerModel();
        pref = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
        editor = pref.edit();
        customerModel.setId(maKH);
        customerModel.setTenKH(pref.getString("cusName", null));
        customerModel.setMail(pref.getString("cusMail", null));
        customerModel.setSDT(pref.getString("cusPhone", null));

        edit_name = findViewById(R.id.edit_name);
        check_name = findViewById(R.id.check_name);
        edit_mail = findViewById(R.id.edit_mail);
        check_mail = findViewById(R.id.check_mail);
        edit_phone = findViewById(R.id.edit_phone);
        check_phone = findViewById(R.id.check_phone);

        edt_cusName = findViewById(R.id.edt_cusName);
        edt_cusName.setText(customerModel.getTenKH());

        edt_cusMail = findViewById(R.id.edt_cusMail);
        edt_cusMail.setText(customerModel.getMail());

        edt_cusPhone = findViewById(R.id.edt_cusPhone);
        edt_cusPhone.setText(customerModel.getSDT());
    }

    public void onClickListener()
    {
        View.OnClickListener onEditListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.edit_name:
                        onEdit(edt_cusName, check_name, edit_name);
                        break;
                    case R.id.edit_phone:
                        onEdit(edt_cusPhone, check_phone, edit_phone);
                        break;
                    case R.id.edit_mail:
                        onEdit(edt_cusMail, check_mail, edit_mail);
                        break;
                }
            }
        };
        edit_name.setOnClickListener(onEditListener);
        edit_mail.setOnClickListener(onEditListener);
        edit_phone.setOnClickListener(onEditListener);

        View.OnClickListener onSaveListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.check_name:
                        name = edt_cusName.getText().toString();
                        if(name.length() >= 3)
                        {
                            dataBaseHandler.updateDataFromKH(maKH, name, "", "");
                            Toast.makeText(getApplicationContext(), "Lưu tên mới thành công", Toast.LENGTH_SHORT).show();
                            editor.putString("cusName", name);
                            onSave(edt_cusName, check_name, edit_name);
                            break;
                        }
                        Toast.makeText(getApplicationContext(), "Tên phải có từ 3 kí tự trở lên", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.check_mail:
                        mail = edt_cusMail.getText().toString();
                        if(ValidateMail(mail) && dataBaseHandler.updateDataFromKH(maKH, "", mail, ""))
                        {
                            Toast.makeText(getApplicationContext(), "Lưu email mới thành công", Toast.LENGTH_SHORT).show();
                            editor.putString("cusMail", mail);
                            onSave(edt_cusMail, check_mail, edit_mail);
                            break;
                        }
                        Toast.makeText(getApplicationContext(), "Email không hợp lệ hoặc đã được sử dụng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.check_phone:
                        phone = edt_cusPhone.getText().toString();
                        if(phone.length() == 11 || phone.length() == 10)
                        {
                            dataBaseHandler.updateDataFromKH(maKH, "", "", phone);
                            Toast.makeText(getApplicationContext(), "Lưu số điện thoại mới thành công", Toast.LENGTH_SHORT).show();
                            editor.putString("cusPhone", phone);
                            onSave(edt_cusPhone, check_phone, edit_phone);
                            break;
                        }
                        Toast.makeText(getApplicationContext(), "Số điện thoại phải có 10 hoặc 11 kí số", Toast.LENGTH_SHORT).show();
                        break;
                }
                editor.apply();
            }
        };
        check_name.setOnClickListener(onSaveListener);
        check_mail.setOnClickListener(onSaveListener);
        check_phone.setOnClickListener(onSaveListener);
    }

    public void onEdit(EditText editText, TextView check, TextView edit)
    {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        edit.setVisibility(View.GONE);
        check.setVisibility(View.VISIBLE);
    }

    public void onSave(EditText editText, TextView check, TextView edit)
    {
        editText.setFocusable(false);
        edit.setVisibility(View.VISIBLE);
        check.setVisibility(View.GONE);
    }

    public boolean ValidateMail(String mail) {
        if(mail.length() > 0) {
            String[] split = mail.split("");

            boolean isMail = false;
            for (String check: split) {
                if(check.equals("@")) {
                    isMail = true;
                    break;
                }
            }

            if(isMail)
                return true;
        }
        return false;
    }
}