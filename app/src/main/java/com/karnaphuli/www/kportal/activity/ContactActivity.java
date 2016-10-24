package com.karnaphuli.www.kportal.activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.karnaphuli.www.kportal.List.ContactList;
import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.adapter.ContactAdapter;
import com.karnaphuli.www.kportal.model.Contact;
import com.karnaphuli.www.kportal.retrofit.ApiService;
import com.karnaphuli.www.kportal.retrofit.RetroClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Contact> contactList ;
    private ContactAdapter adapter ;
    Intent intent;
    SessionManager sessionManager ;
    Dialog alertDialog ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        alertDialog = new Dialog(ContactActivity.this);
        toolbarSetup();
        initViews();
        loadContactData();
    }

    private void loadContactData() {
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String , String> user = sessionManager.getUserDetails();

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

                        /**
                         * Binding that List to Adapter
                         */
                    if (!contactList.isEmpty()){
                        adapter = new ContactAdapter(getApplicationContext(), contactList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                Intent intent = new Intent(getApplicationContext(), ContactDetails.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name",contactList.get(position).getEmployeeName());
                                bundle.putString("office_id",contactList.get(position).getEmployeeCode().toString());
                                bundle.putString("designation",contactList.get(position).getDesignationName());
                                bundle.putString("department",contactList.get(position).getDepartmentName());
                                bundle.putString("mobile_no",contactList.get(position).getMobileNo());
                                bundle.putString("email",contactList.get(position).getEmail());
                                bundle.putString("location",contactList.get(position).getLocationName());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                        else{
                         alertDialog.showDialog("EMPTY!","Data Not Found");
                    }


                    } else {
                        Toast.makeText(getApplicationContext(),"Something Wrong", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<ContactList> call, Throwable t) {
                    dialog.dismiss();

                    alertDialog.showDialog("ERROR!","SERVER ERROR");
                }
            });

        } else {
            alertDialog.showDialog("NO INTERNET!","Please Enable WIFI or Mobile Data");
        }
    }

    public void toolbarSetup(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Contact> filterContactList = filterContact(newText , contactList);
                adapter.setFilter(filterContactList);
                contactList = filterContactList;
                return true;
            }
        });
        return true;
    }

    private ArrayList<Contact> filterContact(String newText, ArrayList<Contact> contactList) {
        newText = newText.toLowerCase();
        final ArrayList<Contact> filteredModelList = new ArrayList<>();

        for(Contact contact: contactList){
            final String text = contact.getEmployeeName().toLowerCase();
            if(text.contains(newText)){
                filteredModelList.add(contact);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.search:
                adapter.setFilter(contactList);
                break ;
            case R.id.refresh:
               loadContactData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.contact_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }



}
