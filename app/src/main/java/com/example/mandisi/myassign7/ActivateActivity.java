package com.example.mandisi.myassign7;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.services.R;
import com.example.mandisi.myassign7.Services.settings.ActivateAccountService;
import com.example.mandisi.myassign7.Services.settings.Impl.ActivateAccountServiceImpl;
import com.example.mandisi.myassign7.Services.settings.Impl.ActivateServiceImpl;


public class ActivateActivity extends AppCompatActivity {
    private  EditText emailAddress;
    private  EditText password;
    private  EditText orgcode;
    private  Button activateButton;
    private ActivateServiceImpl activateService;
    private boolean isBound = false;
    private ActivateAccountService activateAccountService;

    private static final String TAG =
            "ActivateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activateAccountService = ActivateAccountServiceImpl.getInstance();
        setContentView(R.layout.activity_activate);
        emailAddress=(EditText)findViewById(R.id.activate_emailAddress);
        password=(EditText) findViewById(R.id.activate_password);
        orgcode=(EditText) findViewById(R.id.activate_organisationCode);
        activateButton = (Button) findViewById(R.id.activate_activateButton);

       // Intent intent = new Intent(this, ActivateServiceImpl.class);
       // App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


        activateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                String username = emailAddress.getText().toString();
                String passwd = password.getText().toString();
                if (!username.isEmpty() && !passwd.isEmpty()) {
                    // activate
                    //activateAccountService.activateAccount(App.getAppContext(), username, passwd);
                    Intent intentLogin = new Intent(ActivateActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please Enter the Credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });
               // Log.i("EMAIL",emailAddress.getText().toString());
                //Log.i("PASS",password.getText().toString());
               // Log.i("CODE",orgcode.getText().toString());
    }
    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            ActivateServiceImpl.ActivateServiceLocalBinder binder = (ActivateServiceImpl.ActivateServiceLocalBinder) service;
            activateService = binder.getService();
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

}
