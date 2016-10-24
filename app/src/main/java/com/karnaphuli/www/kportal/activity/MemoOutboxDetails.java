package com.karnaphuli.www.kportal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;

public class MemoOutboxDetails extends AppCompatActivity {
    TextView id , message , dateTime,subject;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_outbox_details);
        bundle = getIntent().getExtras();
        toolbarSetup();
        initializeViews();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        TextView groupName = (TextView) findViewById(R.id.txtGroupName);
        groupName.setText(sessionManager.getGroupOfCompany());
        getAndSetData();
    }

    private void getAndSetData() {


        id.setText("Sender: "+bundle.getString("sender_name")+" ("+bundle.getString("sender_id")+")");
        message.setText(bundle.getString("message"));
        subject.setText(bundle.getString("subject"));
        dateTime.setText("Date: " +bundle.getString("date_and_time")+" , "+bundle.getString("time"));

    }

    private void initializeViews() {
        id = (TextView) findViewById(R.id.txt_from_name);
        subject = (TextView) findViewById(R.id.txt_subject);
        message = (TextView) findViewById(R.id.txt_message);
        dateTime = (TextView) findViewById(R.id.txt_date_time);


    }

    private void toolbarSetup() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.inbox_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sent:
                Bundle bundle1 = new Bundle();
                Intent intent = new Intent(MemoOutboxDetails.this, MemoCompose.class);
                bundle1.putString("message",message.getText().toString());
                bundle1.putString("subject",subject.getText().toString());
                bundle1.putString("id",bundle.getString("sender_id"));
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.forward:
                Bundle bundle2 = new Bundle();
                Intent intent1 = new Intent(MemoOutboxDetails.this,MemoCompose.class);
                bundle2.putString("message",message.getText().toString());
                bundle2.putString("id",bundle.getString("sender_id"));
                intent1.putExtras(bundle2);
                startActivity(intent1);
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}

