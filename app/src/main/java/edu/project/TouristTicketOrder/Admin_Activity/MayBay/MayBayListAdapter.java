package edu.project.TouristTicketOrder.Admin_Activity.MayBay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.R;

public class MayBayListAdapter extends ArrayAdapter<MayBayModel> {

    TextView tv_tenMayBay, tv_plate, tv_seats;

    // invoke the suitable constructor of the ArrayAdapter class
    public MayBayListAdapter(@NonNull Context context, ArrayList<MayBayModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public MayBayListAdapter(@NonNull TextWatcher textWatcher, ArrayList<MayBayModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_maybay_list_adapter, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        MayBayModel mayBayModel = getItem(position);

        // get employee name
        tv_tenMayBay = currentItemView.findViewById(R.id.tv_tenMayBay);
        tv_tenMayBay.setText("Tên Máy Bay: " + mayBayModel.getTenXe());
        // get employee phone
        tv_plate = currentItemView.findViewById(R.id.tv_plate);
        tv_plate.setText("Mã số mấy bay: " + mayBayModel.getBienSo());

        // get employee mail
        tv_seats = currentItemView.findViewById(R.id.tv_seats);
        tv_seats.setText("Số ghế: " + mayBayModel.getSoGhe());

        // then return the recyclable view
        return currentItemView;
    }
}