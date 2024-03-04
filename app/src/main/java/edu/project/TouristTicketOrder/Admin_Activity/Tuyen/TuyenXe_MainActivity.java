package edu.project.TouristTicketOrder.Admin_Activity.Tuyen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.project.TouristTicketOrder.Admin_Activity.TuyenBay.ViewTuyenXeList;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.R;

public class TuyenXe_MainActivity extends AppCompatActivity {
    EditText edt_startDes, edt_endDes, edt_startAddr, edt_endAddr, edt_gia;
    Button btn_ViewAll, btn_Add;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(TuyenXe_MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_bay_main);
        //
        edt_startDes = (EditText) findViewById(R.id.edt_startDes);
        edt_endDes = (EditText) findViewById(R.id.edt_endDes);
        btn_Add = (Button) findViewById(R.id.btn_Add);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangBayModel changBayModel;

                String noiXuatPhat = edt_startDes.getText().toString();
                String noiDen = edt_endDes.getText().toString();

                String tenTuyen = noiXuatPhat + " - " + noiDen;

                changBayModel = new ChangBayModel(tenTuyen, noiXuatPhat, noiDen, "");

                boolean isAdded = dataBaseHandler.addTuyenXe(changBayModel);
                if(isAdded)
                    Toast.makeText(TuyenXe_MainActivity.this, "Added: " + changBayModel.getTenTuyen(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(TuyenXe_MainActivity.this, "Error Added!!!!", Toast.LENGTH_SHORT).show();
            }
        });
       Button btn_ViewList = (Button)findViewById(R.id.btn_viewTuyenXe);
       btn_ViewList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), ViewTuyenXeList.class));
           }
       });
        btn_ViewAll = (Button) findViewById(R.id.btn_ViewAll);
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_Tuyen_List.class));
            }
        });

        InputValidation();
    }

    public void InputValidation()
    {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_startDes.getText().toString().length() > 0 &&
                        edt_endDes.getText().toString().length() > 0 ) {
                    btn_Add.setBackgroundColor(Color.parseColor("#f2c40d"));
                    btn_Add.setEnabled(true);
                } else {
                    btn_Add.setBackgroundColor(Color.parseColor("#B2A496"));
                    btn_Add.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        edt_startDes.addTextChangedListener(textWatcher);
        edt_endDes.addTextChangedListener(textWatcher);
    }
//    public void viewActiveTour(View view) {
//        startActivity(new Intent(getApplicationContext(), ViewTuyenXeList.class));
//    }
}