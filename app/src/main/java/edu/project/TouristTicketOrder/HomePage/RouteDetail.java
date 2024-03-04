package edu.project.TouristTicketOrder.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.project.TouristTicketOrder.BookingPage.BookingDetail;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

public class RouteDetail extends AppCompatActivity {
    TextView tv_sanBayDi, tv_sanBayDen, tv_startDes, tv_date, btn_book, tv_price, tv_time, tv_loaiVe, tv_hangGhe;
    public static int seat;
    TuyenBayModel tuyenModel;
    public static String date, time;
    ImageButton ib_back;
    RadioGroup rg_changeSeat;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(RouteDetail.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        Intent intent = getIntent();
        tuyenModel = (TuyenBayModel) intent.getSerializableExtra("tuyenModel");
        date = intent.getStringExtra("Date");
        assignVariable();
        bookButtonClick();
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void assignVariable() {
        tv_startDes = (TextView) findViewById(R.id.tv_startDes);
        tv_sanBayDi = (TextView) findViewById(R.id.tv_sanBayDi);
        tv_sanBayDen = (TextView) findViewById(R.id.tv_sanBayDen);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_loaiVe = (TextView) findViewById(R.id.tv_loaiVe);
        tv_hangGhe = (TextView) findViewById(R.id.tv_hangGhe);

        TextView tv_xe = findViewById(R.id.tv_xe);
        tv_xe.setText("Máy bay " + tuyenModel.getTenMB());

        String[] temp = tuyenModel.getTenTuyen().split("-");
        String tenTuyen = temp[0] + " --> " + temp[1];
        tv_startDes.setText(tenTuyen);

        String[] split = tuyenModel.getNgayKhoihanh().split("-");
        tv_date.setText(split[0]);
        tv_time.setText(split[1]);

        tv_sanBayDi.setText(tuyenModel.getSanBayDi());
        tv_sanBayDen.setText(tuyenModel.getSanBayDen());

        String price = "VND " + tuyenModel.getGia() + "đ/khách";
        tv_price.setText(price);

        ib_back = findViewById(R.id.ib_back);
        btn_book = (TextView) findViewById(R.id.btn_book);

        tv_loaiVe.setText("Loại vé: " + tuyenModel.getLoaiVe());
        tv_hangGhe.setText("Hạng ghế: " + tuyenModel.getHangGhe());
    }

    public void bookButtonClick()
    {
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent routeToBookingIntent = new Intent(getApplicationContext(), BookingDetail.class);
                routeToBookingIntent.putExtra("tuyenModel", tuyenModel);
                startActivity(routeToBookingIntent);
            }
        });
    }
}