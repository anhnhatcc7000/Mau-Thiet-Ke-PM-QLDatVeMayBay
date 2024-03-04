package edu.project.TouristTicketOrder.Admin_Activity.NhanVien;

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

import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.R;

public class NhanVien_List_Adapter extends ArrayAdapter<NhanVienModel> {
    TextView tv_empName, tv_empPhone, tv_empMail;

    // invoke the suitable constructor of the ArrayAdapter class
    public NhanVien_List_Adapter(@NonNull Context context, ArrayList<NhanVienModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public NhanVien_List_Adapter(@NonNull TextWatcher textWatcher, ArrayList<NhanVienModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.nhanvien_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        NhanVienModel nhanVienModel = getItem(position);

        // get employee name
        tv_empName = currentItemView.findViewById(R.id.tv_empName);
        tv_empName.setText("Tên nhân viên: " + nhanVienModel.getEmpName());
        // get employee phone
        tv_empPhone = currentItemView.findViewById(R.id.tv_empPhone);
        tv_empPhone.setText("SDT nhân viên: " + nhanVienModel.getEmpPhone());
        // get employee mail
        tv_empMail = currentItemView.findViewById(R.id.tv_empMail);
        tv_empMail.setText("Mail nhân viên: " + nhanVienModel.getEmpMail());

        // then return the recyclable view
        return currentItemView;
    }
}
