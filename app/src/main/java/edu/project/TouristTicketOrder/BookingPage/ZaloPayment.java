package edu.project.TouristTicketOrder.BookingPage;

import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.seats;
import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.totalPrice;
import static edu.project.TouristTicketOrder.BookingPage.SeatOption.optionSeatList;
import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.LinkedList;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.Model.CreateOrder;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;
import edu.project.TouristTicketOrder.BookingPage.CheckoutPage;


public  class ZaloPayment extends Activity implements PaymentStrategy {
     Context context;
     DataBaseHandler dataBaseHandler;

    public ZaloPayment(Context context) {


        this.context = context;
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public void pay(TuyenBayModel tuyenModel, int hanhLy, int total_Price) {
        dataBaseHandler.addCTHD(tuyenModel, seats, maKH, total_Price, hanhLy);
        requestZalo(total_Price);
        // Hiển thị thông báo thành công
        Toast.makeText(context, "Thanh toán bằng zalo thành công.", Toast.LENGTH_LONG).show();

        // Xử lý sau khi thanh toán thành công, ví dụ: chuyển hướng người dùng về trang chủ
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        // Đặt lại danh sách ghế đã chọn và giỏ hàng (nếu cần)
        seats = null;
        optionSeatList.clear();

    }

    public  void  requestZalo(int total_Price){

        CreateOrder orderApi = new CreateOrder();

        try {
            JSONObject data = orderApi.createOrder(String.valueOf(total_Price));

            String code = data.getString("return_code");
            Log.d( "test: ",code);

            if (code.equals("1")) {

                String token = data.getString("zp_trans_token");



                ZaloPaySDK.getInstance().payOrder(this,token,"token,demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {

                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {

                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
