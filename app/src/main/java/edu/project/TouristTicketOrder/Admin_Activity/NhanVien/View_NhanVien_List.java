package edu.project.TouristTicketOrder.Admin_Activity.NhanVien;

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
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.R;

public class View_NhanVien_List extends AppCompatActivity {

    Dialog myDialog;
    ListView nhanVienList;
    EditText edt_searchNV;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(View_NhanVien_List.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voew_nhan_vien_list);
        myDialog = new Dialog(this);
        nhanVienList = (ListView) findViewById(R.id.lv_nhanVienList);
        loadData("");

        // Search customer name
        edt_searchNV = (EditText) findViewById(R.id.edt_searchNV);
        edt_searchNV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tenNV = edt_searchNV.getText().toString();
                loadData(tenNV);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Select a customer from ListView
        nhanVienList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                NhanVienModel clickedNV = (NhanVienModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected "+ clickedNV.getEmpName(),Toast.LENGTH_SHORT).show();

                // Show a dialog of selected customer detail
                myDialog.setContentView(R.layout.nhanvien_detail_dialog);
                myDialog.show();

                // Set customer detail from List to Dialog
                TextView empName = (TextView) myDialog.findViewById(R.id.tv_empName);
                TextView empRole = (TextView) myDialog.findViewById(R.id.tv_empRole);
                EditText empCCCD = (EditText) myDialog.findViewById(R.id.edt_empCCCD);
                EditText empAddr = (EditText) myDialog.findViewById(R.id.edt_empAddr);
                EditText empPhone = (EditText) myDialog.findViewById(R.id.edt_empPhone);
                EditText empMail = (EditText) myDialog.findViewById(R.id.edt_empMail);

                empName.setText(clickedNV.getEmpName());
                empRole.setText(clickedNV.getEmpRole());
                empCCCD.setText(clickedNV.getEmpCCCD());
                empAddr.setText(clickedNV.getEmpAddr());
                empPhone.setText(clickedNV.getEmpPhone());
                empMail.setText(clickedNV.getEmpMail());

                // Update a nhan vien detail
                Button btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String updateEmpCCCD = empCCCD.getText().toString();
                        String updateEmpAddr = empAddr.getText().toString();
                        String updateEmpPhone = empPhone.getText().toString();
                        String updateEmpMail = empMail.getText().toString();

                        Boolean isUpdated = dataBaseHandler.updateNV(clickedNV, updateEmpCCCD, updateEmpAddr, updateEmpPhone, updateEmpMail);

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
                        Boolean isDeleted = dataBaseHandler.deleteNV(clickedNV);

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

    public void loadData(String tenNV)
    {
        NhanVien_List_Adapter searched_NV = new NhanVien_List_Adapter(View_NhanVien_List.this, dataBaseHandler.get_NV(tenNV));
        nhanVienList.setAdapter(searched_NV);
    }
    public void goBack(View view)
    {
        finish();
    }
}