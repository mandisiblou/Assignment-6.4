package com.example.mandisi.myassign7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.mandisi.services.R;

/**
 * Created by Mandisi on 2016-05-08.
 */
public class Code2 extends AppCompatActivity {
    private RadioButton codeButton;
    private RadioButton radiobutton2;
    private  RadioButton radiobutton3;
    private Button startbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code2);
        codeButton=(RadioButton)findViewById(R.id.codeButton);
        radiobutton2=(RadioButton) findViewById(R.id.codeButton1);
        radiobutton3=(RadioButton) findViewById(R.id.codeButton2);
        startbutton = (Button) findViewById(R.id.startbutton);

//        radiobutton.setText("1");
//        radiobutton2.setText("2");
//        radiobutton3.setText("3");

        startbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(getApplicationContext(), Results.class);
                startActivity(i);
                finish();
//                Log.i("EMAIL",codeButton.getText().toString());
//                Log.i("EMAIL",radiobutton2.getText().toString());
//                Log.i("EMAIL",radiobutton3.getText().toString());
            }
        });
    }
}