package edu.project.TouristTicketOrder.Account_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.R;

public class RegisterActivity extends AppCompatActivity {
    TextView tv_login;
    ImageButton ib_back;
    EditText edt_name, edt_mail, edt_phone, edt_pass, edt_rePass;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_login = findViewById(R.id.tv_login);
        ib_back = findViewById(R.id.ib_back);

        Button btn_register = findViewById(R.id.btn_register);

        //click on the "Login" to go to LoginActivity
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //click on the arrow back icon to go to LoginActivity
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        edt_name = findViewById(R.id.edt_name);
        edt_mail = findViewById(R.id.edt_mail);
        edt_phone = findViewById(R.id.edt_phone);
        edt_pass = findViewById(R.id.edt_pass);
        edt_rePass = findViewById(R.id.edt_rePass);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name =  edt_name.getText().toString();
                String mail =  edt_mail.getText().toString();
                String phone =  edt_phone.getText().toString();
                String pass =  edt_pass.getText().toString();
                String rePass =  edt_rePass.getText().toString();

                boolean checkInput = CheckInput(name, mail, phone, pass, rePass);

                if(checkInput) {
                    btn_register.setEnabled(true);
                } else {
                    btn_register.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        edt_name.addTextChangedListener(textWatcher);
        edt_mail.addTextChangedListener(textWatcher);
        edt_phone.addTextChangedListener(textWatcher);
        edt_pass.addTextChangedListener(textWatcher);
        edt_rePass.addTextChangedListener(textWatcher);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  edt_name.getText().toString();
                String mail =  edt_mail.getText().toString();
                String phone =  edt_phone.getText().toString();
                String pass =  edt_pass.getText().toString();


                CustomerModel customerModel = new CustomerModel(name, phone, mail, pass);

                boolean isRegistered = dataBaseHandler.addOne(customerModel);

                if(isRegistered) {
                    Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "Mail or Phone number is already used", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean CheckInput(String name, String mail, String phone, String pass, String rePass) {
        if(name.length() > 0 && mail.length() > 0 && (phone.length() == 11 || phone.length() == 10) && pass.length() == 11 && rePass.equals(pass)) {
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