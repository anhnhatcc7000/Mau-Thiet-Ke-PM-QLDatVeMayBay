package edu.project.TouristTicketOrder.BottomNavFragment;

import static android.content.Context.MODE_PRIVATE;
import static edu.project.TouristTicketOrder.Account_Activity.LoginActivity.cus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.project.TouristTicketOrder.Account_Activity.AccountDetail;
import edu.project.TouristTicketOrder.Account_Activity.LoginActivity;
import edu.project.TouristTicketOrder.HomePage.HomeActivity;
import edu.project.TouristTicketOrder.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_logOut;
    SharedPreferences currentUserPref;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        currentUserPref = requireActivity().getSharedPreferences(cus, MODE_PRIVATE);
        String username = currentUserPref.getString("cusName", null); // null is the default value if the key is not found

        TextView tv_userName = view.findViewById(R.id.tv_userName);
        tv_userName.setText(username);

        TextView btn_accDetail = (TextView) view.findViewById(R.id.btn_accDetail);
        btn_accDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), AccountDetail.class));
            }
        });

        btn_logOut = (Button) view.findViewById(R.id.btn_logOut);
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = currentUserPref.edit();
                editor.putBoolean("autoLogin", false);
                editor.apply();

                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        autoLoginFunction(view);
        return view;
    }

    public void autoLoginFunction(View view)
    {
        TextView tv_saveLogin = (TextView) view.findViewById(R.id.tv_saveLogin);
        currentUserPref = requireActivity().getSharedPreferences(cus, MODE_PRIVATE);
        boolean autoLogin = currentUserPref.getBoolean("autoLogin", false);
        if(autoLogin)
            tv_saveLogin.setText("Huỷ tự động đăng nhập");
         else
            tv_saveLogin.setText("Tự động đăng nhập");

        tv_saveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = currentUserPref.edit();
                boolean autoLogin = currentUserPref.getBoolean("autoLogin", false);
                if(autoLogin)
                {
                    tv_saveLogin.setText("Tự động đăng nhập");
                    Toast.makeText(requireActivity(), "Đã huỷ tự động đăng nhập", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("autoLogin", false);
                } else {
                    tv_saveLogin.setText("Huỷ tự động đăng nhập");
                    Toast.makeText(requireActivity(), "Đã lưu tự động đăng nhập", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("autoLogin", true);
                }
                editor.apply();
            }
        });
    }
}