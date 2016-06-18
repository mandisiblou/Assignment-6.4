package com.example.mandisi.myassign7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.Context;

import com.example.mandisi.services.R;

public class LoginActivity extends AppCompatActivity {
    BoundService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //////////////////////////////////////////////////////
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        ////////////////////////////////////////////////////////////
        TextView registerScreen = (TextView) findViewById(R.id.activateButton);
        TextView registerScreen1 = (TextView) findViewById(R.id.loginButton);
        TextView myTime = (TextView) findViewById(R.id.myTime);

        // Listening to register new account link
        assert registerScreen != null;
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Register screen
               Intent i = new Intent(getApplicationContext(), ActivateActivity.class);
                startActivity(i);
            }
        });

        assert myTime != null;
        myTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check time now
                String currentTime = myService.getCurrentTime();
                TextView myTextView =
                        (TextView)findViewById(R.id.myTextView);
                myTextView.setText(currentTime);
            }
        });

        // Listening to register new login link
        assert registerScreen1 != null;
        registerScreen1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Learners screen
                Intent i = new Intent(getApplicationContext(), LearnersCode.class);
                startActivity(i);
            }
        });
    }
    private ServiceConnection myConnection = new ServiceConnection()
    {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };
}