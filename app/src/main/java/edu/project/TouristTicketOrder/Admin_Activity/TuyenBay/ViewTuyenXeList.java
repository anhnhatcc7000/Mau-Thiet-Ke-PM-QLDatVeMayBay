package edu.project.TouristTicketOrder.Admin_Activity.TuyenBay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.LocalDateTimeConvert;
import edu.project.TouristTicketOrder.Model.ChangBayModel;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.Model.MayBayModel;
import edu.project.TouristTicketOrder.R;

public class ViewTuyenXeList extends AppCompatActivity {

    Dialog myDialog, addDialog;
    ListView tuyenXeList;
    String selectedDate, selectedTime;
    EditText edt_searchTenTuyen, edt_ngayKhoiHanh, edt_gia;
    NhanVienModel nhanVienModel;
    Spinner spn_hangGhe, spn_loaiVe, spinnerPC;
    TextView tenTuyen, tv_ghe, tv_error;
    Button btn_edit, btn_delete;
    TuyenBayModel clickedTuyenXe;
    MayBayModel selectedXe = null;
    NhanVienModel selectNV = null;
    ChangBayModel selectedTuyen = null;
    TuyenBayListAdapter tuyenBayListAdapter;
    LocalDateTimeConvert localDateTimeConvert = new LocalDateTimeConvert();
    DataBaseHandler dataBaseHandler = new DataBaseHandler(ViewTuyenXeList.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tuyen_xe_list2);
        myDialog = new Dialog(this);
        assignVariable();
        loadListView();
        searchTour();
        TuyenXeClickListener();
        dateTimeChange();
        deleteTuyenXe();
        editButtonClickListener();
    }

    public void assignVariable()
    {
        tuyenXeList = (ListView) findViewById(R.id.lv_tuyenXeList);

        edt_searchTenTuyen = (EditText) findViewById(R.id.edt_searchTenTuyen);

        myDialog.setContentView(R.layout.tuyenxe_detail_dialog2);

        spn_loaiVe = (Spinner) myDialog.findViewById(R.id.spn_loaiVe);
        List<String> danhSachLoaiVe = new ArrayList<>();
        danhSachLoaiVe.add("Một chiều");
        danhSachLoaiVe.add("Khứ hồi");
        ArrayAdapter<String> loaiVeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachLoaiVe);
        spn_loaiVe.setAdapter(loaiVeAdapter);

        spn_hangGhe = (Spinner) myDialog.findViewById(R.id.spn_hangGhe);
        List<String> danhSachHangGhe = new ArrayList<>();
        danhSachHangGhe.add("Thường");
        danhSachHangGhe.add("Thương gia");
        ArrayAdapter<String> hangGheAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhSachHangGhe);
        spn_hangGhe.setAdapter(hangGheAdapter);

        tenTuyen = (TextView) myDialog.findViewById(R.id.tv_tenTuyen);
        tv_ghe = myDialog.findViewById(R.id.tv_ghe);
        edt_ngayKhoiHanh = (EditText) myDialog.findViewById(R.id.edt_ngayKhoiHanh);
//        edt_ngayKetThuc = (EditText) myDialog.findViewById(R.id.edt_ngayKetThuc);
        edt_gia = myDialog.findViewById(R.id.edt_giave);
        btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
        btn_delete = (Button) myDialog.findViewById(R.id.btn_delete);
        getPhiCong();

    }
    public void getPhiCong(){
        spinnerPC = myDialog.findViewById(R.id.spn_nhanVien);
        CustomNhanVienSpinnerAdapter customNhanVienSpinnerAdapter = new CustomNhanVienSpinnerAdapter(this, dataBaseHandler.get_NV(""));
        spinnerPC.setAdapter(customNhanVienSpinnerAdapter);

        // Set an OnItemSelectedListener on the spinner
        spinnerPC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the spinner
                selectNV = (NhanVienModel) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is called when the selection disappears from the spinner (e.g., due to the adapter being empty)
            }
        });
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

                TuyenBayListAdapter searched_TuyenXe = new TuyenBayListAdapter(getApplicationContext(), dataBaseHandler.getTuyenXeOption(tenTuyen, "", false));
                tuyenXeList.setAdapter(searched_TuyenXe);
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
                clickedTuyenXe = (TuyenBayModel) parent.getItemAtPosition(position);
                Cursor cursor = clickedTuyenXe.getTuyenXeModelCursor();
                Toast.makeText(getApplicationContext(), "Selected "+ clickedTuyenXe.getTenTuyen(),Toast.LENGTH_SHORT).show();
                // Show a dialog of selected customer detail
                myDialog.show();
                tv_error = myDialog.findViewById(R.id.tv_error);
                tv_error.setVisibility(View.GONE);
                String tv_tenTuyen = "Tên tuyến: " + clickedTuyenXe.getTenTuyen();

                tenTuyen.setText(tv_tenTuyen);
                edt_gia.setText(clickedTuyenXe.getGia()+"");
                tv_ghe.setText("Máy bay " + clickedTuyenXe.getSoGhe() + " chỗ");
                edt_ngayKhoiHanh.setText(clickedTuyenXe.getNgayKhoihanh());

                ArrayAdapter<String> hangGheAdapter = (ArrayAdapter<String>) spn_hangGhe.getAdapter();
                String hangGhe = clickedTuyenXe.getHangGhe();
                int hangGheAdapterPosition = hangGheAdapter.getPosition(hangGhe);
                if (hangGheAdapterPosition != -1) {
                    spn_hangGhe.setSelection(hangGheAdapterPosition);
                }

                ArrayAdapter<String> loaiVeAdapter = (ArrayAdapter<String>) spn_loaiVe.getAdapter();
                String loaiVeModel = clickedTuyenXe.getLoaiVe();
                int loaiVeAdapterPosition = loaiVeAdapter.getPosition(loaiVeModel);
                if (loaiVeAdapterPosition != -1) {
                    spn_hangGhe.setSelection(loaiVeAdapterPosition);
                }

                ArrayAdapter<String> nhanVienAdapter = (ArrayAdapter<String>) spinnerPC.getAdapter();
                if(selectNV != null){
                    String nhanVien = selectNV.getEmpName();
                    int nhanVienAdapterPosition = nhanVienAdapter.getPosition(nhanVien);
                    if (nhanVienAdapterPosition != -1) {
                        spinnerPC.setSelection(nhanVienAdapterPosition);
                    }
                }
            }
        });
    }

    public void deleteTuyenXe()
    {
        // Delete a customer when hit btn_delete
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isDeleted = dataBaseHandler.deleteTuyenXe(clickedTuyenXe);
                if(isDeleted == true)
                {
                    // Close Dialog
                    myDialog.dismiss();
                    /* Reload the List when a customer was deleted */
                    loadListView();
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
                String updateNgayKhoiHanh = edt_ngayKhoiHanh.getText().toString();
                String hangGhe = spn_hangGhe.getSelectedItem().toString();
                String loaiVe = spn_loaiVe.getSelectedItem().toString();
                String nhanvien = spinnerPC.getSelectedItem().toString();
                Integer gia = Integer.valueOf(edt_gia.getText().toString());
                Boolean isUpdated;
                    isUpdated = dataBaseHandler.updateTuyenXe(clickedTuyenXe, updateNgayKhoiHanh, hangGhe, loaiVe, gia, nhanvien);
                if(isUpdated == true) {
//                    finish();
//                    overridePendingTransition(0,0);
//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);
                    myDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                    loadListView();
                }
                else
                    Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
//        loadListView();
    }

    public void loadListView()
    {
        tuyenBayListAdapter = new TuyenBayListAdapter(this, dataBaseHandler.getTuyenXeOption("", "", false));
        // set the Customers_List_Adapter for ListView
        tuyenXeList.setAdapter(tuyenBayListAdapter);
    }

    public void dateTimeChange()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = "";
                selectedTime = "";
                switch (v.getId())
                {
                    case R.id.edt_ngayKhoiHanh:
                        showDatePickerDialog(R.id.edt_ngayKhoiHanh);
                        break;
//                    case R.id.edt_ngayKetThuc:
//                        showDatePickerDialog(R.id.edt_ngayKetThuc);
//                        break;
                }
            }
        };
        edt_ngayKhoiHanh.setOnClickListener(onClickListener);
//        edt_ngayKetThuc.setOnClickListener(onClickListener);
    }
    public void showDatePickerDialog(int id) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedDate = String.format("%02d/%02d/%02d", day, month+1, year);
                showTimePickerDialog(id);
            }
        }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    public void showTimePickerDialog(int id) {
        EditText date = myDialog.findViewById(id);
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // Handle the time selection
                selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                date.setText(selectedDate + "-" + selectedTime);
            }
        }, hourOfDay, minute,false);
        timePickerDialog.show();
    }

    public void addNewTour(View view)
    {
        addDialog = new Dialog(this);
        addDialog.setContentView(R.layout.add_tuyenxe_dialog);

        Spinner spinnerXe = addDialog.findViewById(R.id.spn_maybay);
        CustomXeSpinnerAdapter customXeSpinnerAdapter = new CustomXeSpinnerAdapter(this, dataBaseHandler.getXe(""));
        spinnerXe.setAdapter(customXeSpinnerAdapter);

        // Set an OnItemSelectedListener on the spinner
        spinnerXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the spinner
                selectedXe = (MayBayModel) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is called when the selection disappears from the spinner (e.g., due to the adapter being empty)
            }
        });

        Spinner spinnerTuyen = addDialog.findViewById(R.id.spn_tuyen);
        CustomTuyenSpinnerAdapter customTuyenSpinnerAdapter = new CustomTuyenSpinnerAdapter(this, dataBaseHandler.getTuyenOption(""));
        spinnerTuyen.setAdapter(customTuyenSpinnerAdapter);

        spinnerTuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the spinner
                selectedTuyen = (ChangBayModel) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is called when the selection disappears from the spinner (e.g., due to the adapter being empty)
            }
        });


        addDialog.show();
    }

    public void submitAddNewTour(View view)
    {
        if(selectedXe != null && selectedTuyen != null)
        {
            if(dataBaseHandler.addTuyenXe(selectedTuyen, selectedXe))
            {
                Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_SHORT).show();

                addDialog.dismiss();
                loadListView();
            } else {
                Toast.makeText(getApplicationContext(), "Duplicated Record", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Add Failed", Toast.LENGTH_SHORT).show();
        }
    }
    public void goBack(View view)
    {
        finish();
    }
}