package edu.project.TouristTicketOrder.BookingPage;

import static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;
import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.seats;
import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.totalPrice;
import static edu.project.TouristTicketOrder.BookingPage.SeatOption.optionSeatList;
import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

public class CheckoutPage extends AppCompatActivity {
    TuyenBayModel tuyenModel;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(CheckoutPage.this);
    int hanhLy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        Intent intentFromRouteDetail = getIntent();
        tuyenModel = (TuyenBayModel) intentFromRouteDetail.getSerializableExtra("tuyenModel");
        hanhLy = intentFromRouteDetail.getIntExtra("hanhLy", 0);

        TextView tv_totalPrice = findViewById(R.id.tv_totalPrice);
        tv_totalPrice.setText("" + totalPrice);
    }

    public void cashPayment(View view)
    {
        dataBaseHandler.addCTHD(tuyenModel, seats, maKH, totalPrice, hanhLy);
        SendMail(tuyenModel);
        Toast.makeText(getApplicationContext(), "Đã lưu thông tin thanh toán", Toast.LENGTH_SHORT).show();
        seats = null;
        optionSeatList = new LinkedList<>();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void SendMail(TuyenBayModel tuyenModel)
    {
        String email = "thefivetransport@gmail.com"; // Mail người gửi
        String pass = "ovnsyqgpfviikcrv"; // Mật khẩu ứng dụng mail gửi

        SharedPreferences pref = getApplicationContext().getSharedPreferences(cus, MODE_PRIVATE);
        String receiver = pref.getString("cusMail", null);
        String tenKh = pref.getString("cusName", null);
        String sdt = pref.getString("cusPhone", null);

        String stringHost = "smtp.gmail.com";
        Properties prop = System.getProperties();

        prop.put("mail.smtp.host", stringHost);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            message.setSubject("Hoá đơn thanh toán sau");
            String htmlBody = "<html><body style=\"font-family: Arial, sans-serif; font-size: 15px; line-height: 1.6; color: #333; margin: 0 auto; padding: 20px;\">" +
                    "  <div style=\"background-color: #f7f7f7; border-radius: 5px; padding: 20px;\">" +
                    "    <h2 style=\"text-align: center; font-size: 24px; color: #333; margin-bottom: 20px;\">Hoá Đơn Tạm Thời</h2>" +
                    "    <hr style=\"border: 1px solid #e4e4e4;\">" +
                    "    <table style=\"width: 100%; margin: 20px 0;\">" +
                    "      <tr>" +
                    "        <td>" +
                    "          <strong>Từ:</strong><br>" +
                    "          Touristo<br>" +
                    "          828 Đ. Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh<br>" +
                    "          Việt Nam" +
                    "        </td>" +
                    "        <td style=\"text-align: right;\">" +
                    "          <strong>Tới:</strong><br>" +
                    "          " + tenKh + "<br>" +
                    "          " + sdt + "<br>" +
                    "        </td>" +
                    "      </tr>" +
                    "    </table>" +
                    "    <table style=\"width: 100%; border-collapse: collapse;\">" +
                    "      <thead>" +
                    "        <tr>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: left; padding: 10px;\">Tuyến Bay</th>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: center; padding: 10px;\">Ngày Bay</th>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: center; padding: 10px;\">Hạng Ghế</th>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: center; padding: 10px;\">Ghế</th>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: center; padding: 10px;\">Loại Vé</th>" +
                    "          <th style=\"background-color: #f0f0f0; text-align: right; padding: 10px;\">Hành Lý Kí Gửi</th>" +
                    "        </tr>" +
                    "      </thead>" +
                    "      <tbody>" +
                    "        <tr>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; padding: 10px;\">" + tuyenModel.getTenTuyen() + "</td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: center; padding: 10px;\">" + tuyenModel.getNgayKhoihanh() + "</td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: center; padding: 10px;\">" + tuyenModel.getHangGhe() + "</td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: center; padding: 10px;\">" + seats + "</td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: center; padding: 10px;\">" + tuyenModel.getLoaiVe() + "</td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: right; padding: 10px;\">" + hanhLy + "kg" + "</td>" +
                    "        </tr>" +
                    "        <!-- Add more rows as needed -->" +
                    "      </tbody>" +
                    "      <tfoot>" +
                    "        <tr>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4;\"></td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4;\"></td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4;\"></td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4;\"></td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; padding: 10px; text-align: right;\"><strong>Tổng:</strong></td>" +
                    "          <td style=\"border-top: 1px solid #e4e4e4; text-align: right; padding: 10px;\"><strong>" + totalPrice + " vnđ</strong></td>" +
                    "        </tr>" +
                    "      </tfoot>" +
                    "    </table>" +
                    "    <p style=\"margin-bottom: 20px;\">Cảm ơn quý khách đã đặt vé! Xin hãy thanh toán tài quầy trước 3 tiếng để được nhận vé.</p>" +
                    "    <p style=\"text-align: center; font-size: 12px; color: #777;\">This is an automatically generated invoice. Please do not reply to this email.</p>" +
                    "  </div>" +
                    "</body></html>";
            message.setContent(htmlBody,"text/html; charset=utf-8");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}