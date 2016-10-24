package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.karnaphuli.www.kportal.List.OnTourList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.TourReportAdapter;
import com.karnaphuli.www.kportal.model.TourView;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourReport extends AppCompatActivity {

    RecyclerView recylerview;
    TourReportAdapter adapter;
    SessionManager sessionManager;
    private ArrayList<TourView> tourViewArrayList = new ArrayList<>();
    Dialog alertDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_report);
        alertDialog = new Dialog(TourReport.this);
        toolbarSetup();
        loadTourReportView();
        loadTourReportData();
    }

    private void loadTourReportView() {
        recylerview = (RecyclerView) findViewById(R.id.tour_report_list);
        recylerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recylerview.setLayoutManager(layoutManager);
    }


    public void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void loadTourReportData() {
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();

        if (isNetworkConnected()) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("On  Tour Data Loading");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            String key = user.get(sessionManager.KEY_KEY);
            String employeeId = user.get(sessionManager.KEY_EMPLOYEE_ID);
            Call<OnTourList> call = api.getTourReportList(key, "GETTOUR", employeeId);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<OnTourList>() {
                @Override
                public void onResponse(Call<OnTourList> call, Response<OnTourList> response) {
                    //Dismiss Dialog
                    progressDialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        tourViewArrayList = response.body().getTourView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!tourViewArrayList.isEmpty()) {
                            adapter = new TourReportAdapter(getApplicationContext(), tourViewArrayList);
                             recylerview.setAdapter(adapter);
                        } else {
                            Toast.makeText(TourReport.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<OnTourList> call, Throwable t) {
                    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
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
               loadTourReportData();

                break ;
        }
        return super.onOptionsItemSelected(item);
    }
}
