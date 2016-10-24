package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karnaphuli.www.kportal.List.GroupOfCompanyList;
import com.karnaphuli.www.kportal.List.LoginList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.model.GroupOfCompany;
import com.karnaphuli.www.kportal.model.LoginTable;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Toolbar mToolbar;

    private EditText  inputEmail, inputPassword;
    private TextInputLayout  inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    private ArrayList<LoginTable> loginList = new ArrayList<>();
    private SessionManager sessionManager;
    private TextView txtGroupName ;
    private ArrayList<GroupOfCompany> groupOfCompanyList;
    Dialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(getApplicationContext());
        alertDialog  = new Dialog(LoginActivity.this);

        mToolbar    = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Login");
        txtGroupName = (TextView) findViewById(R.id.txtGroupName);
        loadGroupName();
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);


        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             submitForm();



            }
        });
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateEmail() && !validatePassword()) {
            return;
        }
        else{
            loadLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
        }

    }


    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty()) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }
    public void loadLogin(String user , String pass){
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
            Call<LoginList> call = api.getLoginDetails("fr_pt_2016",user ,pass);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<LoginList>() {
                @Override
                public void onResponse(Call<LoginList> call, Response<LoginList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();
                    int res = response.code();
                    if(response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        loginList = response.body().getLoginTable();

                        /**
                         * Binding that List to Adapter
                         */
                        checkLogin();

                    } else {
                        Toast.makeText(getApplicationContext(),"Something Wrong", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<LoginList> call, Throwable t) {
                    dialog.dismiss();

                    alertDialog.showDialog("ERROR!","SERVER ERROR");

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }
    }

    private void checkLogin() {
        if(!loginList.isEmpty()){

            sessionManager = new SessionManager(getApplicationContext());

            sessionManager.createLoginSession("fr_pt_2016",loginList.get(0).getEmployeeId().toString(),loginList.get(0).getEmployeeName().toString(),loginList.get(0).getEmployeePhoto().toString()
            ,loginList.get(0).getApprovedBy().toString(),loginList.get(0).getReportingTo().toString(),
                    loginList.get(0).getDepartmentName(), loginList.get(0).getEmployeeCode(),loginList.get(0).getMobileNo(),loginList.get(0).getReportingMobileNo(),loginList.get(0).getLeaveReqMsg(),loginList.get(0).getUserId().toString(),loginList.get(0).getMsgUserName(),loginList.get(0).getMsgUserPass(),loginList.get(0).getMsgBrandName(),loginList.get(0).getLeaveRejMsg(),loginList.get(0).getLeaveAprMsg(),
                    loginList.get(0).getTourReqMsg(), loginList.get(0).getTourAprMsg(), loginList.get(0).getTourRejMsg(),loginList.get(0).getODReqMsg(), loginList.get(0).getODAprMsg(),loginList.get(0).getODRejMsg());
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();

          }

        else {
            Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG)
                    .show();
            }


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    private void loadGroupName() {

        if (isNetworkConnected()) {


            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<GroupOfCompanyList> call = api.getGroupName("fr_pt_2016");

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<GroupOfCompanyList>() {
                @Override
                public void onResponse(Call<GroupOfCompanyList> call, Response<GroupOfCompanyList> response) {
                    //Dismiss Dialog

                    if(response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        groupOfCompanyList = response.body().getGroupOfCompany();

                        /**
                         * Binding that List to Adapter
                         */
                        if (!groupOfCompanyList.isEmpty()){
                            txtGroupName.setText(groupOfCompanyList.get(0).getGroupOfCompanyName());

                            sessionManager.addGroupOfCompany(groupOfCompanyList.get(0).getGroupOfCompanyName());
                        }
                        else{

                        }


                    }
                }

                @Override
                public void onFailure(Call<GroupOfCompanyList> call, Throwable t) {

                }
            });

        }


    }


}
