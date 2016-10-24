package com.karnaphuli.www.kportal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.karnaphuli.www.kportal.List.ContactList;
import com.karnaphuli.www.kportal.List.InboxList;
import com.karnaphuli.www.kportal.List.OutboxList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.model.Contact;
import com.karnaphuli.www.kportal.model.Inbox;
import com.karnaphuli.www.kportal.model.Outbox;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemoActivity extends AppCompatActivity {
    Intent intent;
    SessionManager sessionManager ;

    public static Button inbox , outbox , contact ;
    TextView groupName;
    private ArrayList<Contact> contactList ;
    private ArrayList<Inbox> inboxList;
    private ArrayList<Outbox> outboxList;
    Dialog alertDialog;
    HashMap<String , String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        toolbarSetup();
        inbox = (Button) findViewById(R.id.btnInbox);
        outbox = (Button) findViewById(R.id.btnOutbox);
        contact = (Button) findViewById(R.id.btnContact);
        groupName = (TextView) findViewById(R.id.txtGroupName);
        alertDialog = new Dialog(MemoActivity.this);
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        user = sessionManager.getUserDetails();
        groupName.setText(sessionManager.getGroupOfCompany());
        getInboxList();
        getOutboxList();
        getContactList();




    }

    public void btnComposeMemoOnClick(View view){
        intent = new Intent(getApplicationContext(),MemoCompose.class);
        startActivity(intent);

    }
    public void btnInboxOnclick(View view){
        intent = new Intent(getApplicationContext(),MemoInbox.class);
        startActivity(intent);
    }
    public void btnOutBoxOnClick(View view){
        intent = new Intent(getApplicationContext(), MemoOutbox.class);
        startActivity(intent);
    }
    public void btnContactOnclick(View view){
        intent = new Intent(getApplicationContext(), ContactActivity.class);
        startActivity(intent);

    }

    public void toolbarSetup(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void getContactList(){



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
            Call<ContactList> call = api.getContactList(user.get(sessionManager.KEY_KEY));

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ContactList>() {
                @Override
                public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccess()) {
                        /**
                         * Got Successfully
                         */


                        contactList = response.body().getContacts();
                        if(!contactList.isEmpty()){
                            contact.setText("Contact ("+contactList.size()+")");
                        }


                        /**
                         * Binding that List to Adapter
                         */

                    }
                }

                @Override
                public void onFailure(Call<ContactList> call, Throwable t) {
                    dialog.dismiss();

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }

    public void getInboxList(){
        if (isNetworkConnected()) {
            final ProgressDialog progressDialog ;

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Loading Inbox Data");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();

            Call<com.karnaphuli.www.kportal.List.InboxList> call = api.getInboxList(user.get(sessionManager.KEY_KEY),"INBOX",Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID)));

            call.enqueue(new Callback<InboxList>() {
                @Override
                public void onResponse(Call<InboxList> call, Response<InboxList> response) {
                    progressDialog.dismiss();

                    if (response.isSuccess()){


                        inboxList = response.body().getInbox();
                        if(!inboxList.isEmpty()){
                            inbox.setText("Inbox ("+inboxList.size()+")");
                        }


                    }
                }

                @Override
                public void onFailure(Call<InboxList> call, Throwable t) {

                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }

    }
    public void getOutboxList() {


        if(isNetworkConnected()){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Outbox Data Loading");
            progressDialog.show();

            ApiService api = RetroClient.getApiService();
            Call<OutboxList> call = api.getOutboxList(user.get(sessionManager.KEY_KEY), "OUTBOX",Integer.parseInt(user.get(sessionManager.KEY_EMPLOYEE_ID)));
            call.enqueue(new Callback<OutboxList>() {
                @Override
                public void onResponse(Call<OutboxList> call, Response<OutboxList> response) {
                    progressDialog.dismiss();
                    if(response.isSuccess()){
                        outboxList = response.body().getOutbox();
                        if(!outboxList.isEmpty()){
                            outbox.setText("Outbox ("+outboxList.size()+")");
                        }


                    }
                }

                @Override
                public void onFailure(Call<OutboxList> call, Throwable t) {

                }
            });
        }
        else{
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }



    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
