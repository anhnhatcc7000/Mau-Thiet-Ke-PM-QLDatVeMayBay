package edu.project.TouristTicketOrder.Admin_Activity.Voucher;

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

import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.Model.VoucherModel;
import edu.project.TouristTicketOrder.R;

public class VoucherListAdapter extends ArrayAdapter<VoucherModel> {
    TextView maVoucher, moTa, giamGia, ngayBatDau, ngayKetThuc;

    // invoke the suitable constructor of the ArrayAdapter class
    public VoucherListAdapter(@NonNull Context context, ArrayList<VoucherModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public VoucherListAdapter(@NonNull TextWatcher textWatcher, ArrayList<VoucherModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.voucher_list_layout, parent, false);
        }

        VoucherModel currentCustomer = getItem(position);

        maVoucher = currentItemView.findViewById(R.id.tv_maVoucher);
        maVoucher.setText("Mã voucher: " + currentCustomer.getMaVoucher());

        moTa = currentItemView.findViewById(R.id.tv_moTa);
        moTa.setText("Mô tả: " + currentCustomer.getMoTa());

        giamGia = currentItemView.findViewById(R.id.tv_giamGia);
        giamGia.setText("Giảm: " + currentCustomer.getGiamGia());

        ngayBatDau = currentItemView.findViewById(R.id.tv_ngayBatDau);
        ngayBatDau.setText("Ngày bắt đầu: " + currentCustomer.getNgayBatDau());

        ngayKetThuc = currentItemView.findViewById(R.id.tv_ngayKetThuc);
        ngayKetThuc.setText("Ngày kết thúc: " + currentCustomer.getNgayKetThuc());

        // then return the recyclable view
        return currentItemView;
    }
}
