package edu.project.TouristTicketOrder.Admin_Activity.TuyenBay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.R;

public class CustomTuyenSpinnerAdapter extends ArrayAdapter<ChangBayModel> {

    public CustomTuyenSpinnerAdapter(Context context, ArrayList<ChangBayModel> arrayList) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_tuyen_spinner_layout, parent, false);
        }
        ChangBayModel currentTuyen = getItem(position);

        TextView tv_tenTuyen = convertView.findViewById(R.id.custom_spinner_tenTuyen);
        tv_tenTuyen.setText(currentTuyen.getTenTuyen());
        return convertView;
    }
}
