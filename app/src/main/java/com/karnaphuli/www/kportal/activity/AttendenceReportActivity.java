package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.karnaphuli.www.kportal.List.AttendanceList;
import com.karnaphuli.www.kportal.List.MonthList;
import com.karnaphuli.www.kportal.List.YearList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.AttendanceReportAdapter;
import com.karnaphuli.www.kportal.model.IndividualAttendance;
import com.karnaphuli.www.kportal.model.Month;
import com.karnaphuli.www.kportal.model.Year;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendenceReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    private ArrayList<Month> arrayMonthList;
    private ArrayList<Year> arrayYearList;
    private AttendanceReportAdapter adapter;
    private SessionManager sessionManager;
    private HashMap<String, String> user;
    private String yearId, monthId;
    private ArrayList<IndividualAttendance> attendanceArrayList;
    String month="";
    String year ;
    private Dialog messageDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_report);
        sessionManager = new SessionManager(getApplicationContext());
        messageDialog = new Dialog(AttendenceReportActivity.this);
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        Calendar  mCalendar = Calendar.getInstance();
        month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        year = String.valueOf(mCalendar.get(Calendar.YEAR));
        toolbarSetup();
        initViews();
        LoadDataInMonthAndYearSpinner();

    }

    private void LoadDataInMonthAndYearSpinner() {

        if (isNetworkConnected()) {
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Loading Component");
            progressDialog.show();
            ApiService api = RetroClient.getApiService();


            Call<MonthList> monthCall = api.getMonth(user.get(sessionManager.KEY_KEY), "GETMONTH", user.get(sessionManager.KEY_EMPLOYEE_ID));
            Call<YearList> yearCall = api.getYear(user.get(sessionManager.KEY_KEY), "GETYEAR", user.get(sessionManager.KEY_EMPLOYEE_ID));

            yearCall.enqueue(new Callback<YearList>() {
                @Override
                public void onResponse(Call<YearList> call, Response<YearList> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()) {
                        arrayYearList = new ArrayList<Year>();
                        arrayYearList = response.body().getYear();
                        spinnerYearSelect(arrayYearList);
                    } else {
                       messageDialog.showDialog("EMPTY!","Year Data Not Found");
                    }
                }

                @Override
                public void onFailure(Call<YearList> call, Throwable t) {
                    messageDialog.showDialog("ERROR!","Server Error!");
                }
            });
            monthCall.enqueue(new Callback<MonthList>() {
                @Override
                public void onResponse(Call<MonthList> call, Response<MonthList> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()) {
                        arrayMonthList = new ArrayList<Month>();
                        arrayMonthList = response.body().getMonth();
                        spinnerMonthSelect(arrayMonthList);
                    } else {

                        messageDialog.showDialog("EMPTY!","Data Not Found");
                    }

                }


                @Override
                public void onFailure(Call<MonthList> call, Throwable t) {
                    progressDialog.dismiss();
                    messageDialog.showDialog("ERROR!","SERVER ERROR");
                }
            });

        } else {
            messageDialog.showDialog("NO INTERNET!","Connect To WIFI or Mobile Data");
        }


    }

    public void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.attendence_report_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    private void spinnerMonthSelect(ArrayList<Month> monthList) {
        Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);




        // Spinner click listener
        spinnerMonth.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        ArrayList<String> spinnerMonthList = new ArrayList<>();
        for (int i = 0; i < monthList.size(); i++) {
            String name = monthList.get(i).getMonthName();
            spinnerMonthList.add(name);
        }


        // Creating adapter for spinner
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerMonthList);

        // Drop down layout style - list view with radio button
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerMonth.setAdapter(monthAdapter);
        spinnerMonth.setSelection(monthAdapter.getPosition(month));


        /**
         * Binding that List to Adapter
         */
    }

    public void spinnerYearSelect(ArrayList<Year> yearList) {
        Spinner spinnerYear = (Spinner) findViewById(R.id.spinnerYear);
        spinnerYear.setOnItemSelectedListener(this);

        ArrayList<String> spinnerYearList = new ArrayList<>();
        for (int i = 0; i < yearList.size(); i++) {
            String name = String.valueOf(yearList.get(i).getYearName());
            spinnerYearList.add(name);
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerYearList);

        // Drop down layout style - list view with radio button
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerYear.setAdapter(yearAdapter);
        spinnerYear.setSelection(yearAdapter.getPosition(year));

    }


    public void btnShowReport(View view) {

        if (!yearId.isEmpty() && !monthId.isEmpty()) {
            loadAttendanceReport(yearId, monthId);
        } else {
            messageDialog.showDialog("ERROR!","Select Year And Month.");
        }


    }

    private void loadAttendanceReport(String yearId, String monthId) {

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

            Call<AttendanceList> call = api.getAttendanceList(user.get(sessionManager.KEY_KEY), user.get(sessionManager.KEY_EMPLOYEE_ID), yearId, monthId);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<AttendanceList>() {
                @Override
                public void onResponse(Call<AttendanceList> call, Response<AttendanceList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        attendanceArrayList = response.body().getIndividualAttendance();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!attendanceArrayList.isEmpty()) {

                            adapter = new AttendanceReportAdapter(getApplicationContext(), attendanceArrayList);
                            recyclerView.setAdapter(adapter);
                        } else {
                            messageDialog.showDialog("Empty", "No Data Found");
                        }


                    } else {
                         messageDialog.showDialog("NO Response","Response Error");

                    }
                }

                @Override
                public void onFailure(Call<AttendanceList> call, Throwable t) {
                    dialog.dismiss();

                    messageDialog.showDialog("Error", "Server Error");

                }
            });

        } else {
            messageDialog.showDialog("No Internet","On Mobile Data Or WIFI");
        }
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinnerMonth) {
            monthId = arrayMonthList.get(position).getMonthId().toString();

        } else if (spinner.getId() == R.id.spinnerYear) {
            yearId = arrayYearList.get(position).getYearId().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
