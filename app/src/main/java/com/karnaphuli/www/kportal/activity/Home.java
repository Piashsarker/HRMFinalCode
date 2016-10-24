package com.karnaphuli.www.kportal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.Session.SessionManager;
import com.karnaphuli.www.kportal.fragment.HomeFragment;

import java.util.HashMap;

public class Home extends AppCompatActivity  {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction(homeFragment);




       // TextView navigationName = (TextView) view.findViewById(R.id.txtNavigationName);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String , String> user = sessionManager.getUserDetails();

     //   navigationName.setText(user.get(sessionManager.KEY_EMPLOYEE_NAME));

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                // if (menuItem.isChecked()) menuItem.setChecked(false);
                // else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_item_home:

                          fragmentTransaction(homeFragment);
                          return true;

                    // For rest of the options we just show a toast on click

                    case R.id.nav_item_memo:
                        Intent intent = new Intent(getApplicationContext(),MemoActivity.class);
                        startActivity(intent);

                        return true;
                    case R.id.nav_item_hr_data:
                         intent = new Intent(getApplicationContext(),HRActivity.class);
                        startActivity(intent);

                        return true;
                    case  R.id.nav_item_recommendation:
                        intent = new Intent(getApplicationContext(), ChargeHandOver.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_item_logout:
                        logOutDialog();
                        return true;
                    case R.id.nav_item_exit:
                        exitDialog();

                        return true ;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        View header = navigationView.getHeaderView(0);
        TextView headerName  = (TextView) header.findViewById(R.id.txtNavigationName);
        TextView groupName = (TextView) header.findViewById(R.id.txtGroupName);
        ImageView headerImage = (ImageView) header.findViewById(R.id.imageView);
        groupName.setText(sessionManager.getGroupOfCompany());
        headerName.setText(user.get(sessionManager.KEY_EMPLOYEE_NAME).toUpperCase());


        byte[] decodeString = Base64.decode(user.get(sessionManager.KEY_EMPLOYEE_PHOTO).getBytes(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        headerImage.setImageBitmap(bitmap);









    }

    public void fragmentTransaction(Fragment f){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame,f);
        fragmentTransaction.commit();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onBackPressed() {
        drawerLayout.closeDrawers();
        getFragmentManager().popBackStack();
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            sessionManager.logoutUser();

        } else {
            exitDialog();
        }



    }

    public void exitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You Want To Exit?")
                .setTitle("Exit App");


        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                sessionManager.logoutUser();
                Home.this.finish();
                startActivity(homeIntent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void logOutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Do You Want Logout?")
                .setTitle("Logout");


        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.logoutUser();
                 Intent intent = new Intent( getApplicationContext(), LoginActivity.class);
                startActivity( intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}