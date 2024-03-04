package edu.project.TouristTicketOrder.Account_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

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
import edu.project.TouristTicketOrder.R;

public class ForgetPassActivity extends AppCompatActivity {
    EditText edt_mail, edt_code;
    int randomNumber;
    TextView tv_message;
    Button btn_fgtPass;
    Dialog myDialog;
    DataBaseHandler db = new DataBaseHandler(ForgetPassActivity.this);
    boolean isSent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        myDialog = new Dialog(this);
        btn_fgtPass = (Button) findViewById(R.id.btn_fgtPass);
        edt_code = (EditText) findViewById(R.id.edt_code);
        edt_mail = (EditText) findViewById(R.id.edt_mail);
        tv_message = (TextView) findViewById(R.id.tv_message);

        edt_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String mail = edt_mail.getText().toString();
                if(CheckInput(mail))
                {
                    btn_fgtPass.setBackgroundColor(Color.parseColor("#f2c40d"));
                    btn_fgtPass.setEnabled(true);
                } else {
                    btn_fgtPass.setBackgroundColor(Color.parseColor("#B2A496"));
                    btn_fgtPass.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        btn_fgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiver = edt_mail.getText().toString();
                boolean isExistCus = db.CustomerMailValidate(receiver);
                if(isExistCus)
                {
                    randomNumber = ( int )( Math.random() * 9999 );

                    if( randomNumber <= 1000 )
                        randomNumber = randomNumber + 1000;

                    SendMail(receiver, randomNumber);
                    myDialog.setContentView(R.layout.reset_pass_dialog);
                    myDialog.show();

                    EditText edt_code = (EditText) myDialog.findViewById(R.id.edt_code);
                    TextView tv_message = (TextView) myDialog.findViewById(R.id.tv_message);

                    Button btn_resetPass = (Button) myDialog.findViewById(R.id.btn_resetPass);
                    btn_resetPass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(edt_code.getText().toString().equals(String.valueOf(randomNumber)))
                            {
                                Intent resetPass = new Intent(getApplicationContext(), ResetPassword.class);
                                resetPass.putExtra("mail",receiver);
                                myDialog.dismiss();
                                startActivity(resetPass);
                            } else {
                                tv_message.setText("Sai code");
                            }
                        }
                    });

                    ImageButton ib_back = myDialog.findViewById(R.id.ib_back);
                    ib_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.dismiss();
                        }
                    });
                } else {
                    tv_message.setText("Tài khoản có mail này không tồn tại");
                    tv_message.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void CodeIndentifier()
    {
        edt_mail.setFocusable(false);
        edt_code.setVisibility(View.VISIBLE);
    }

    public boolean CheckInput(String mail) {
        if(mail.length() > 0)
        {
            String[] split = mail.split("");
            for (String check: split)
            {
                if(check.equals("@"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void SendMail(String receiver, int randomNumber)
    {
        String email = "thefivetransport@gmail.com"; // Mail người gửi
        String pass = "ovnsyqgpfviikcrv"; // Mật khẩu ứng dụng mail gửi

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

            message.setSubject("Reset Your Password On Touristo");
            String htmlBody = "<html><body>"
                    + "<h3 style='color:red;'>Seem like you forgot your password</h3>"
                    + "<p>Use this code to reset your password: <span style='color:red'>" + randomNumber + "</span>.</p>"
                    + "</body></html>";
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