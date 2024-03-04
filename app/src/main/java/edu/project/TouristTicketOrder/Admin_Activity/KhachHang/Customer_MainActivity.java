package edu.project.TouristTicketOrder.Admin_Activity.KhachHang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.R;

public class Customer_MainActivity extends AppCompatActivity {
    Button btn_ViewAllCus, btn_AddCus;
    EditText edt_Id, edt_Name, edt_Phone, edt_Pass, edt_Passport,edt_Mail;
    TextView tv_PhoneError, tv_MailError, tv_PassportError;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(Customer_MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer);

        btn_ViewAllCus = (Button) findViewById(R.id.btn_ViewAll);
        btn_AddCus = (Button) findViewById(R.id.btn_Add);

        edt_Name = (EditText) findViewById(R.id.edt_CustomerName);
        edt_Phone = (EditText) findViewById(R.id.edt_Phone);
        edt_Pass = (EditText) findViewById(R.id.edt_Pass);
        edt_Passport = (EditText) findViewById(R.id.edt_Passport);
        edt_Mail = (EditText) findViewById(R.id.edt_Mail);

        tv_PhoneError = (TextView) findViewById(R.id.tv_PhoneError);
        tv_MailError = (TextView) findViewById(R.id.tv_MailError);
        tv_PassportError = (TextView) findViewById(R.id.tv_PassportError);


        // Add button click event listener
        btn_AddCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;

                String name = edt_Name.getText().toString();
                String phone = edt_Phone.getText().toString();
                String mail = edt_Mail.getText().toString();
                String pass = edt_Pass.getText().toString();
                String passport = edt_Passport.getText().toString();


                customerModel = new CustomerModel(name, phone, mail, pass, passport);

                // Check if the information of customer is duplicated
                boolean isAdded =  dataBaseHandler.addOne(customerModel);

                if(isAdded)
                    Toast.makeText(Customer_MainActivity.this, "Added: " + customerModel.getTenKH(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Customer_MainActivity.this, "Already have the same mail or phone number", Toast.LENGTH_SHORT).show();
            }
        });

        TextWatcher mTextWatcher = new TextWatcher() { // Check input while typing
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = edt_Name.getText().toString();
                String phone = edt_Phone.getText().toString();
                String mail = edt_Mail.getText().toString();
                String pass = edt_Pass.getText().toString();
                String passport = edt_Passport.getText().toString();


                String phone_error = "Phone number must be 11 numbers";
                String email_error = "Invalid email";
                String passport_error = "Invalid passport";

                // Verify input
                boolean checkInput = CheckInput(name, phone, mail, pass, passport);
                if(checkInput) { // Check if input correct then enable ADD button or disable
                    btn_AddCus.setEnabled(true);

                    tv_PhoneError.setText("");
                    tv_MailError.setText("");
                    tv_PassportError.setText("");

                }
                 else {
                     btn_AddCus.setEnabled(false);

                    tv_PhoneError.setText(phone_error);
                    tv_MailError.setText(email_error);
                    tv_PassportError.setText(passport_error);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        edt_Name.addTextChangedListener(mTextWatcher);
        edt_Phone.addTextChangedListener(mTextWatcher);
        edt_Mail.addTextChangedListener(mTextWatcher);
        edt_Pass.addTextChangedListener(mTextWatcher);
        edt_Passport.addTextChangedListener(mTextWatcher);


        // View all button click event listener
        btn_ViewAllCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Customer_MainActivity.this, View_Customer_List.class));
            }
        });


    }

    public boolean CheckInput(String name, String phone, String mail, String pass, String passport) {

        if(name.length() > 0 && phone.length() == 11 && mail.length() > 0 && pass.length() > 0 && pass.length() < 11 && passport.length() > 0 && passport.length() < 9 ) {
            // Check input mail is mail
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