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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.karnaphuli.www.kportal.List.LeaveDaysRequiredList;
import com.karnaphuli.www.kportal.List.LeaveTypeList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.model.LeaveBalance;
import com.karnaphuli.www.kportal.model.LeaveType;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.ApiServiceMessage;
import com.karnaphuli.www.kportal.retrofit.RetroClient;
import com.karnaphuli.www.kportal.retrofit.RetroClientMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveInsert extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button btnFrom, btnTo;
    int leaveId = 0;
    Dialog alertDialog;
    private EditText fromDateEtxt, toDateEtxt, etHo, etReason, etPhone;
    private String fromDate, toDate,  handOverId, reason, phone;
    private Date fromDays, toDays;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter ;
    private SessionManager sessionManager;
    private ArrayList<LeaveType> leaveTypes = new ArrayList<>();
    private HashMap<String, String> user;
    private ArrayList<LeaveBalance> leaveBalanceArrayList = new ArrayList<>();
    private String leaveTypeSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_insert);
        toolbarSetup();

        alertDialog = new Dialog(LeaveInsert.this);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        findViewsById();

        loadLeaveType();

        String currentDate = dateFormatter.format(Calendar.getInstance().getTime());
        fromDays = Calendar.getInstance().getTime();
        toDays = Calendar.getInstance().getTime();

        fromDateEtxt.setText(currentDate);
        fromDate = currentDate;

        toDateEtxt.setText(currentDate);
        toDate = currentDate;

        btnFrom.setOnClickListener(this);
        btnTo.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate = dateFormatter.format(newDate.getTime());
                fromDays = newDate.getTime();
                fromDateEtxt.setText(fromDate);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate = dateFormatter.format(newDate.getTime());
                toDays = newDate.getTime();
                toDateEtxt.setText(toDate);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void loadLeaveType() {

        if(isNetworkConnected()){

            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<LeaveTypeList> call = api.getLeaveTypeList(user.get(SessionManager.KEY_KEY), "LEAVETYPE", user.get(SessionManager.KEY_EMPLOYEE_ID));

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<LeaveTypeList>() {
                @Override
                public void onResponse(Call<LeaveTypeList> call, Response<LeaveTypeList> response) {
                    //Dismiss Dialog

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */
                        leaveTypes = response.body().getLeaveType();

                        spinnerSelectet(leaveTypes);

                    } else {
                        alertDialog.showDialog("EMPTY!","Data Not Found");
                    }


                }



                @Override
                public void onFailure(Call<LeaveTypeList> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });

        }

        else

        {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");

        }

    }

    private void spinnerSelectet(ArrayList<LeaveType> leaveTypes) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        ArrayList<String> typeList = new ArrayList<>();
        for (int i = 0; i < leaveTypes.size(); i++) {
            String name = leaveTypes.get(i).getLeaveTypeName();
            typeList.add(name);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        /**
         * Binding that List to Adapter
         */
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
        toDateEtxt = (EditText) findViewById(R.id.etToDate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
        etHo = (EditText) findViewById(R.id.etHOId);

        etReason = (EditText) findViewById(R.id.etReason);

        etPhone = (EditText) findViewById(R.id.etPhone);
        btnFrom = (Button) findViewById(R.id.btnFrom);
        btnTo = (Button) findViewById(R.id.btnTo);

    }



    @Override
    public void onClick(View view) {
        if (view == btnFrom) {
            fromDatePickerDialog.show();

        } else if (view == btnTo) {
            toDatePickerDialog.show();

        }

    }




    public void saveOnClick(View view) {

        phone = etPhone.getText().toString();
        reason = etReason.getText().toString();
        handOverId = etHo.getText().toString();


        if (leaveId != 0 && !handOverId.isEmpty() && !reason.isEmpty() && !phone.isEmpty()) {
            if (leaveBalance() >= requestLeaveDays()) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Sending..");
                progressDialog.setMessage("Leave Data Sending");
                progressDialog.show();

                ApiService apiService = RetroClient.getApiService();

                Call<ResponseBody> call = apiService.postLeave(user.get(SessionManager.KEY_KEY), "POST", user.get(SessionManager.KEY_EMPLOYEE_ID), fromDate, toDate, phone, leaveId, reason, user.get(SessionManager.KEY_EMPLOYEE_ID), handOverId);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {
                            progressDialog.dismiss();
                            alertDialog.showDialog("Success", "Your Leave Request Sent");
                            postMessage();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        alertDialog.showDialog("Error", "Server Error");
                    }
                });


            } else {
                alertDialog.showDialog("Sorry", "Not Enough Leave");

            }
        } else {
            alertDialog.showDialog("REQUIRED", "Insert All Fields");
        }



    }

    private int requestLeaveDays() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Leave Days Checking");
        progressDialog.setTitle("Checking");
        progressDialog.show();

        ApiService apiService = RetroClient.getApiService();

        Call<LeaveDaysRequiredList> call = apiService.getLeaveDays(user.get(SessionManager.KEY_KEY), "GETLEAVEBALANCE", user.get(SessionManager.KEY_EMPLOYEE_ID), leaveId, fromDate, toDate);

        call.enqueue(new Callback<LeaveDaysRequiredList>() {
            @Override
            public void onResponse(Call<LeaveDaysRequiredList> call, Response<LeaveDaysRequiredList> response) {
                if (response.isSuccess()) {
                    progressDialog.dismiss();
                    leaveBalanceArrayList = response.body().getLeaveBalance();
                }

            }

            @Override
            public void onFailure(Call<LeaveDaysRequiredList> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveInsert.this, "Leave Balance Not Found", Toast.LENGTH_SHORT).show();
            }
        });
        if (leaveBalanceArrayList.isEmpty()) {
            return 0;
        } else {
            return leaveBalanceArrayList.get(0).getLeaveDuration();
        }
    }

    private int leaveBalance() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Leave Days Checking");
        progressDialog.setTitle("Checking");
        progressDialog.show();

        ApiService apiService = RetroClient.getApiService();

        Call<LeaveDaysRequiredList> call = apiService.getLeaveDays(user.get(SessionManager.KEY_KEY), "GETLEAVEBALANCE", user.get(SessionManager.KEY_EMPLOYEE_ID), leaveId, fromDate, toDate);

        call.enqueue(new Callback<LeaveDaysRequiredList>() {
            @Override
            public void onResponse(Call<LeaveDaysRequiredList> call, Response<LeaveDaysRequiredList> response) {
                if (response.isSuccess()) {
                    progressDialog.dismiss();
                    leaveBalanceArrayList = response.body().getLeaveBalance();
                }

            }

            @Override
            public void onFailure(Call<LeaveDaysRequiredList> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveInsert.this, "Leave Balance Not Found", Toast.LENGTH_SHORT).show();
            }
        });
        if (leaveBalanceArrayList.isEmpty()) {
            return 0;
        } else {
            return leaveBalanceArrayList.get(0).getLeaveBalance();
        }
    }

    private void postMessage() {

        String reportingBossNumber = user.get(SessionManager.KEY_REPORTING_MOBILE);
        if (reportingBossNumber.length()==11){
            reportingBossNumber = "88"+reportingBossNumber;
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Message");
            progressDialog.setMessage("Message Sending......");
            progressDialog.show();
            ApiServiceMessage apiServiceMessage = RetroClientMessage.getApiService();

            Call<ResponseBody> messageCall = apiServiceMessage.postMessage(user.get(SessionManager.KEY_MSG_USER_NAME), user.get(SessionManager.KEY_MSG_PASSWORD), reportingBossNumber, user.get(SessionManager.KEY_MSG_BRAND_NAME), user.get(SessionManager.KEY_LEAVE_REQUEST_MESSAGE) + "\n" + user.get(SessionManager.KEY_EMPLOYEE_NAME) + "\n" + user.get(SessionManager.KEY_DEPARTMENT), "0/1");

            messageCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()){
                        Toast.makeText(LeaveInsert.this, "Message Sent.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LeaveInsert.this, "Message Not Sent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    alertDialog.showDialog("ERROR!","SERVER ERROR");

                }
            });

        }
        else{
            Toast.makeText(LeaveInsert.this, "Reporting Boss Number Is Not Correct", Toast.LENGTH_SHORT).show();
        }




    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        leaveTypeSelected = adapterView.getItemAtPosition(i).toString();
        leaveId = leaveTypes.get(i).getLeaveTypeId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }






}

