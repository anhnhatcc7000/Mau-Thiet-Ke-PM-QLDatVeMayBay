package edu.project.TouristTicketOrder.Admin_Activity.Tuyen;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.R;

public class Tuyen_List_Adapter extends ArrayAdapter<ChangBayModel> {
    TextView tv_TenTuyen, tv_startAddr, tv_endAddr, tv_xe, tv_gia, tv_ngayKetThuc;

    // invoke the suitable constructor of the ArrayAdapter class
    public Tuyen_List_Adapter(@NonNull Context context, ArrayList<ChangBayModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public Tuyen_List_Adapter(@NonNull TextWatcher textWatcher, ArrayList<ChangBayModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.tuyenxe_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ChangBayModel currentTuyenXe = getItem(position);

        // get TenTuyen
        tv_TenTuyen = currentItemView.findViewById(R.id.tv_TenTuyen);
        tv_TenTuyen.setText(currentTuyenXe.getNoiXuatPhat() + " --> " + currentTuyenXe.getNoiDen());

//
        tv_startAddr = currentItemView.findViewById(R.id.tv_startAddr);
        if(currentTuyenXe.getNoiXuatPhat().length() > 0)
            tv_startAddr.setText("Sân bay đi: "+currentTuyenXe.getNoiXuatPhat());

        tv_endAddr = currentItemView.findViewById(R.id.tv_endAddr);
        if(currentTuyenXe.getNoiDen().length() > 0)
            tv_endAddr.setText("Sân bay đến: "+currentTuyenXe.getNoiDen());

        tv_ngayKetThuc = currentItemView.findViewById(R.id.tv_ngayKetThuc);
        tv_ngayKetThuc.setVisibility(View.GONE);

        tv_gia = currentItemView.findViewById(R.id.tv_gia);
        tv_gia.setVisibility(View.GONE);

        tv_xe = currentItemView.findViewById(R.id.tv_xe);
        tv_xe.setVisibility(View.GONE);

        // then return the recyclable view
        return currentItemView;
    }
}
