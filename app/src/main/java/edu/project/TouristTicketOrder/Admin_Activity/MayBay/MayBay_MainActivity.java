package edu.project.TouristTicketOrder.Admin_Activity.MayBay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.R;

public class MayBay_MainActivity extends AppCompatActivity {

    EditText edt_name, edt_plate, edt_seats;
    Button btn_ViewAll, btn_Add;
    TextView tv_formError;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(MayBay_MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maybay_main);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_plate = (EditText) findViewById(R.id.edt_plate);
        edt_seats = (EditText) findViewById(R.id.edt_seats);


        tv_formError = (TextView) findViewById(R.id.tv_formError);

        TextWatcher mTextWatcher = new TextWatcher() { // Check input while typing
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = edt_name.getText().toString();
                String plate = edt_plate.getText().toString();
                String seats = edt_seats.getText().toString();

                // Verify input
                boolean checkInput = CheckInput(name, plate, seats);
                if(checkInput) { // Check if input correct then enable ADD button or disable
                    btn_Add.setEnabled(true);
                    tv_formError.setVisibility(View.GONE);
                }
                else {
                    btn_Add.setEnabled(false);

                    tv_formError.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        edt_name.addTextChangedListener(mTextWatcher);
        edt_plate.addTextChangedListener(mTextWatcher);
        edt_seats.addTextChangedListener(mTextWatcher);

        btn_Add = (Button) findViewById(R.id.btn_Add);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MayBayModel mayBayModel = new MayBayModel();

                String name = edt_name.getText().toString();
                String plate = edt_plate.getText().toString();
                String seats = edt_seats.getText().toString();

                mayBayModel.setTenXe(name);
                mayBayModel.setBienSo(plate);
                mayBayModel.setSoGhe(Integer.parseInt(seats));


                boolean isAdded = dataBaseHandler.addMayBay(mayBayModel);
                if(isAdded)
                    Toast.makeText(MayBay_MainActivity.this, "Added: " + mayBayModel.getTenXe(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MayBay_MainActivity.this, "Error Added!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_ViewAll = (Button) findViewById(R.id.btn_ViewAll);
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewMayBayList.class));
            }
        });
    }

    public boolean CheckInput(String name, String plate, String seats) {
        return name.length() > 0 && plate.length() > 0 && seats.length() > 0;
    }
}