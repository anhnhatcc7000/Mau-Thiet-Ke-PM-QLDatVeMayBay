package edu.project.TouristTicketOrder.BookingPage;

import static edu.project.TouristTicketOrder.BookingPage.BookingDetail.seats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

public class SeatOption extends AppCompatActivity {
    int carTotalSeats, seatNumber;
    int[] idArray, floorOneSeatNumber, floorTwoSeatNumber;
    Button btn_continue;
    TuyenBayModel tuyenBayModel;
    RelativeLayout rl_parent, rl_right;
    LinearLayout rl_left;
    RelativeLayout.LayoutParams layoutParams;
    TextView tv_seats;
    ImageView ib_back;
    Drawable greenChair, redChair, yellowChair;
    LinkedList<String> bookedSeatList = new LinkedList<>();
    public static LinkedList<Integer> optionSeatList = new LinkedList<>();
    DataBaseHandler db = new DataBaseHandler(SeatOption.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_option);
        variableAssign();
        tuyenBayModel = (TuyenBayModel) getIntent().getSerializableExtra("tuyenModel");
        carTotalSeats = tuyenBayModel.getSoGhe();
//        carTotalSeats = 24;
        if(seats == null)
        {
            optionSeatList = new LinkedList<>();
        }
        createLayout32();
        changeSeat();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void variableAssign()
    {
        rl_parent = (RelativeLayout) findViewById(R.id.rl_parent);
        rl_left = (LinearLayout) findViewById(R.id.rl_left);
//        rl_right = (RelativeLayout) findViewById(R.id.rl_right);
        greenChair = ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_chair_24);
        redChair = ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_chair_red);
        yellowChair = ContextCompat.getDrawable(getApplicationContext(), R.drawable.baseline_chair_yellow);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        ib_back = (ImageView) findViewById(R.id.ib_back);
        tv_seats = (TextView) findViewById(R.id.tv_seats);
    }
    public void createLayout32()
    {
        seatNumber = 1;
        floorOneSeatNumber = new int[carTotalSeats + 2];
//        floorTwoSeatNumber = new int[carTotalSeats / 2 + 2];
        getBookedSeatList();
//        floorOneChairs();
//        floorTwoChairs();
        createTextViews();
    }

    public void getBookedSeatList()
    {
        Cursor cursor = db.getBookedSeats(tuyenBayModel.getMaTuyenXe());
        try {
            if(cursor.moveToFirst())
            {
                do {
                    String[] bookSeats = cursor.getString(0).split(", ");
                    bookedSeatList.addAll(Arrays.asList(bookSeats));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {}
    }

    private void createTextViews() {
        LinearLayout parentLayout = findViewById(R.id.rl_left);  // Replace with your actual parent layout
        LinearLayout rowLayout = null;
        final int NUM_OF_COLUMNS = 3; // Number of TextViews per row

        idArray = new int[carTotalSeats + 2];

        for (int i = 1; i <= carTotalSeats; i++) {  // Replace 10 with your actual number of TextViews
            floorOneSeatNumber[(int) i] = seatNumber;

            // Create a new row every NUM_OF_COLUMNS items
            if (i % NUM_OF_COLUMNS == 0) {
                rowLayout = new LinearLayout(this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                rowLayout.setLayoutParams(params);

                // Add the row to the parent layout
                parentLayout.addView(rowLayout);
            }

            // Create a new TextView
            TextView textView = createTextView(i, false);

            // Add the TextView to the current row
            if (rowLayout != null) {
                rowLayout.addView(textView);
            }
            idArray[(int) i] = textView.getId();
        }
    }


    public TextView createTextView(double i, boolean isFloorTwo)
    {

        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);

        textView.setId((int) i);
        textView.setPadding(15,15,0,0);
        if(bookedSeatList.contains(String.valueOf(seatNumber).trim()))
        {
            textView.setCompoundDrawablesWithIntrinsicBounds(yellowChair, null, null, null);
        }
        else
        {
            textView.setCompoundDrawablesWithIntrinsicBounds(greenChair, null, null, null);
        }
        if(optionSeatList.contains(seatNumber))
        {
            textView.setCompoundDrawablesWithIntrinsicBounds(redChair, null, null, null);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = textView.getId();

                int currentSeatNumber = floorOneSeatNumber[id];

                seatOnClick(currentSeatNumber, textView);
            }
        });
        seatNumber++;
        return textView;
    }

    public void seatOnClick(int currentSeatNumber, TextView textView)
    {
        if(!bookedSeatList.contains(String.valueOf(currentSeatNumber)))
        {
            if(!optionSeatList.contains(currentSeatNumber))
            {
                optionSeatList.add(currentSeatNumber);
                textView.setCompoundDrawablesWithIntrinsicBounds(redChair, null, null, null);
            }
            else
            {
                optionSeatList.remove(optionSeatList.indexOf(currentSeatNumber));
                textView.setCompoundDrawablesWithIntrinsicBounds(greenChair, null, null, null);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Ghế đã có người đặt trước", Toast.LENGTH_SHORT).show();
        }
        changeSeat();
    }

    public void setLayoutParams(boolean isMoreThanHalf, int position, RelativeLayout.LayoutParams layoutParams, TextView textView)
    {

        if(isMoreThanHalf)
        {
            if(position >= carTotalSeats / 3 + 1) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, position / 2 + 1);
                int i = idArray[(position / 2 + 1) - 1];

                if(position == carTotalSeats / 3 + 1)
                    position = idArray[0];
                else
                    position = idArray[(position / 2 + 1)];
            }
            else {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, position);
                position = idArray[position - 1];
            }
        }
        layoutParams.addRule(RelativeLayout.BELOW, position);
        textView.setLayoutParams(layoutParams);
    }

    public void changeSeat()
    {
        optionSeatList.sort(null);
        if(optionSeatList.size() > 0)
            tv_seats.setText("" + optionSeatList);
        else
            tv_seats.setText("Bạn chưa chọn ghế");
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        ib_back.setOnClickListener(onClickListener);
        btn_continue.setOnClickListener(onClickListener);
    }
}