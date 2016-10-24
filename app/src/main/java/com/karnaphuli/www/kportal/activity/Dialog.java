package com.karnaphuli.www.kportal.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.karnaphuli.www.kportal.R;

/**
 * Created by pt on 10/2/16.
 */
public class Dialog {

    private Context context ;

    public Dialog(Context context ){
        this.context = context;
    }

    public void showDialog( String title , String message ){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setIcon(R.drawable.warning);
        builder.setMessage(message);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();


        alertDialog.show();

    }


}
