package com.karnaphuli.www.kportal.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.activity.ChargeHandOver;
import com.karnaphuli.www.kportal.activity.HRActivity;
import com.karnaphuli.www.kportal.activity.LoginActivity;
import com.karnaphuli.www.kportal.activity.MemoActivity;
import com.karnaphuli.www.kportal.model.GroupOfCompany;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pt on 7/27/16.
 */
public class HomeFragment  extends Fragment {
    private Intent intent;
    private ImageView profileImage;
    private TextView userName , groupName;
    private  Button memo , hrData ,logout, exits, recommendation;
    private   String usrName,employeeId , employeePhoto;
    SessionManager sessionManager  ;
    private HashMap<String , String> user ;
    ArrayList<GroupOfCompany> groupOfCompanyList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getContext());
        profileImage = (ImageView) rootView.findViewById(R.id.profile_image);
        userName = (TextView) rootView.findViewById(R.id.txt_user_name);
        groupName = (TextView) rootView.findViewById(R.id.txtGroupName);
        groupName.setText(sessionManager.getGroupOfCompany());
        memo = (Button) rootView.findViewById(R.id.btnMemo);
        hrData = (Button) rootView.findViewById(R.id.btnHrData);
        logout = (Button) rootView.findViewById(R.id.btnLogout);
        exits = (Button)rootView.findViewById(R.id.btnExits);
        recommendation = (Button) rootView.findViewById(R.id.btnRecommendation);


        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        usrName = user.get(sessionManager.KEY_EMPLOYEE_NAME);
        userName.setText(usrName.toUpperCase());

        byte[] decodeString = Base64.decode(user.get(sessionManager.KEY_EMPLOYEE_PHOTO).getBytes(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        profileImage.setImageBitmap(bitmap);


        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent = new Intent(getActivity(), MemoActivity.class);
                startActivity(intent);

            }
        });

        hrData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), HRActivity.class);
                startActivity(intent);
            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ChargeHandOver.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logOutDialog();

            }
        });


        exits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              exitDialog();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }







    public void makeToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void exitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do You Want To Exit ?")
                .setTitle("Exit App");


        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                sessionManager.logoutUser();
                getActivity().finish();
                startActivity(homeIntent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void logOutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do You Want To Logout ? ")
                .setTitle("Logout");


        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.logoutUser();
                intent = new Intent(getActivity(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
