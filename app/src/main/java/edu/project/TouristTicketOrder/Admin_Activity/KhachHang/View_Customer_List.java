package edu.project.TouristTicketOrder.Admin_Activity.KhachHang;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collection;
import java.util.Locale;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CustomerModel;
import edu.project.TouristTicketOrder.R;

public class View_Customer_List extends AppCompatActivity {
    Dialog myDialog;
    ListView customerList;

    EditText edt_searchName;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(View_Customer_List.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_list);
        myDialog = new Dialog(this);
        customerList = (ListView) findViewById(R.id.lv_customerList);
        loadData("");

        // Search customer name
        edt_searchName = (EditText) findViewById(R.id.edt_searchName);
        edt_searchName.addTextChangedListener(new TextWatcher() {
            String name;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = edt_searchName.getText().toString();
                loadData(name);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        // Select a customer from ListView
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected "+ clickedCustomer.getTenKH(),Toast.LENGTH_SHORT).show();

                // Show a dialog of selected customer detail
                myDialog.setContentView(R.layout.customer_detail_dialog);
                myDialog.show();

                // Set customer detail from List to Dialog
                EditText cusName = (EditText) myDialog.findViewById(R.id.tv_cusName);
                TextView cusID = (TextView) myDialog.findViewById(R.id.tv_cusID);
                EditText cusPhone = (EditText) myDialog.findViewById(R.id.tv_cusPhone);
                EditText cusMail = (EditText) myDialog.findViewById(R.id.tv_cusMail);
                TextView cusPass = (TextView) myDialog.findViewById(R.id.tv_cusPass);
                EditText cusPassport = (EditText) myDialog.findViewById(R.id.tv_cusPassport);


                cusName.setText("" + clickedCustomer.getTenKH());
                cusID.setText("Customer ID: " + clickedCustomer.getId());
                cusPhone.setText("" + clickedCustomer.getSDT());
                cusMail.setText("" + clickedCustomer.getMail());
                cusPass.setText("Password: " + clickedCustomer.getPass());
                cusPassport.setText("" + clickedCustomer.getPassport());

                // Update a customer detail
                Button btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText getUpdateName = (EditText) myDialog.findViewById(R.id.tv_cusName);
                        EditText getUpdatePhone = (EditText) myDialog.findViewById(R.id.tv_cusPhone);
                        EditText getUpdateMail = (EditText) myDialog.findViewById(R.id.tv_cusMail);
                        EditText getUpdatePassport = (EditText) myDialog.findViewById(R.id.tv_cusPassport);

                        String updateName = getUpdateName.getText().toString();
                        String updatePhone = getUpdatePhone.getText().toString();
                        String updateMail = getUpdateMail.getText().toString();
                        String updatePassport = getUpdatePassport.getText().toString();

                        boolean isUpdated = false;
                        if(updateName.length() > 0 && (updatePhone.length() == 11 || updatePhone.length() == 10) && ValidateMail(updateMail))
                        {
                            if(updateName.equals(clickedCustomer.getTenKH()))
                                updateName = "";
                            if(updatePhone.equals(clickedCustomer.getSDT()))
                                updatePhone = "";
                            if(updateMail.equals(clickedCustomer.getMail()))
                                updateMail = "";
                            isUpdated = dataBaseHandler.updateData(clickedCustomer, updateName, updatePhone, updateMail, "", updatePassport);
                        }

                        if(isUpdated) {
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(getIntent());
                            overridePendingTransition(0,0);

                            Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                });

                // Delete a customer when hit btn_delete
                Button btn_delete = (Button) myDialog.findViewById(R.id.btn_delete);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean isDeleted = dataBaseHandler.deleteData(clickedCustomer);

                        // Close Dialog
                        myDialog.dismiss();

                        /* Reload the List when a customer was deleted */
                        finish();
                        overridePendingTransition(0,0);
                        startActivity(getIntent());
                        overridePendingTransition(0,0);
                    }
                });
            }
        });
    }

    public boolean ValidateMail(String mail) {
        if(mail.length() > 0) {
            String[] split = mail.split("");

            boolean isMail = false;
            for (String check: split) {
                if(check.equals("@")) {
                    isMail = true;
                    break;
                }
            }

            if(isMail)
                return true;
        }
        return false;
    }
    public void loadData(String name)
    {
        Customers_List_Adapter searched_customer = new Customers_List_Adapter(View_Customer_List.this, dataBaseHandler.get_Customer(name));
        customerList.setAdapter(searched_customer);
    }

    public void goBack(View view)
    {
        finish();
    }
}