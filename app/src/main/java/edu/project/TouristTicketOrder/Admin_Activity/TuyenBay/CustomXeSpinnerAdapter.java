package edu.project.TouristTicketOrder.Admin_Activity.TuyenBay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.R;

public class CustomXeSpinnerAdapter extends ArrayAdapter<MayBayModel> {

    public CustomXeSpinnerAdapter(Context context, ArrayList<MayBayModel> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    private View initializeView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_xe_spinner_layout, parent, false);
        }
        MayBayModel currentMayBay = getItem(position);

        TextView tv_tenMayBay = convertView.findViewById(R.id.custom_spinner_tenXe);
        tv_tenMayBay.setText("Tên máy bay: " + currentMayBay.getTenXe());

        TextView tv_soGhe = convertView.findViewById(R.id.custom_spinner_soGhe);
        tv_soGhe.setText("Số ghế: " + currentMayBay.getSoGhe());
        return convertView;
    }
}
