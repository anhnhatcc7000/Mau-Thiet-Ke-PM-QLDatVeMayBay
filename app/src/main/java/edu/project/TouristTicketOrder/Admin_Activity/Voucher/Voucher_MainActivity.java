package edu.project.TouristTicketOrder.Admin_Activity.Voucher;

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
import edu.project.TouristTicketOrder.Admin_Activity.KhachHang.View_Customer_List;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.VoucherModel;
import edu.project.TouristTicketOrder.R;

public class Voucher_MainActivity extends AppCompatActivity {

    Button btn_ViewAll, btn_AddVoucher;
    EditText edt_MaVoucher, edt_MoTa, edt_GiamGia, edt_NgayBatDau, edt_NgayKetThuc;
    TextView tv_PhoneError, tv_MailError, tv_PassportError;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(Voucher_MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        btn_ViewAll = (Button) findViewById(R.id.btn_ViewAll);
        btn_AddVoucher = (Button) findViewById(R.id.btn_AddVoucher);

        edt_MaVoucher = (EditText) findViewById(R.id.edt_MaVoucher);
        edt_MoTa = (EditText) findViewById(R.id.edt_MoTa);
        edt_GiamGia = (EditText) findViewById(R.id.edt_NgayBatDau);
        edt_NgayBatDau = (EditText) findViewById(R.id.edt_NgayKetThuc);
        edt_NgayKetThuc = (EditText) findViewById(R.id.edt_GiamGia);

        tv_PhoneError = (TextView) findViewById(R.id.tv_PhoneError);
        tv_MailError = (TextView) findViewById(R.id.tv_MailError);
        tv_PassportError = (TextView) findViewById(R.id.tv_PassportError);

        // Add button click event listener
//        btn_AddVoucher.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String ma = edt_MaVoucher.getText().toString();
//                String mota = edt_MoTa.getText().toString();
//                String giam = edt_GiamGia.getText().toString();
//                String start = edt_NgayBatDau.getText().toString();
//                String end = edt_NgayKetThuc.getText().toString();
//
//                VoucherModel voucherModel = new VoucherModel.Builder()
//                        .maVoucher(ma)
//                        .moTa(mota)
//                        .giamGia(giam)
//                        .ngayBatDau(start)
//                        .ngayKetThuc(end)
//                        .build();
//
//                // Check if the information of customer is duplicated
//                boolean isAdded =  dataBaseHandler.addVoucher(voucherModel);
//
//                if(isAdded)
//                    Toast.makeText(Voucher_MainActivity.this, "Added: " + voucherModel.getMaVoucher(), Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(Voucher_MainActivity.this, "Already voucher", Toast.LENGTH_SHORT).show();
//            }
//        });

        TextWatcher mTextWatcher = new TextWatcher() { // Check input while typing
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String ma = edt_MaVoucher.getText().toString();
                String mota = edt_MoTa.getText().toString();
                String giam = edt_GiamGia.getText().toString();
                String start = edt_NgayBatDau.getText().toString();
                String end = edt_NgayKetThuc.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        edt_MaVoucher.addTextChangedListener(mTextWatcher);
        edt_MoTa.addTextChangedListener(mTextWatcher);
        edt_GiamGia.addTextChangedListener(mTextWatcher);
        edt_NgayBatDau.addTextChangedListener(mTextWatcher);
        edt_NgayKetThuc.addTextChangedListener(mTextWatcher);


        // View all button click event listener
        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Voucher_MainActivity.this, ViewVoucher.class));
            }
        });


    }
    public void AddVoucher(View view){
        String ma = edt_MaVoucher.getText().toString();
        String mota = edt_MoTa.getText().toString();
        String giam = edt_GiamGia.getText().toString();
        String start = edt_NgayBatDau.getText().toString();
        String end = edt_NgayKetThuc.getText().toString();

        VoucherModel voucherModel = new VoucherModel.VoucherBuilder()
                .maVoucher(ma)
                .moTa(mota)
                .giamGia(giam)
                .ngayBatDau(start)
                .ngayKetThuc(end)
                .build();

        // Check if the information of customer is duplicated
        boolean isAdded =  dataBaseHandler.addVoucher(voucherModel);

        if(isAdded)
            Toast.makeText(Voucher_MainActivity.this, "Added: " + voucherModel.getMaVoucher(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Voucher_MainActivity.this, "Already voucher", Toast.LENGTH_SHORT).show();
    }
}