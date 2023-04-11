package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addFAB;
    ListView dblv;
    ArrayList<String> dblist;

    DBManager dbManager;

    final String[] fromColumns = new String[]{
            DatabaseHelper.COL_ID,
            DatabaseHelper.COL2_NAME,
            DatabaseHelper.COL3_NAME
    };

    final int[] toViewIds = new int[]{
            R.id.dbid,
            R.id.dbcountry,
            R.id.dbcurrency
    };

    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFAB = findViewById(R.id.addFAB);
        dblv = findViewById(R.id.dblist);


        registerForContextMenu(dblv);

        dbManager = new DBManager(this);
        //dbManager.Open();


        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(MainActivity.this, view, "FAB clicked", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, SubmitDialog.class));
            }
        });
//@style/Theme.AppCompat.Light.Dialog
        fetchDatabase();

        dblv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
    }

    public void fetchDatabase(){
        dbManager.Open();
        Cursor fetch = dbManager.fetch();
        dbManager.Close();

        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.row, fetch, fromColumns, toViewIds);
                //context, customlayout, cursor obj, [] tables, [] ids
        dblv.setAdapter(adapter);
    }




    /*@Override
    public void registerForContextMenu(View view) {
        view = dblv;
        super.registerForContextMenu(view);
    }*/

    //Context option menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_options,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.listitemedit:
                TextView idtv = (TextView) menuInfo.targetView.findViewById(R.id.dbid);
                TextView countrytv = (TextView) menuInfo.targetView.findViewById(R.id.dbcountry);
                TextView currencyTv = (TextView) menuInfo.targetView.findViewById(R.id.dbcurrency);

                String colid = idtv.getText().toString();
                //int colid = Integer.parseInt(idtv.getText().toString());
                String country = countrytv.getText().toString();
                String currency = currencyTv.getText().toString();

                Intent updateIntent = new Intent(MainActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("colid", colid);
                bundle.putString("oldCountry", country);
                bundle.putString("oldCurrency", currency);
                updateIntent.putExtra("bundle", bundle);

                /*updateIntent.putExtra("colid", colid);
                updateIntent.putExtra("oldCountry", country);
                updateIntent.putExtra("oldCurrency", currency);*/
                startActivity(updateIntent);
                return true;

            case R.id.listitemdelete:
                TextView idtvD = (TextView) menuInfo.targetView.findViewById(R.id.dbid);


                Long colidD = Long.valueOf(idtvD.getText().toString());
                //int colidD = Integer.parseInt(idtvD.getText().toString());
                //String country = countrytv.getText().toString();
                //String currency = currencyTv.getText().toString();

                dbManager.Open();
                dbManager.Delete(colidD);
                dbManager.Close();
                //Bundle dbundle =  new Bundle();
                //dbundle.putInt("colid", colidD);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    //Option menu
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addoption)
            startActivity(new Intent(MainActivity.this, SubmitDialog.class));
        return super.onOptionsItemSelected(item);
    }
}