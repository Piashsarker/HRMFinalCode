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

import com.karnaphuli.www.kportal.List.DistrictList;
import com.karnaphuli.www.kportal.List.ThanaList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.model.District;
import com.karnaphuli.www.kportal.model.Thana;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.ApiServiceMessage;
import com.karnaphuli.www.kportal.retrofit.RetroClient;
import com.karnaphuli.www.kportal.retrofit.RetroClientMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourInsert extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    SessionManager sessionManager;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText etPurpose;
    private EditText toDateEtxt;
    private String purpose;
    private String  toDate, fromDate, areaId, districtId;
    private ArrayList<Thana> thanaArrayList;
    private ArrayList<District> districtArrayList;
    private Button btnTo;
    Dialog alertDialog;


    HashMap<String, String> user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_add);

        alertDialog = new Dialog(TourInsert.this);
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
        toDateEtxt = (EditText) findViewById(R.id.etToDate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
        toDateEtxt.requestFocus();
        toDateEtxt.setText(dateFormatter.format(Calendar.getInstance().getTime()));
        toDate = dateFormatter.format(Calendar.getInstance().getTime());
        fromDate = dateFormatter.format(Calendar.getInstance().getTime());
        etPurpose = (EditText) findViewById(R.id.etReason);
        btnTo = (Button) findViewById(R.id.btnTo);
        loadDistrict();




    }

    private void setDateTimeField() {

        btnTo.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();


        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate = dateFormatter.format(newDate.getTime());
                toDateEtxt.setText(toDate);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View view) {
        if (view == btnTo) {
            toDatePickerDialog.show();

        }

    }


    public void saveOnClick(View view) {
        purpose = etPurpose.getText().toString();

        if (isNetworkConnected()) {
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("On Duty Data Sending...");
            progressDialog.show();
            if (!purpose.isEmpty() && !fromDate.isEmpty() && !toDate.isEmpty()&& !areaId.isEmpty()) {
                ApiService apiService = RetroClient.getApiService();
                Calendar calendar = Calendar.getInstance();
                String submissionDate = dateFormatter.format(calendar.getTime());
                Call<ResponseBody> call = apiService.postOnTour(user.get(sessionManager.KEY_KEY), user.get(sessionManager.KEY_EMPLOYEE_ID), submissionDate,
                        toDate, toDate, purpose, "1", user.get(sessionManager.KEY_APPROVED_BY), user.get(sessionManager.KEY_REPORTING_TO),areaId);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        if (response.code() == 200) {
                            Toast.makeText(TourInsert.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            postMessage();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(TourInsert.this, "Server Error" + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(TourInsert.this, "All Field Required", Toast.LENGTH_SHORT).show();
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

            Call<ResponseBody> messageCall = apiServiceMessage.postMessage(user.get(sessionManager.KEY_MSG_USER_NAME),user.get(sessionManager.KEY_MSG_PASSWORD),reportingBossNumber, user.get(sessionManager.KEY_MSG_BRAND_NAME),user.get(sessionManager.KEY_TOUR_REQUEST_MESSAGE)+"\n"+user.get(sessionManager.KEY_EMPLOYEE_NAME)+"\n"+user.get(sessionManager.KEY_DEPARTMENT),"0/1");

            messageCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccess()){
                        Toast.makeText(TourInsert.this, "Message Sent.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(TourInsert.this, "Message Not Sent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(TourInsert.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            Toast.makeText(TourInsert.this, "Reporting Boss Number Is Not Correct", Toast.LENGTH_SHORT).show();
        }




    }

    private void loadDistrict(){

        if(isNetworkConnected()){

            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<DistrictList> call = api.getDistrict(user.get(sessionManager.KEY_KEY), "GETDISTRICT", user.get(sessionManager.KEY_EMPLOYEE_ID));

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<DistrictList>() {
                @Override
                public void onResponse(Call<DistrictList> call, Response<DistrictList> response) {
                    //Dismiss Dialog

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */
                        districtArrayList = response.body().getDistrict();

                         districtSpinner(districtArrayList);

                    } else {
                        alertDialog.showDialog("EMPTY!","Data Not Found");
                    }


                }



                @Override
                public void onFailure(Call<DistrictList> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });

        }

        else

        {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");

        }

    }

    private void districtSpinner(ArrayList<District> districtArrayList) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_district);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        ArrayList<String> typeList = new ArrayList<>();
        for (int i = 0; i < districtArrayList.size(); i++) {
            String name = districtArrayList.get(i).getDistrictName();
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

    private void loadThana(String districtId){

        if(isNetworkConnected()){

            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<ThanaList> call = api.getThana(user.get(sessionManager.KEY_KEY), "GETTHANA", user.get(sessionManager.KEY_EMPLOYEE_ID),districtId);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ThanaList>() {
                @Override
                public void onResponse(Call<ThanaList> call, Response<ThanaList> response) {
                    //Dismiss Dialog

                    if (response.isSuccess()) {
                        /**
                         * Got Successfully
                         */
                        thanaArrayList = response.body().getThana();

                        thanaSpinner(thanaArrayList);

                    } else {
                        alertDialog.showDialog("EMPTY!","Data Not Found");
                    }


                }



                @Override
                public void onFailure(Call<ThanaList> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }
            });

        }

        else

        {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");

        }


    }

    private void thanaSpinner(ArrayList<Thana> thanaArrayList) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_thana);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        ArrayList<String> typeList = new ArrayList<>();
        for (int i = 0; i < thanaArrayList.size(); i++) {
            String name = thanaArrayList.get(i).getAreaName();
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_district:
                districtId =  districtArrayList.get(position).getDistrictId().toString();
                loadThana(districtId);
                break;
            case R.id.spinner_thana:
                areaId = thanaArrayList.get(position).getAreaID().toString();
                break ;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
