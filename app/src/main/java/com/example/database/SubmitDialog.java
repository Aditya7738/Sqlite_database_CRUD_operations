package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SubmitDialog extends Activity {

    EditText country;
    EditText currency;
    Button submit;

    Activity context;
    String countries;
    String currencies;

    DBManager dbManager = new DBManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_dialog);

        setTitle("Add Currency");

        country = (EditText) findViewById(R.id.et1);
        currency = (EditText) findViewById(R.id.et2);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countries = country.getText().toString();
                currencies = currency.getText().toString();

                dbManager.Open();
                dbManager.Insert(countries, currencies);
                dbManager.Close();

                Intent mainIntnet = new Intent(SubmitDialog.this, MainActivity.class);
                //mainIntnet.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntnet);

            }
        });
    }

}