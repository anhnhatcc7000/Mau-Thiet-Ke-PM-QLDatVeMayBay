package edu.project.TouristTicketOrder.Admin_Activity.TuyenBay;

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

import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

public class TuyenBayListAdapter extends ArrayAdapter<TuyenBayModel> {
    TextView tv_TenTuyen, tv_ngayKhoiHanh, tv_ngayKetThuc, tv_maybay, tv_gia, tv_startAddr, tv_endAddr, tv_loaiVe, tv_hangGhe;

    // invoke the suitable constructor of the ArrayAdapter class
    public TuyenBayListAdapter(@NonNull Context context, ArrayList<TuyenBayModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public TuyenBayListAdapter(@NonNull TextWatcher textWatcher, ArrayList<TuyenBayModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.tuyenbay_list, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        TuyenBayModel currentTuyenXe = getItem(position);
        // get TenTuyen
        String[] temp = currentTuyenXe.getTenTuyen().split("-");
        String tenTuyen = temp[0] + " --> " + temp[1];
        tv_TenTuyen = currentItemView.findViewById(R.id.tv_TenTuyen);
        tv_TenTuyen.setText(tenTuyen);

        tv_maybay = currentItemView.findViewById(R.id.tv_maybay);
        tv_maybay.setText("Máy bay: " + currentTuyenXe.getTenMB());

        tv_ngayKhoiHanh = currentItemView.findViewById(R.id.tv_ngayKhoiHanh);
        tv_ngayKhoiHanh.setText("Ngày bay: " + currentTuyenXe.getNgayKhoihanh());

        tv_gia = currentItemView.findViewById(R.id.tv_gia);
        tv_gia.setText(currentTuyenXe.getGia() + "/khách");

        tv_loaiVe = currentItemView.findViewById(R.id.tv_loaive);
        tv_loaiVe.setText("Loại vé: " + currentTuyenXe.getLoaiVe());
//        tv_startAddr = currentItemView.findViewById(R.id.tv_startAddr);
//        tv_startAddr.setText("Hạng ghế: " + currentTuyenXe.getHangGhe());

        tv_hangGhe = currentItemView.findViewById(R.id.tv_hangGhe);
        tv_hangGhe.setText("Hạng ghế: " + currentTuyenXe.getHangGhe());

        return currentItemView;
    }
}

