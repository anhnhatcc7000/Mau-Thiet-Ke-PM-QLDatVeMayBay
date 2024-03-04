package edu.project.TouristTicketOrder.Admin_Activity.Tuyen;

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
import edu.project.TouristTicketOrder.LocalDateTimeConvert;
import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.R;

public class View_Tuyen_List extends AppCompatActivity {
    Dialog myDialog;
    ListView tuyenXeList;
    String selectedDate, selectedTime;
    EditText edt_searchTenTuyen, startDes, endDes, startAddr, edt_gia,edt_ngayKhoiHanh, edt_ngayKetThuc;
    TextView tenTuyen, endAddr, tv_error;
    Button btn_edit, btn_delete;
    ChangBayModel clickedTuyenXe;
    Tuyen_List_Adapter tuyen_list_adapter;
    LocalDateTimeConvert localDateTimeConvert = new LocalDateTimeConvert();
    DataBaseHandler dataBaseHandler = new DataBaseHandler(View_Tuyen_List.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tuyen_xe_list);
        myDialog = new Dialog(this);
        assignVariable();
        loadListView("");
        searchTour();
        TuyenXeClickListener();
        deleteTuyen();
        editButtonClickListener();
    }

    public void assignVariable()
    {
        tuyenXeList = (ListView) findViewById(R.id.lv_tuyenXeList);

        edt_searchTenTuyen = (EditText) findViewById(R.id.edt_searchTenTuyen);

        myDialog.setContentView(R.layout.tuyenxe_detail_dialog);

        // Set customer detail from List to Dialog
        tenTuyen = (TextView) myDialog.findViewById(R.id.tv_tenTuyen);
        startDes = (EditText) myDialog.findViewById(R.id.edt_startDes);
        endDes = (EditText) myDialog.findViewById(R.id.edt_endDes);
//        startAddr = (EditText) myDialog.findViewById(R.id.edt_startAddr);
//        endAddr = (EditText) myDialog.findViewById(R.id.edt_endAddr);
//        edt_gia = myDialog.findViewById(R.id.edt_gia);
        edt_ngayKhoiHanh = (EditText) myDialog.findViewById(R.id.edt_ngayKhoiHanh);
//        edt_ngayKetThuc = (EditText) myDialog.findViewById(R.id.edt_ngayKetThuc);
        btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
        btn_delete = (Button) myDialog.findViewById(R.id.btn_delete);
    }

    public void searchTour()
    {
        // Search tuyen xe
        edt_searchTenTuyen.addTextChangedListener(new TextWatcher() {
            String tenTuyen;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tenTuyen = edt_searchTenTuyen.getText().toString();
                loadListView(tenTuyen);
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public void TuyenXeClickListener()
    {
        // Select a customer from ListView
        tuyenXeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                clickedTuyenXe = (ChangBayModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected "+ clickedTuyenXe.getTenTuyen(),Toast.LENGTH_SHORT).show();
                // Show a dialog of selected customer detail
                myDialog.show();
                tv_error = myDialog.findViewById(R.id.tv_error);
                tv_error.setVisibility(View.GONE);
                String tv_tenTuyen = "Tên tuyến: " + clickedTuyenXe.getTenTuyen();

                tenTuyen.setText(tv_tenTuyen);
                startDes.setText(clickedTuyenXe.getNoiXuatPhat());
                endDes.setText(clickedTuyenXe.getNoiDen());
//                startAddr.setText(clickedTuyenXe.getDiemDon());
//                endAddr.setText(clickedTuyenXe.getDiemDung());
//                edt_gia.setText(String.valueOf(clickedTuyenXe.getGia()));
            }
        });
    }

    public void deleteTuyen()
    {
        // Delete a customer when hit btn_delete
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isDeleted = dataBaseHandler.deleteTuyen(clickedTuyenXe);
                if(isDeleted == true)
                {
                    // Close Dialog
                    myDialog.dismiss();

                    /* Reload the List when a customer was deleted */
                    loadListView("");
                } else {
                    tv_error.setVisibility(View.VISIBLE);
                }
            }

        });
    }
    public void editButtonClickListener()
    {
        // Update a tuyen xe detail
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateStartDes = startDes.getText().toString();
                String updateEndDes = endDes.getText().toString();

                Boolean isUpdated = dataBaseHandler.updateTuyen(clickedTuyenXe, updateStartDes, updateEndDes);

                if(isUpdated == true) {
//                    finish();
//                    overridePendingTransition(0,0);
//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);
                    myDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                    loadListView("");
                }
                else
                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadListView(String tenTuyen)
    {
        tuyen_list_adapter = new Tuyen_List_Adapter(this, dataBaseHandler.getTuyenOption(tenTuyen));
        // set the Customers_List_Adapter for ListView
        tuyenXeList.setAdapter(tuyen_list_adapter);
    }
    public void goBack(View view)
    {
        finish();
    }
}