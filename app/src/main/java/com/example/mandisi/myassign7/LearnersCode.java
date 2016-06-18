package com.example.mandisi.myassign7;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.mandisi.services.R;

/**
 * Created by Mandisi on 2016-05-08.
 */
public class LearnersCode extends AppCompatActivity {
    private  Button startbutton;
    private  RadioButton codeButton;
    private RadioButton codeButton1;
    private RadioButton codeButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_learnerscode);

        codeButton = (RadioButton) findViewById(R.id.codeButton);
        codeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(getApplicationContext(), Code1.class);
                startActivity(i);
                finish();
            }
        });
        codeButton1 = (RadioButton) findViewById(R.id.codeButton1);
        codeButton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(getApplicationContext(), Code2.class);
                startActivity(i);
                finish();
            }
        });
        codeButton2 = (RadioButton) findViewById(R.id.codeButton2);
        codeButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(getApplicationContext(), Code3.class);
                startActivity(i);
                finish();
            }
        });
//                setContentView(R.layout.code1);
//                TextView codeName = (TextView) findViewById(R.id.codeName);
//                TextView rdb9 = (TextView) findViewById(R.id.rdb9);
//                TextView rdb8 = (TextView) findViewById(R.id.rdb8);
//                TextView rdb7 = (TextView) findViewById(R.id.rdb7);
//                TextView rdb6 = (TextView) findViewById(R.id.rdb6);
//                TextView rdb5 = (TextView) findViewById(R.id.rdb5);
//                TextView rdb4 = (TextView) findViewById(R.id.rdb4);
//                TextView rdb3 = (TextView) findViewById(R.id.rdb3);
//                TextView rdb2 = (TextView) findViewById(R.id.rdb2);
//                TextView rdb1 = (TextView) findViewById(R.id.rdb1);
//                TextView que3 = (TextView) findViewById(R.id.que3);
//                TextView que2 = (TextView) findViewById(R.id.que2);
//                TextView que1 = (TextView) findViewById(R.id.que1);
//            codeName.setText("VEHICLE");
//            que1.setText("The last option to take before moving to other lane is to..");
//            rdb1.setText("A - Switch on your indicator");
//            rdb2.setText("B - Check the blind spot");
//            rdb3.setText("C - Look in ther rear view mirror");
//
//            que2.setText("You may not overtake another Vehicle?");
//            rdb4.setText("A - If the view of the road ahead is restricted");
//            rdb5.setText("B - On a one way road");
//            rdb6.setText("C - Neight of the above");
//
//            que3.setText("What is the most important rule in RSA?");
//            rdb7.setText("A - Always stop to help somebody whose vehicle broken down");
//            rdb8.setText("B - Keep left as far as possible");
//            rdb9.setText("C - Never exceed the speed limit");
/*        startbutton = (Button) findViewById(R.id.startbutton);
        startbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Do something in response to button click
                Intent i = new Intent(getApplicationContext(), Code1.class);
                startActivity(i);
            }
        });*/
    }
}