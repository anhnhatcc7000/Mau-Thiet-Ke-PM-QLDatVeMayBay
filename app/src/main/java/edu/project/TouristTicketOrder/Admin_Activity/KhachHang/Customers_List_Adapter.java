package edu.project.TouristTicketOrder.Admin_Activity.KhachHang;

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
import edu.project.TouristTicketOrder.R;

public class Customers_List_Adapter extends ArrayAdapter<CustomerModel> {
    TextView cusName, cusID, cusPhone, cusMail, cusPass, cusPassport;

    // invoke the suitable constructor of the ArrayAdapter class
    public Customers_List_Adapter(@NonNull Context context, ArrayList<CustomerModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    public Customers_List_Adapter(@NonNull TextWatcher textWatcher, ArrayList<CustomerModel> arrayList) {
        super((Context) textWatcher,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.customer_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        CustomerModel currentCustomer = getItem(position);

        // get customer name from db to text view cus name
        cusName = currentItemView.findViewById(R.id.tv_cusName);
        cusName.setText("Name: " + currentCustomer.getTenKH());

        // get customer ID from db to text view cus id
        cusID = currentItemView.findViewById(R.id.tv_cusID);
        cusID.setText("ID: " + currentCustomer.getId());

        // get customer mail from db to text view cus mail
        cusMail = currentItemView.findViewById(R.id.tv_cusMail);
        cusMail.setText("Mail: " + currentCustomer.getMail());

        // get customer phone from db to text view cus phone
        cusPhone = currentItemView.findViewById(R.id.tv_cusPhone);
        cusPhone.setText("Phone: " + currentCustomer.getSDT());

        // get customer pass from db to text view cus phone
        cusPass = currentItemView.findViewById(R.id.tv_cusPass);
        cusPass.setText("Password: " + currentCustomer.getPass());

        // get customer passport from db to text view cus phone
        cusPassport = currentItemView.findViewById(R.id.tv_cusPassport);
        cusPassport.setText("Passport: " + currentCustomer.getPassport());

        // then return the recyclable view
        return currentItemView;
    }
}

