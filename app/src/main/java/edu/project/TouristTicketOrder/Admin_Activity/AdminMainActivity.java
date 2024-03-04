package edu.project.TouristTicketOrder.Admin_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.project.TouristTicketOrder.Admin_Activity.CTHD.CTHD_MainActivity;
import edu.project.TouristTicketOrder.Admin_Activity.KhachHang.Customer_MainActivity;
import edu.project.TouristTicketOrder.Admin_Activity.NhanVien.NhanVien_MainActivity;
import edu.project.TouristTicketOrder.Admin_Activity.Tuyen.TuyenXe_MainActivity;
import edu.project.TouristTicketOrder.Admin_Activity.MayBay.MayBay_MainActivity;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.R;


public class AdminMainActivity extends AppCompatActivity {
    TextView tv_doanhThu, tv_totalHD;

    private static final int REQUEST_CODE_CTHD_ACTIVITY = 1;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminMainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        tv_doanhThu = (TextView)findViewById(R.id.tv_doanhthu);
        tv_totalHD = (TextView)findViewById(R.id.tv_hoadon);
        Button btn_KhachHang = (Button) findViewById(R.id.btn_KhachHang);
        Button btn_Xe = (Button) findViewById(R.id.btn_Xe);
        Button btn_TuyenXe = (Button) findViewById(R.id.btn_TuyenXe);
        Button btn_NhanVien = (Button) findViewById(R.id.btn_NhanVien);
        Button btn_CTHD = (Button) findViewById(R.id.btn_CTHD);
        getThongKe();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_KhachHang:
                        startActivity(new Intent(getApplicationContext(), Customer_MainActivity.class));
                        break;
                    case R.id.btn_Xe:
                        startActivity(new Intent(getApplicationContext(), MayBay_MainActivity.class));
                        break;
                    case R.id.btn_TuyenXe:
                        startActivity(new Intent(getApplicationContext(), TuyenXe_MainActivity.class));
                        break;
                    case R.id.btn_NhanVien:
                        startActivity(new Intent(getApplicationContext(), NhanVien_MainActivity.class));
                        break;
                    case R.id.btn_CTHD:
                        startActivity(new Intent(getApplicationContext(), CTHD_MainActivity.class));
                        break;
                }
            }
        };

        btn_KhachHang.setOnClickListener(clickListener);
        btn_TuyenXe.setOnClickListener(clickListener);
        btn_NhanVien.setOnClickListener(clickListener);
        btn_CTHD.setOnClickListener(clickListener);
        btn_Xe.setOnClickListener(clickListener);
    }
    public void getThongKe(){
        int doanhthu = dataBaseHandler.getTotalRevenue();
        int hoadon = dataBaseHandler.getNumberOfOrders();
        tv_doanhThu.setText(Integer.toString(doanhthu));
        tv_totalHD.setText(Integer.toString(hoadon));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getThongKe();
    }

}