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

public class CTHD_List_Adapter extends ArrayAdapter<CTHDModel> implements Observer {
    private ArrayList<CTHDModel> arrayList; // Danh sách ban đầu
    private ArrayList<CTHDModel> filteredList; // Danh sách đã lọc
    public CTHD_List_Adapter(@NonNull Context context, ArrayList<CTHDModel> arrayList) {
        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
        this.arrayList = new ArrayList<>(arrayList);
        this.filteredList = new ArrayList<>(arrayList);
    }
    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public CTHDModel getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public void update(String searchQuery) {
        filteredList.clear();
        if (searchQuery.isEmpty()) {
            filteredList.addAll(arrayList);
        } else {
            for (CTHDModel item : arrayList) {
                boolean matched = false;

                // Tiềm kiếm theo tên tuyến bay
                if (item.getTenTuyen().toLowerCase().contains(searchQuery.toLowerCase())) {
                    matched = true;
                }
                // Tiềm kiếm theo mã đơn
                String maDon = String.valueOf(item.getMaDon());
                if (!matched && maDon.contains(searchQuery.toLowerCase())) {
                    matched = true;
                }
                // Tiềm kiếm theo tên khách hàng
                if (!matched && item.getTenKH().toLowerCase().contains(searchQuery.toLowerCase())) {
                    matched = true;
                }

                if (matched) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Thông báo cho ListView cập nhật giao diện dựa trên danh sách mới
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
        tv_tenTuyen.setText("Mã đơn: " + currentTuyenXe.getMaDon());

        TextView tv_maDon = currentItemView.findViewById(R.id.tv_maDon);
        String text = "Tuyến: " + currentTuyenXe.getTenTuyen();
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
