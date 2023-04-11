package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    TextView idtv;
    EditText etcountry, etcurrency;
    Button updateBtn, deleteBtn;

    //DBManager dbManager = new DBManager(this);
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        idtv = (TextView) findViewById(R.id.idtv);
        etcountry = (EditText) findViewById(R.id.countryEt);
        etcurrency = (EditText) findViewById(R.id.currencyEt);
        updateBtn = (Button) findViewById(R.id.update);
        deleteBtn = (Button) findViewById(R.id.delete);

        setTitle("Update record");

        Intent uintent = getIntent();
        Bundle extras = uintent.getBundleExtra("bundle");

        String id = extras.getString("colid");
        String oldCountry = extras.getString("oldCountry");
        String oldCurrency = extras.getString("oldCurrency");

        idtv.setText(id);
        etcountry.setText(oldCountry);
        etcurrency.setText(oldCurrency);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

         dbManager = new DBManager(this);
         dbManager.Open();

        /*updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCountry = etcountry.getText().toString();
                String newCurrency = etcurrency.getText().toString();
                String col_id = idtv.getText().toString();

                dbManager.Open();
                dbManager.Update(col_id, newCountry, newCurrency);
                dbManager.Close();

                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });*/

        /*deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colid = idtv.getText().toString();

                dbManager.Open();
                dbManager.Delete(colid);
                dbManager.Close();

                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        Long _id = Long.valueOf(idtv.getText().toString());
        String country = etcountry.getText().toString();
        String currency = etcurrency.getText().toString();

        switch (view.getId()){
            case R.id.update:
                dbManager.Update(_id, country, currency);
                dbManager.Close();

                returnHome();

                break;

            case R.id.delete:
                dbManager.Delete(_id);
                dbManager.Close();

                returnHome();

                break;
        }

    }

    private void returnHome() {
        startActivity(new Intent(EditActivity.this, MainActivity.class));
    }
}