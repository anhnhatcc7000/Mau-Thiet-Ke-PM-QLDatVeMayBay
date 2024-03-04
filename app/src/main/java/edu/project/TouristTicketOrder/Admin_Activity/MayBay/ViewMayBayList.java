package edu.project.TouristTicketOrder.Admin_Activity.MayBay;

import androidx.appcompat.app.AppCompatActivity;

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

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.R;

public class ViewMayBayList extends AppCompatActivity {

    Dialog myDialog;
    EditText edt_searchMayBay;
    ListView lv_maybayList;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(ViewMayBayList.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_maybay_list);
        myDialog = new Dialog(this);
        lv_maybayList = (ListView) findViewById(R.id.lv_maybayList);
        loadData("");

        // Search customer name
        edt_searchMayBay = (EditText) findViewById(R.id.edt_searchMayBay);
        edt_searchMayBay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tenNV = edt_searchMayBay.getText().toString();
                loadData(tenNV);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Select a customer from ListView
        lv_maybayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                MayBayModel clickedMayBay = (MayBayModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected "+ clickedMayBay.getTenXe(),Toast.LENGTH_SHORT).show();

                // Show a dialog of selected customer detail
                myDialog.setContentView(R.layout.maybay_detail_dialog);
                myDialog.show();

                // Set customer detail from List to Dialog
                TextView tv_tenMayBay = (TextView) myDialog.findViewById(R.id.tv_tenMayBay);
                EditText edt_tenMayBay = (EditText) myDialog.findViewById(R.id.edt_tenMayBay);
                EditText edt_plate = (EditText) myDialog.findViewById(R.id.edt_plate);
                EditText edt_seats = (EditText) myDialog.findViewById(R.id.edt_seats);
                tv_tenMayBay.setText(clickedMayBay.getTenXe() + " " + clickedMayBay.getBienSo());
                edt_tenMayBay.setText(clickedMayBay.getTenXe());
                edt_plate.setText(clickedMayBay.getBienSo());
                edt_seats.setText(String.valueOf(clickedMayBay.getSoGhe()));

                // Update a nhan vien detail
                Button btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tenmaybay = edt_tenMayBay.getText().toString();
                        String plate = edt_plate.getText().toString();
                        String seats = edt_seats.getText().toString();

                        Boolean isUpdated = dataBaseHandler.updateMayBay(clickedMayBay, tenmaybay, plate, seats);
//
//                        boolean isUpdated = false;
//                        if(tenmaybay.length() > 0 && plate.length() > 0 && seats.length() > 0)
//                        {
//                            if(tenmaybay.equals(clickedMayBay.getTenMayBay()))
//                                tenmaybay = "";
//                            if(plate.equals(clickedMayBay.getmasomb()))
//                                plate = "";
//                            if(seats.equals(clickedMayBay.getSoGhe()))
//                                seats = "";
//                            isUpdated = dataBaseHandler.updateMayBay(clickedMayBay, tenmaybay, plate, seats);
//                        }

                        if(isUpdated == true) {
//                            finish();
//                            overridePendingTransition(0,0);
//                            startActivity(getIntent());
//                            overridePendingTransition(0,0);

                            myDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                            loadData("");
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
                        Boolean isDeleted = dataBaseHandler.deleteMayBay(clickedMayBay);

                        if(isDeleted)
                        {
                            // Close Dialog
                            myDialog.dismiss();
//                            myDialog.dismiss();
                            /* Reload the List when a customer was deleted */
                            loadData("");
                        } else {
                            TextView tv_error = myDialog.findViewById(R.id.tv_error);
                            tv_error.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });
    }

    public void loadData(String tenMayBay)
    {
        MayBayListAdapter mayBayListAdapter = new MayBayListAdapter(ViewMayBayList.this, dataBaseHandler.getXe(tenMayBay));
        lv_maybayList.setAdapter(mayBayListAdapter);
    }
    public void goBack(View view)
    {
        finish();
    }
}