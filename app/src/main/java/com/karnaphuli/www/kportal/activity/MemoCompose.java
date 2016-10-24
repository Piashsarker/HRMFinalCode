package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemoCompose extends AppCompatActivity {

   private String toId, msg , message, subjectText,  idText;
    private EditText id, subject, memoBody;
    Dialog alertDialog;
    SessionManager sessionManager ;
    String selectedId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_compose);
        alertDialog = new Dialog(MemoCompose.this);
        toolbarSetup();
        initializeViews();
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if(extras!=null){
            if(extras.containsKey("message")){
                message = extras.getString("message");
                memoBody.setText(message);
            }
            if(extras.containsKey("id")){

                idText = extras.getString("id");
                id.setText(idText);
            }
            if(extras.containsKey("employee_code")){
                idText = extras.getString("employee_code");
                id.setText(idText);

            }
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                selectedId= data.getStringExtra("id");
                if (idText==null){

                    selectedId = selectedId.substring(1);
                    id.setText(selectedId);
                }
                else{
                    id.setText(idText+selectedId);
                }

            }
        }

    }

    private void initializeViews() {

        id = (EditText) findViewById(R.id.txtOfficeId);
        subject = (EditText) findViewById(R.id.txtSubject);
        memoBody = (EditText) findViewById(R.id.txtMemoBody);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compose_memu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sent:
                sentMessage();
                break;
            case R.id.contact:
                Intent intent = new Intent(MemoCompose.this,SelectContactActivity.class);
                startActivityForResult(intent,1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sentMessage() {

        toId = id.getText().toString();
        msg = memoBody.getText().toString();
        subjectText = subject.getText().toString();
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String , String> user = sessionManager.getUserDetails();

        if(isNetworkConnected()){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Data Sending To Server");
            progressDialog.show();

            if(!toId.isEmpty() && !msg.isEmpty()){
                ApiService apiService = RetroClient.getApiService();
                Call<ResponseBody> call  = apiService.postMessage(user.get(sessionManager.KEY_KEY),"SEND",user.get(sessionManager.KEY_EMPLOYEE_ID),toId,subjectText,msg);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();

                        int code = response.code();

                        if(code==200){
                            Toast.makeText(MemoCompose.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
                            id.setText("");
                            subject.setText("");
                            memoBody.setText("");

                        }
                        else{

                            Toast.makeText(MemoCompose.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        alertDialog.showDialog("ERROR!","SERVER ERROR");

                    }
                });
            }
            else{
                progressDialog.dismiss();
                Toast.makeText(MemoCompose.this, "All Fields Required", Toast.LENGTH_SHORT).show();
            }


        }
        else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }

    public void makeToast(String message) {
        Toast.makeText(MemoCompose.this, message, Toast.LENGTH_SHORT).show();
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


}
