package edu.project.TouristTicketOrder.Admin_Activity.CTHD;

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

public class CTHD_Custom_Spinners extends ArrayAdapter<NhanVienModel> {
    TextView tv_id, tv_detail;

    // invoke the suitable constructor of the ArrayAdapter class
    public CTHD_Custom_Spinners(@NonNull Context context, ArrayList arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public CTHD_Custom_Spinners(@NonNull TextWatcher textWatcher, ArrayList arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        NhanVienModel nhanVienModel = getItem(position);

        // get employee name

        // then return the recyclable view
        return currentItemView;
    }
}
