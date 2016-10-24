package com.karnaphuli.www.kportal.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.ApiServiceMessage;
import com.karnaphuli.www.kportal.retrofit.RetroClient;
import com.karnaphuli.www.kportal.retrofit.RetroClientMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnDutyInsert extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText etPurpose;
    private EditText fromDateEtxt;
    private String purpose;
    private String fromDate,currentDate;
    SessionManager sessionManager;
    Button  btnFrom;

    Dialog alertDialog ;

    HashMap<String , String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_duty);
        alertDialog = new Dialog(OnDutyInsert.this);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        toolbarSetup();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
    }


    public void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etFromDate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();
        fromDateEtxt.setText(dateFormatter.format(Calendar.getInstance().getTime()));
        fromDate = dateFormatter.format(Calendar.getInstance().getTime());
        currentDate = dateFormatter.format(Calendar.getInstance().getTime());
        etPurpose = (EditText) findViewById(R.id.etReason);
        btnFrom = (Button) findViewById(R.id.btnFrom);



    }

    private void setDateTimeField() {
        btnFrom.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate = dateFormatter.format(newDate.getTime());
                fromDateEtxt.setText(fromDate);


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View view) {
        if (view == btnFrom) {
            fromDatePickerDialog.show();

        }

    }


    public void saveOnClick(View view) {
        purpose = etPurpose.getText().toString();

        if (isNetworkConnected()) {
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("On Duty Data Sending...");

            if(!purpose.isEmpty() && !fromDate.isEmpty() ) {

                ApiService apiService = RetroClient.getApiService();
                Calendar calendar = Calendar.getInstance();
                String submissionDate = dateFormatter.format(calendar.getTime());
                Call<ResponseBody> call = apiService.postOnDuty(user.get(sessionManager.KEY_KEY), user.get(sessionManager.KEY_EMPLOYEE_ID), submissionDate,
                        fromDate, fromDate, purpose, "1", user.get(sessionManager.KEY_APPROVED_BY), user.get(sessionManager.KEY_REPORTING_TO));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        progressDialog.dismiss();
                        Toast.makeText(OnDutyInsert.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        postMessage();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(OnDutyInsert.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                progressDialog.dismiss();
                Toast.makeText(OnDutyInsert.this, "All Field Required", Toast.LENGTH_SHORT).show();
            }
        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void postMessage() {

        String reportingBossNumber = user.get(sessionManager.KEY_REPORTING_MOBILE);
        if (reportingBossNumber.length()==11){
            reportingBossNumber = "88"+reportingBossNumber;
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Message");
            progressDialog.setMessage("Message Sending......");
            progressDialog.show();
            ApiServiceMessage apiServiceMessage = RetroClientMessage.getApiService();

            Call<ResponseBody> messageCall = apiServiceMessage.postMessage(user.get(sessionManager.KEY_MSG_USER_NAME),user.get(sessionManager.KEY_MSG_PASSWORD),reportingBossNumber, user.get(sessionManager.KEY_MSG_BRAND_NAME),user.get(sessionManager.KEY_ONDUTY_REQUEST_MESSAGE)+"\n"+user.get(sessionManager.KEY_EMPLOYEE_NAME)+"\n"+user.get(sessionManager.KEY_DEPARTMENT),"0/1");

            messageCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()){
                        Toast.makeText(OnDutyInsert.this, "Message Sent.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(OnDutyInsert.this, "Message Not Sent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(OnDutyInsert.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            Toast.makeText(OnDutyInsert.this, "Reporting Boss Number Is Not Correct", Toast.LENGTH_SHORT).show();
        }




    }

}

