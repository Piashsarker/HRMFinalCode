package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.karnaphuli.www.kportal.List.ChargeHandOverList;
import com.karnaphuli.www.kportal.List.PendingLeaveList;
import com.karnaphuli.www.kportal.List.PendingOnDutyList;
import com.karnaphuli.www.kportal.List.PendingTourViewList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.model.PendingCHOApplicationView;
import com.karnaphuli.www.kportal.model.PendingLeaveApplicationView;
import com.karnaphuli.www.kportal.model.PendingOnDutyView;
import com.karnaphuli.www.kportal.model.PendingTourView;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargeHandOver extends AppCompatActivity {

    Intent intent ;
    SessionManager sessionManager ;

    public static Button chargeHandOver , leave , tour, onDuty ;

    private ArrayList<PendingCHOApplicationView> pendingChoList ;
    private ArrayList<PendingLeaveApplicationView> pendingLeaveList;
    private ArrayList<PendingTourView> pendingTourList;
    private ArrayList<PendingOnDutyView> pendingOnDutyList;
    Dialog alertDialog;
    HashMap<String , String> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_hand_over);
        toolbarSetup();
        TextView groupName = (TextView) findViewById(R.id.txtGroupName);

        chargeHandOver = (Button) findViewById(R.id.btnChargeHandOver);
        leave = (Button) findViewById(R.id.btnLeave);
        tour = (Button) findViewById(R.id.btnTour);
        onDuty = (Button) findViewById(R.id.btnOnduty);

        alertDialog = new Dialog(ChargeHandOver.this);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        groupName.setText(sessionManager.getGroupOfCompany());
        getPendingChoList();
        getPendingLeave();
        getPendingOnDuty();
        getPendingOnTour();

    }

    public void toolbarSetup(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void btnChargeHanOverOnClick(View view) {
        intent = new Intent(getApplicationContext(), ChargeHandOverActivity.class);
        startActivity(intent);
    }

    public void btnLeaveOnClick(View view) {
        intent = new Intent(getApplicationContext(), PendingLeave.class);
        startActivity(intent);
    }

    public void btnTourOnClick(View view) {
        intent = new Intent(getApplicationContext(), PendingTour.class);
        startActivity(intent);
    }

    public void btnOnDutyOnClick(View view) {
        intent = new Intent(getApplicationContext(), PendingOnDuty.class);
        startActivity(intent);
    }

    public void getPendingChoList(){

        if (isNetworkConnected()) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(this);
            dialog.setTitle("Wait");
            dialog.setMessage("Loading");
            dialog.show();


            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            String key  = user.get(sessionManager.KEY_KEY);
            int employeeId = Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID));
            Call<ChargeHandOverList> call = api.getChargeOver( key, "GETCHOAPP",employeeId );

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ChargeHandOverList>() {
                @Override
                public void onResponse(Call<ChargeHandOverList> call, Response<ChargeHandOverList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */

                        pendingChoList = response.body().getPendingCHOApplicationView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!pendingChoList.isEmpty()){

                            chargeHandOver.setText("Charge Hand Over ("+pendingChoList.size()+")");

                        }

                    }
                }

                @Override
                public void onFailure(Call<ChargeHandOverList> call, Throwable t) {
                    dialog.dismiss();

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI Or Mobile Data");
        }

    }
    public void getPendingLeave(){
        if (isNetworkConnected()) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(this);
            dialog.setTitle("Wait");
            dialog.setMessage("Loading");
            dialog.show();


            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            String key  = user.get(sessionManager.KEY_KEY);
            int employeeId = Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID));
            Call<PendingLeaveList> call = api.getPendingLeave( key, "GETPENDINGLEAVE",employeeId );

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<PendingLeaveList>() {
                @Override
                public void onResponse(Call<PendingLeaveList> call, Response<PendingLeaveList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        pendingLeaveList =  response.body().getPendingLeaveApplicationView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!pendingLeaveList.isEmpty()){
                            leave.setText("Leave ("+pendingLeaveList.size()+")");
                        }


                    }
                }

                @Override
                public void onFailure(Call<PendingLeaveList> call, Throwable t) {
                    dialog.dismiss();

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }
    }
    public void getPendingOnDuty(){
        if (isNetworkConnected()) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(this);
            dialog.setTitle("Wait");
            dialog.setMessage("Loading");
            dialog.show();


            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            String key  = user.get(sessionManager.KEY_KEY);
            int employeeId = Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID));
            Call<PendingOnDutyList> call = api.getPendingOnDuty( key, "GETPENDINGOD",employeeId );

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<PendingOnDutyList>() {
                @Override
                public void onResponse(Call<PendingOnDutyList> call, Response<PendingOnDutyList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        pendingOnDutyList = response.body().getPendingOnDutyView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!pendingOnDutyList.isEmpty()){
                           onDuty.setText("On Duty ("+pendingOnDutyList.size()+")");
                        }



                    }
                }

                @Override
                public void onFailure(Call<PendingOnDutyList> call, Throwable t) {
                    dialog.dismiss();


                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }
    public void getPendingOnTour(){
        if (isNetworkConnected()) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(this);
            dialog.setTitle("Wait");
            dialog.setMessage("Loading");
            dialog.show();


            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            String key = user.get(sessionManager.KEY_KEY);
            int employeeId = Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID));
            Call<PendingTourViewList> call = api.getPendingTour(key, "GETPENDINGTOUR", employeeId);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<PendingTourViewList>() {
                @Override
                public void onResponse(Call<PendingTourViewList> call, Response<PendingTourViewList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        pendingTourList = response.body().getPendingTourView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!pendingTourList.isEmpty()) {
                            tour.setText("Tour ("+pendingTourList.size()+")");
                        }

                    }
                }

                @Override
                public void onFailure(Call<PendingTourViewList> call, Throwable t) {
                    dialog.dismiss();


                }
            });

        } else {

        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}