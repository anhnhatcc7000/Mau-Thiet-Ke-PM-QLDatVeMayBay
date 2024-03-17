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

import java.util.ArrayList;
import java.util.List;

import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.Model.CTHDModel;
import edu.project.TouristTicketOrder.R;

public class CTHD_MainActivity extends AppCompatActivity implements Subject{
    private List<Observer> observers = new ArrayList<>();
    ListView lv_cthd;
    EditText edt_searchName;
    Dialog dialog;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(CTHD_MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd_main);
        dialog = new Dialog(this);
        assignVariable("");
        onLoading();
        setupSearchFunctionality();

    }

    public void assignVariable(String tenTuyen)
    {
        edt_searchName = findViewById(R.id.edt_searchName);
        lv_cthd = findViewById(R.id.lv_cthd);
        // Tạo và đăng ký adapter làm Observer
        CTHD_List_Adapter adapter = new CTHD_List_Adapter(this, dataBaseHandler.getAllCTHD(maKH, tenTuyen, ""));
        registerObserver(adapter);
        lv_cthd.setAdapter(adapter);
    }

    public void onLoading()
    {
        // Select a OrderDetail from ListView
        lv_cthd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
                CTHDModel clickedCTHD = (CTHDModel) parent.getItemAtPosition(position);
                // Show a dialog of selected OrderDetail detail
                dialog.setContentView(R.layout.cthd_detail_dialog);
                dialog.show();

                // Delete a OrderDetail when hit btn_delete
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
                        /* Reload the List when a OrderDetail was deleted */
                        assignVariable("");
                    }
                });
            }
        });
    }
    private void setupSearchFunctionality() {
        edt_searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notifyObservers(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String searchQuery) {
        for (Observer observer : observers) {
            observer.update(searchQuery);
        }
    }

    public void goBack(View view)
    {
        finish();
    }
}