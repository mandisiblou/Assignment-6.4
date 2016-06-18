package com.example.mandisi.myassign7;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mandisi.myassign7.Services.settings.ActivateAccountService;
import com.example.mandisi.myassign7.Services.settings.Impl.ActivateAccountServiceImpl;
import com.example.mandisi.myassign7.Services.settings.Impl.ActivateServiceImpl;
import com.example.mandisi.services.R;


public class Results extends AppCompatActivity {
    private  EditText results;
    private  Button resultsBtn;
    private  Button resultsB;
    public  int answer = 1;

    private RadioButton codeButton;
    private RadioButton radiobutton2;
    private  RadioButton radiobutton3;

    private static final String TAG =
            "Results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_learnerscode);
        results=(EditText)findViewById(R.id.resultsText);
        resultsBtn = (Button) findViewById(R.id.buttonResults);
        resultsB = (Button) findViewById(R.id.resultsButton);

        //setContentView(R.layout.code1);
        codeButton=(RadioButton)findViewById(R.id.codeButton);
        radiobutton2=(RadioButton) findViewById(R.id.codeButton1);
        radiobutton3=(RadioButton) findViewById(R.id.codeButton2);

        resultsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                String result = results.getText().toString();

                if (!result.isEmpty()) {
                    Intent intentLogin = new Intent(Results.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "View your results first!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        resultsB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                String result = results.getText().toString();
                results.setText("You got: "+answer+ " out of 3");
            }
        });
    }
}
