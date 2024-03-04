package edu.project.TouristTicketOrder.BookingPage;

import static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;
import static edu.project.TouristTicketOrder.BookingPage.SeatOption.optionSeatList;
import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;
import static edu.project.TouristTicketOrder.HomePage.RouteDetail.date;
import static edu.project.TouristTicketOrder.HomePage.RouteDetail.seat;
import static edu.project.TouristTicketOrder.HomePage.RouteDetail.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

public class BookingDetail extends AppCompatActivity {
    ImageButton ib_back;
    RadioGroup rd_luggage;
    TextView tv_day, tv_tenTuyen, tv_time, tv_vehicle_detail, tv_cusName, tv_cusPhone, tv_cusMail, tv_seat, tv_changeSeat, tv_totalPrice;
    Button btn_pay;
    public static int maTuyenXe;
    public static String seats = null;
    public static int totalPrice;
    public int currentPrice, hanhLy = 0;
    MayBayModel mayBayModel;
    TuyenBayModel tuyenBayModel;

    CustomerModel customerModel;
    DataBaseHandler dataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);
        dataBaseHandler = new DataBaseHandler(getApplicationContext());

        Intent intentFromRouteDetail = getIntent();
        tuyenBayModel = (TuyenBayModel) intentFromRouteDetail.getSerializableExtra("tuyenModel");

        assignVariable();
        bookButtonClick();
        luggageSelect();
        ib_back = findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(optionSeatList == null || optionSeatList.size() < 0)
            rd_luggage.clearCheck();
        changeSeat();
    }

    public void changeSeat()
    {
        tv_changeSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingDetail.this, SeatOption.class);
                intent.putExtra("tuyenModel", tuyenBayModel);
                startActivity(intent);
            }
        });
        if(maTuyenXe == tuyenBayModel.getMaTuyenXe())
        {
            if(optionSeatList.size() > 0)
            {
                totalPrice = tuyenBayModel.getGia();
                totalPrice *= optionSeatList.stream().count();
                currentPrice = totalPrice;

                switch (rd_luggage.getCheckedRadioButtonId()) {
                    case R.id.rd_0kg:
                        totalPrice = currentPrice;
                        tv_totalPrice.setText("" + totalPrice + " VND");
                        hanhLy = 0;
                        break;
                    case R.id.rd_10kg:
                        totalPrice = currentPrice + 130000;
                        tv_totalPrice.setText("" + totalPrice + " VND");
                        hanhLy = 10;
                        break;
                    case R.id.rd_20kg:
                        totalPrice = currentPrice + 230000;
                        tv_totalPrice.setText("" + totalPrice + " VND");
                        hanhLy = 20;
                        break;
                }

                tv_totalPrice.setText("" + totalPrice + " VND");
                stringWithoutBraces();
                tv_seat.setText("" + seats);
                btn_pay.setBackgroundColor(Color.parseColor("#039ae7"));
                btn_pay.setEnabled(true);
            }
            else
            {
                tv_totalPrice.setText("0");
                tv_seat.setText("Bạn chưa chọn ghế");
                btn_pay.setBackgroundColor(Color.parseColor("#f2c40d"));
                btn_pay.setEnabled(false);
            }
        } else {
            seats = null;
            maTuyenXe = tuyenBayModel.getMaTuyenXe();
        }
    }

    public void stringWithoutBraces()
    {
        seats = optionSeatList.toString();
        String[] temp = seats.split("\\[");
        temp = temp[1].split("]");
        seats = temp[0];
    }

    public void bookButtonClick()
    {
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                intent.putExtra("tuyenModel", tuyenBayModel);
                intent.putExtra("mayBayModel", mayBayModel);
                intent.putExtra("customerModel", customerModel);
                intent.putExtra("hanhLy", hanhLy);
                startActivity(intent);
            }
        });
    }

    public void luggageSelect() {
        rd_luggage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(optionSeatList != null && optionSeatList.size() > 0) {
                    switch (checkedId) {
                        case R.id.rd_0kg:
                            totalPrice = currentPrice;
                            tv_totalPrice.setText("" + totalPrice + " VND");
                            hanhLy = 0;
                            break;
                        case R.id.rd_10kg:
                            totalPrice = currentPrice + 130000;
                            tv_totalPrice.setText("" + totalPrice + " VND");
                            hanhLy = 10;
                            break;
                        case R.id.rd_20kg:
                            totalPrice = currentPrice + 230000;
                            tv_totalPrice.setText("" + totalPrice + " VND");
                            hanhLy = 20;
                            break;
                    }
                } else {
                    Toast.makeText(BookingDetail.this, "Bạn cần phải chọn ghế trước", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void assignVariable() {
        customerModel = new CustomerModel();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
        customerModel.setId(maKH);
        customerModel.setTenKH(pref.getString("cusName", null));
        customerModel.setMail(pref.getString("cusMail", null));
        customerModel.setSDT(pref.getString("cusPhone", null));

        tv_changeSeat = (TextView) findViewById(R.id.tv_changeSeat);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_tenTuyen = (TextView) findViewById(R.id.tv_tenTuyen);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_vehicle_detail = (TextView) findViewById(R.id.tv_vehicle_detail);
        tv_cusName = (TextView) findViewById(R.id.tv_cusName);
        tv_cusPhone = (TextView) findViewById(R.id.tv_cusPhone);
        tv_cusMail = (TextView) findViewById(R.id.tv_cusMail);
        rd_luggage = findViewById(R.id.rd_luggage);

        tv_day.setText(tuyenBayModel.getNgayKhoihanh());
        tv_tenTuyen.setText(tuyenBayModel.getTenTuyen());
        tv_vehicle_detail.setText(tuyenBayModel.getTenMB());
        tv_cusName.setText(customerModel.getTenKH());
        tv_cusPhone.setText(customerModel.getSDT());
        tv_cusMail.setText(customerModel.getMail());

        tv_seat = (TextView) findViewById(R.id.tv_seat);
        tv_totalPrice = (TextView) findViewById(R.id.tv_totalPrice);
        btn_pay = findViewById(R.id.btn_pay);
        btn_pay.setBackgroundColor(Color.parseColor("#039ae7"));

        currentPrice = tuyenBayModel.getGia();
    }
}