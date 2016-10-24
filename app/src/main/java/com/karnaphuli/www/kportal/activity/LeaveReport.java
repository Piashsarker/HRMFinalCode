package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.karnaphuli.www.kportal.List.LeaveReportList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.LeaveAndTourAdapter;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveReport extends AppCompatActivity {

    private ArrayList<com.karnaphuli.www.kportal.model.LeaveReport> leaveReportArrayList;
    private LeaveAndTourAdapter adapter;
    private RecyclerView recyclerView;
    Intent intent;
    SessionManager sessionManager ;
    Dialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_report);
        alertDialog  = new Dialog(LeaveReport.this);
        initializeViews();
        loadLeaveAndTourList();
    }

    private void initializeViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.leave_and_tour_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
    private void loadLeaveAndTourList() {
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String , String> user = sessionManager.getUserDetails();
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
            Call<LeaveReportList> call = api.getLeaveReportList( key, "GET",employeeId );

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<LeaveReportList>() {
                @Override
                public void onResponse(Call<LeaveReportList> call, Response<LeaveReportList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        leaveReportArrayList = response.body().getLeaveReport();

                        /**
                         * Binding that List to Adapter
                         */
                    if (!leaveReportArrayList.isEmpty()){
                        adapter = new LeaveAndTourAdapter(getApplicationContext(), leaveReportArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                        else {
                        alertDialog.showDialog("EMPTY!","Data Not Found");
                    }


                    } else {
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<LeaveReportList> call, Throwable t) {
                    dialog.dismiss();

                    alertDialog.showDialog("ERROR!","SERVER ERROR");

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inbox,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                 loadLeaveAndTourList();
                break ;
        }
        return super.onOptionsItemSelected(item);
    }
}
