package edu.project.TouristTicketOrder.BookingPage;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.widget.Toast;
import  static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;
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
import java.util.LinkedList;

import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
public interface PaymentStrategy {



    void pay(TuyenBayModel tuyenModel, int hanhLy, int totalPrice);


}
