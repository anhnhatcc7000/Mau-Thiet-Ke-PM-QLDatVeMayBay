package edu.project.TouristTicketOrder.Account_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.R;

public class ResetPassword extends AppCompatActivity {
    EditText edt_newPass, edt_rePass;
    Button btn_savePass;
    DataBaseHandler db = new DataBaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        String mail = getIntent().getExtras().getString("mail");

        edt_newPass = (EditText) findViewById(R.id.edt_newPass);
        edt_rePass = (EditText) findViewById(R.id.edt_rePass);
        btn_savePass = (Button) findViewById(R.id.btn_savePass);
        btn_savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = edt_newPass.getText().toString();
                String rePass = edt_rePass.getText().toString();
                if(rePass.equals(newPass))
                {
                    if(db.updateData(null, null, null, mail, newPass, null))
                    {
                        Toast.makeText(getApplicationContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                }
            }
        });

    }
}