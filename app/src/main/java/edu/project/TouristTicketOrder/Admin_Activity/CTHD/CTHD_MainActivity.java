package edu.project.TouristTicketOrder.Admin_Activity.CTHD;

import static edu.project.TouristTicketOrder.HomePage.HomeActivity.maKH;

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

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CTHDModel;
import edu.project.TouristTicketOrder.R;

public class CTHD_MainActivity extends AppCompatActivity {
    ListView lv_cthd;
    EditText edt_searchName;
    Dialog dialog;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(CTHD_MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd_main);
        dialog = new Dialog(this);

        assignVariable();
        onSearching("");
        onLoading();
    }

    public void assignVariable()
    {
        edt_searchName = findViewById(R.id.edt_searchName);
        lv_cthd = findViewById(R.id.lv_cthd);
    }

    public void onLoading()
    {
        // Select a customer from ListView
        lv_cthd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                CTHDModel clickedCTHD = (CTHDModel) parent.getItemAtPosition(position);
                // Show a dialog of selected customer detail
                dialog.setContentView(R.layout.cthd_detail_dialog);
                dialog.show();

                // Delete a customer when hit btn_delete
                Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
                Button btn_delete = (Button) dialog.findViewById(R.id.btn_delete);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean isDeleted = dataBaseHandler.deleteCTHD(clickedCTHD);

                        // Close Dialog
                        dialog.dismiss();
                        /* Reload the List when a customer was deleted */
                        onSearching("");
                    }
                });
            }
        });

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
                onSearching(name);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void onSearching(String tenTuyen)
    {
        CTHD_List_Adapter CTHD_List_Adapter = new CTHD_List_Adapter(getApplicationContext(), dataBaseHandler.getAllCTHD(maKH, tenTuyen, ""));
        lv_cthd.setAdapter(CTHD_List_Adapter);
    }

    public void goBack(View view)
    {
        finish();
    }
}