package edu.project.TouristTicketOrder.Admin_Activity.Voucher;

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

import edu.project.TouristTicketOrder.Admin_Activity.NhanVien.NhanVien_List_Adapter;
import edu.project.TouristTicketOrder.Admin_Activity.NhanVien.View_NhanVien_List;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.NhanVienModel;
import edu.project.TouristTicketOrder.Model.VoucherModel;
import edu.project.TouristTicketOrder.R;

public class ViewVoucher extends AppCompatActivity {
    Dialog myDialog;
    ListView voucherList;
    EditText edt_searchVoucher;

    DataBaseHandler dataBaseHandler = new DataBaseHandler(ViewVoucher.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voucher_list);
        myDialog = new Dialog(this);
        voucherList = (ListView) findViewById(R.id.lv_voucherList);
        loadData("");

        // Search customer name
        edt_searchVoucher = (EditText) findViewById(R.id.edt_searchVoucher);
        edt_searchVoucher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tenNV = edt_searchVoucher.getText().toString();
                loadData(tenNV);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Select a customer from ListView
        voucherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                VoucherModel clickedVoucher = (VoucherModel) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected "+ clickedVoucher.getMaVoucher(),Toast.LENGTH_SHORT).show();

                // Show a dialog of selected customer detail
                myDialog.setContentView(R.layout.voucher_detail_dialog);
                myDialog.show();

                // Set customer detail from List to Dialog
                TextView maVoucher = (TextView) myDialog.findViewById(R.id.tv_maVoucher);
                EditText moTa = (EditText) myDialog.findViewById(R.id.edt_MoTa);
                EditText giamGia = (EditText) myDialog.findViewById(R.id.edt_giamGia);
                EditText ngayBatDau = (EditText) myDialog.findViewById(R.id.edt_ngayBatDau);
                EditText ngayKetThuc = (EditText) myDialog.findViewById(R.id.edt_ngayKetThuc);

                maVoucher.setText(clickedVoucher.getMaVoucher());
                moTa.setText(clickedVoucher.getMoTa());
                giamGia.setText(clickedVoucher.getGiamGia());
                ngayBatDau.setText(clickedVoucher.getNgayBatDau());
                ngayKetThuc.setText(clickedVoucher.getNgayKetThuc());

                // Update a nhan vien detail
                Button btn_edit = (Button) myDialog.findViewById(R.id.btn_edit);
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String updateMoTa = moTa.getText().toString();
                        String updateGiamGia = giamGia.getText().toString();
                        String updateNgayBatDau = ngayBatDau.getText().toString();
                        String updateNgayKetThuc = ngayKetThuc.getText().toString();

                        Boolean isUpdated = dataBaseHandler.updateVoucher(clickedVoucher, updateMoTa, updateGiamGia, updateNgayBatDau, updateNgayKetThuc);

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
                        Boolean isDeleted = dataBaseHandler.deleteVoucher(clickedVoucher);

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

    public void loadData(String maVoucher)
    {
        VoucherListAdapter searched_Voucher = new VoucherListAdapter(ViewVoucher.this, dataBaseHandler.get_Voucher(maVoucher));
        voucherList.setAdapter(searched_Voucher);
    }
    public void goBack(View view)
    {
        finish();
    }
}
