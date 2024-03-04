package edu.project.TouristTicketOrder.Admin_Activity.CTHD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.project.TouristTicketOrder.Model.CTHDModel;
import edu.project.TouristTicketOrder.R;

public class CTHD_List_Adapter extends ArrayAdapter<CTHDModel> {
    public CTHD_List_Adapter(@NonNull Context context, ArrayList<CTHDModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_cthd_list, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        CTHDModel currentTuyenXe = getItem(position);
        // get TenTuyen
        TextView tv_tenTuyen = currentItemView.findViewById(R.id.tv_tenTuyen);
        tv_tenTuyen.setText(currentTuyenXe.getTenTuyen());

        TextView tv_maDon = currentItemView.findViewById(R.id.tv_maDon);
        String text = "Mã đơn: " + currentTuyenXe.getMaDon();
        tv_maDon.setText(text);

        TextView tv_tenKH = currentItemView.findViewById(R.id.tv_tenKH);
        text = "Tên khách hàng: " + currentTuyenXe.getTenKH();
        tv_tenKH.setText(text);

        TextView tv_tenMayBay = currentItemView.findViewById(R.id.tv_tenMayBay);
        text = "Tên máy bay: " + currentTuyenXe.getTenXe();
        tv_tenMayBay.setText(text);

        TextView tv_ngayKhoiHanh = currentItemView.findViewById(R.id.tv_ngayKhoiHanh);
        text = "Ngày khởi hành: " + currentTuyenXe.getNgayKhoiHanh();
        tv_ngayKhoiHanh.setText(text);


        TextView tv_ghe = currentItemView.findViewById(R.id.tv_ghe);
        text = "Ghế: " + currentTuyenXe.getGhe();
        tv_ghe.setText(text);

        TextView tv_ngayDat = currentItemView.findViewById(R.id.tv_ngayDat);
        text = "Ngày đặt: " + currentTuyenXe.getNgayDat();
        tv_ngayDat.setText(text);

        TextView thanhTien = currentItemView.findViewById(R.id.thanhTien);
        text = "Thành tiền: " + currentTuyenXe.getThanhTien();
        thanhTien.setText(text);

        TextView tv_trangThai = currentItemView.findViewById(R.id.tv_trangThai);
        text = "Trạng thái: " + currentTuyenXe.getTrangThai();
        tv_trangThai.setText(text);

        return currentItemView;
    }

}
