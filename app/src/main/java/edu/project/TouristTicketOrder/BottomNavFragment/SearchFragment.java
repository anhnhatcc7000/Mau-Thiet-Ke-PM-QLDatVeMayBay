package edu.project.TouristTicketOrder.BottomNavFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.project.TouristTicketOrder.Admin_Activity.TuyenBay.TuyenBayListAdapter;
import edu.project.TouristTicketOrder.DataBaseHandler;
import edu.project.TouristTicketOrder.HomePage.RouteDetail;
import edu.project.TouristTicketOrder.Model.TuyenBayModel;
import edu.project.TouristTicketOrder.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lv_tuyenXeList;
    TuyenBayModel clickedTuyenXe;
    String selectedDate;
    DataBaseHandler dataBaseHandler;
    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dataBaseHandler = new DataBaseHandler(requireActivity());

        lv_tuyenXeList = view.findViewById(R.id.lv_tuyenXeList);
        onLoading("");
        lv_tuyenXeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedTuyenXe = (TuyenBayModel) parent.getItemAtPosition(position);

                Intent intent = new Intent(requireContext(), RouteDetail.class);

                intent.putExtra("Date", selectedDate);
                intent.putExtra("tuyenModel", clickedTuyenXe);

                startActivity(intent);
            }
        });
        return view;
    }

    public void onLoading(String tenTuyen)
    {
        TuyenBayListAdapter tuyenBayListAdapter = new TuyenBayListAdapter(requireActivity(), dataBaseHandler.getTuyenXeOption(tenTuyen, "",true));
        lv_tuyenXeList.setAdapter(tuyenBayListAdapter);
    }
}