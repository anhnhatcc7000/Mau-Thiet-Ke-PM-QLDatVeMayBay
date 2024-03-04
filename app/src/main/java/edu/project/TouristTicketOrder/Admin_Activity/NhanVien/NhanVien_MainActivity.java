package edu.project.TouristTicketOrder.Admin_Activity.NhanVien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.R;

public class NhanVien_MainActivity extends AppCompatActivity {
    EditText edt_empName, edt_empCCCD, edt_empAddr,edt_empPhone, edt_empMail;
    Button btn_ViewAll, btn_Add;
    TextView tv_PhoneError, tv_MailError, tv_formError, tv_IdError;

    String empRole;
    RadioGroup rd_role;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(NhanVien_MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien_main);

        edt_empName = (EditText) findViewById(R.id.edt_empName);
        edt_empCCCD = (EditText) findViewById(R.id.edt_empCCCD);
        edt_empAddr = (EditText) findViewById(R.id.edt_empAddr);
        edt_empPhone = (EditText) findViewById(R.id.edt_empPhone);
        edt_empMail = (EditText) findViewById(R.id.edt_empMail);


        tv_PhoneError = (TextView) findViewById(R.id.tv_PhoneError);
        tv_MailError = (TextView) findViewById(R.id.tv_MailError);
        tv_formError = (TextView) findViewById(R.id.tv_formError);
        tv_IdError = (TextView) findViewById(R.id.tv_IdError);

        rd_role = (RadioGroup) findViewById(R.id.rd_role);
        rd_role.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_roleEmp:
                        // do something when radio button 1 is selected
                        empRole = "Employee";
                        break;
                    case R.id.rd_roleAdmin:
                        // do something when radio button 2 is selected
                        empRole = "Admin";
                        break;
                }
            }
        });

        TextWatcher mTextWatcher = new TextWatcher() { // Check input while typing
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String empName = edt_empName.getText().toString();
                String empAddr = edt_empAddr.getText().toString();
                String empPhone = edt_empPhone.getText().toString();
                String empMail = edt_empMail.getText().toString();
                String empCCCD = edt_empCCCD.getText().toString();

                String phone_error = "Phone number must be 11 numbers";
                String email_error = "Invalid email";
                String cccd_error = "Identifier number must be 12 numbers";
                String form_eror = "Employee information must be fulfilled";

                // Verify input
                boolean checkInput = CheckInput(empName, empAddr, empPhone, empMail, empCCCD);
                if(checkInput) { // Check if input correct then enable ADD button or disable
                    btn_Add.setEnabled(true);

                    tv_PhoneError.setText("");
                    tv_MailError.setText("");
                    tv_formError.setText("");
                    tv_IdError.setText("");
                }
                else {
                    btn_Add.setEnabled(false);

                    tv_PhoneError.setText(phone_error);
                    tv_MailError.setText(email_error);
                    tv_IdError.setText(cccd_error);
                    tv_formError.setText(form_eror);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        edt_empName.addTextChangedListener(mTextWatcher);
        edt_empAddr.addTextChangedListener(mTextWatcher);
        edt_empPhone.addTextChangedListener(mTextWatcher);
        edt_empMail.addTextChangedListener(mTextWatcher);
        edt_empCCCD.addTextChangedListener(mTextWatcher);

        btn_Add = (Button) findViewById(R.id.btn_Add);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVienModel nhanVienModel;

                String empName = edt_empName.getText().toString();
                String empAddr = edt_empAddr.getText().toString();
                String empPhone = edt_empPhone.getText().toString();
                String empMail = edt_empMail.getText().toString();
                String empCCCD = edt_empCCCD.getText().toString();


                nhanVienModel = new NhanVienModel(empName, empCCCD, empAddr, empPhone, empMail, empRole);

                boolean isAdded = dataBaseHandler.addNhanVien(nhanVienModel);
                if(isAdded)
                    Toast.makeText(NhanVien_MainActivity.this, "Added: " + nhanVienModel.getEmpName(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NhanVien_MainActivity.this, "Error Added!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_ViewAll = (Button) findViewById(R.id.btn_ViewAll);
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_NhanVien_List.class));
            }
        });
    }

    public boolean CheckInput(String empName, String empAddr, String empPhone, String empMail, String empCCCD) {

        if(empName.length() > 0 && empPhone.length() == 11 && empMail.length() > 0 && empAddr.length() > 0 && empCCCD.length() == 12) {
            // Check input mail is mail
            String[] split = empMail.split("");

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