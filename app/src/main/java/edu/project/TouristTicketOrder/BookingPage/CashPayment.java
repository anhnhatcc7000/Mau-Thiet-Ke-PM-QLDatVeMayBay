package edu.project.TouristTicketOrder.BookingPage;

import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.seats;
import static edu.project.TouristTicketOrder.BookingPage.SeatOption.optionSeatList;
import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
public  class CashPayment implements PaymentStrategy {
         DataBaseHandler dataBaseHandler;
        Context context;

        public CashPayment(Context context) {
                this.context = context;
                this.dataBaseHandler = new DataBaseHandler(context);
        }




    @Override
    public void pay(TuyenBayModel tuyenModel, int hanhLy, int total_Price) {

        // Logic để xử lý thanh toán bằng tiền mặt

        // Lưu thông tin thanh toán vào cơ sở dữ liệu
        dataBaseHandler.addCTHD(tuyenModel, seats, maKH, total_Price, hanhLy);

        // Hiển thị thông báo thành công
        Toast.makeText(context, "Thanh toán bằng tiền mặt thành công. Vui lòng đến quầy để hoàn tất.", Toast.LENGTH_LONG).show();

        // Xử lý sau khi thanh toán thành công, ví dụ: chuyển hướng người dùng về trang chủ
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        // Đặt lại danh sách ghế đã chọn và giỏ hàng (nếu cần)
        seats = null;
        optionSeatList.clear();

    }
}
