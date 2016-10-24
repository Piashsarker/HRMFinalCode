package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.karnaphuli.www.kportal.List.ChargeHandOverList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.ChargeHandOverAdapter;
import com.karnaphuli.www.kportal.model.PendingCHOApplicationView;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChargeHandOverActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ArrayList<PendingCHOApplicationView> arrayList ;
    ChargeHandOverAdapter adapter ;
    SessionManager sessionManager;
    HashMap<String , String> user;
    int rowPosition=0;
    Dialog alertDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_hand_over2);
        sessionManager = new SessionManager(getApplicationContext());
        alertDialog = new Dialog(ChargeHandOverActivity.this);

        sessionManager.checkLogin();
        user= sessionManager.getUserDetails();
        initializeView();
        loadData();
        toolbarSetup();
    }


    private void loadData() {

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

                        arrayList = response.body().getPendingCHOApplicationView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!arrayList.isEmpty()){
                            adapter = new ChargeHandOverAdapter(getApplicationContext(), arrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new ChargeHandOverAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    if(v.getId()==R.id.btnApprove){
                                        approveDialog(arrayList.get(position).getApplicantName(), arrayList.get(position).getApplicationId().toString());
                                         rowPosition = position;
                                        }
                                    else  if(v.getId()==R.id.btnReject){
                                        rejectDialog(arrayList.get(position).getApplicantName(), arrayList.get(position).getApplicationId().toString());
                                       rowPosition = position;
                                    }
                                }
                            });

                        }
                        else {
                            alertDialog.showDialog("EMPTY!","No Data Found");

                        }


                    } else {
                        alertDialog.showDialog("ERROR!","Server Error");
                    }
                }

                @Override
                public void onFailure(Call<ChargeHandOverList> call, Throwable t) {
                    dialog.dismiss();

                    alertDialog.showDialog("ERROR!","Server Error");
                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI Or Mobile Data");
        }

    }

    private void initializeView() {

        recyclerView = (RecyclerView) findViewById(R.id.chargeHandOverlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void toolbarSetup(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    public void approveDialog(final String empId , final String applicationId){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure?")
                .setTitle("Leave Action");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postApproveResponse(empId,applicationId);

            }


        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void rejectDialog(final String empId , final String applicationId){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure?")
                .setTitle("Leave Action");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postRejectResponse(empId, applicationId);
                loadData();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void postApproveResponse(String empId, String applicationId) {
        if(isNetworkConnected()){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Response Sending To Server");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();
            Call<ResponseBody> call = api.postPendingLeaveApproved(user.get(sessionManager.KEY_KEY),"POSTCHOAGREED",empId,applicationId);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccess()){

                        progressDialog.dismiss();
                        arrayList.remove(rowPosition);
                        recyclerView.removeViewAt(rowPosition);
                        adapter.notifyItemRemoved(rowPosition);
                        adapter.notifyItemRangeChanged(rowPosition,arrayList.size());
                        adapter.notifyDataSetChanged();
                        alertDialog.showDialog("Success","Data Inserted Successfully");

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    alertDialog.showDialog("ERROR!","Server Error");
                }
            });
        }
        else {

           alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }


    }

    private void postRejectResponse(String empId, String applicationId) {
        if(isNetworkConnected()){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Response Sending To Server");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();
            Call<ResponseBody> call = api.postPendingLeaveReject(user.get(sessionManager.KEY_KEY),"POSTCHOREJECTED",empId,applicationId);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if(response.isSuccess()){
                        arrayList.remove(rowPosition);
                        recyclerView.removeViewAt(rowPosition);
                        adapter.notifyItemRemoved(rowPosition);
                        adapter.notifyItemRangeChanged(rowPosition,arrayList.size());
                        adapter.notifyDataSetChanged();
                        alertDialog.showDialog("SUCCESS!","Data Inserted Successfully");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                   alertDialog.showDialog("ERROR!","Server Error");
                }
            });
        }
        else {

            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.refresh :
                initializeView();
                loadData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

