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
import android.widget.Toast;

import com.karnaphuli.www.kportal.List.PendingTourViewList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.PendingTourAdapter;
import com.karnaphuli.www.kportal.model.PendingTourView;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.ApiServiceMessage;
import com.karnaphuli.www.kportal.retrofit.RetroClient;
import com.karnaphuli.www.kportal.retrofit.RetroClientMessage;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingTour extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<PendingTourView> arrayList;
    PendingTourAdapter adapter;
    SessionManager sessionManager;
    HashMap<String, String> user;
    int rowPosition = 0;
    Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_tour);
        alertDialog = new Dialog(PendingTour.this);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
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


                        arrayList = response.body().getPendingTourView();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!arrayList.isEmpty()) {
                            adapter = new PendingTourAdapter(getApplicationContext(), arrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new PendingTourAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    rowPosition = position;
                                    if (v.getId() == R.id.btnReject) {

                                        rejectDialog(String.valueOf(arrayList.get(position).getEmployeeId()), arrayList.get(position).getTourId().toString());
                                        rowPosition = position;
                                    } else if (v.getId() == R.id.btnApprove) {
                                        approveDialog(String.valueOf(arrayList.get(position).getEmployeeId()), arrayList.get(position).getTourId().toString());
                                        rowPosition = position;
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(PendingTour.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<PendingTourViewList> call, Throwable t) {
                    dialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!", "Please Enable WIFI or Mobile Data");
        }

    }

    private void initializeView() {

        recyclerView = (RecyclerView) findViewById(R.id.pendingTourList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void approveDialog(final String empId, final String applicationId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure?")
                .setTitle("Leave Action");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postApproveResponse(empId, applicationId);
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


    public void rejectDialog(final String empId, final String applicationId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure?")
                .setTitle("Leave Action");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postRejectResponse(empId, applicationId);
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
        if (isNetworkConnected()) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Response Sending To Server");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();
            Call<ResponseBody> call = api.postPendingTourApproved(user.get(sessionManager.KEY_KEY), "POSTTOURAPPROVE", empId, applicationId);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccess()) {
                        progressDialog.dismiss();

                        int leaveStatus = Integer.parseInt(arrayList.get(rowPosition).getTourStatus());
                        if (leaveStatus == 0) {
                            sentMessage(user.get(sessionManager.KEY_TOUR_APPROVE_MESSAGE), arrayList.get(rowPosition).getAppMobileNo());
                        } else if (leaveStatus == 2) {
                            sentMessage(user.get(sessionManager.KEY_TOUR_APPROVE_MESSAGE), arrayList.get(rowPosition).getApplicantMobileNo());
                        }

                        arrayList.remove(rowPosition);
                        adapter.notifyItemRemoved(rowPosition);
                        adapter.notifyItemRangeChanged(rowPosition, arrayList.size());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(PendingTour.this, "Response Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(PendingTour.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }


    }

    private void postRejectResponse(String empId, String applicationId) {
        if (isNetworkConnected()) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Response Sending To Server");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();
            Call<ResponseBody> call = api.postPendingTourReject(user.get(sessionManager.KEY_KEY), "POSTTOURREJECTED", empId, applicationId);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()) {
                        Toast.makeText(PendingTour.this, "Response Sent Successfully", Toast.LENGTH_SHORT).show();
                        sentMessage(user.get(sessionManager.KEY_TOUR_REJECTED_MESSAGE), arrayList.get(rowPosition).getApplicantMobileNo());

                        arrayList.remove(rowPosition);
                        adapter.notifyItemRemoved(rowPosition);
                        adapter.notifyItemRangeChanged(rowPosition, arrayList.size());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(PendingTour.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                loadData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sentMessage(String message, String applicantMobileNo) {

        String reportingBossNumber = applicantMobileNo;
        if (reportingBossNumber.length() == 11) {
            reportingBossNumber = "88" + reportingBossNumber;
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Message");
            progressDialog.setMessage("Message Sending......");
            progressDialog.show();
            ApiServiceMessage apiServiceMessage = RetroClientMessage.getApiService();

            Call<ResponseBody> messageCall = apiServiceMessage.postMessage(user.get(sessionManager.KEY_MSG_USER_NAME), user.get(sessionManager.KEY_MSG_PASSWORD), reportingBossNumber, user.get(sessionManager.KEY_MSG_BRAND_NAME), message, "0/1");

            messageCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()) {
                        Toast.makeText(PendingTour.this, "Message Sent To Reporting Boss", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PendingTour.this, "Message Not Sent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(PendingTour.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(PendingTour.this, "Reporting Boss Number Is Not Correct", Toast.LENGTH_SHORT).show();
        }


    }
}
