package com.brighterbrain.implicit_intent_google_maps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText txtSearch;
    EditText txtCategory;
    EditText txtSource;
    EditText txtDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoToMaps = (Button) findViewById(R.id.btnGoToMaps);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnCategory = (Button) findViewById(R.id.btnCategory);
        Button btnNavigate = (Button) findViewById(R.id.btnNavigate);
        txtCategory = (EditText) findViewById(R.id.txtCategory3);
        txtSource = (EditText) findViewById(R.id.txtSource);
        txtDestination = (EditText) findViewById(R.id.txtDestination);
        txtSearch = (EditText) findViewById(R.id.txtSearch);

        btnGoToMaps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Go button pressed!", Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse("geo:67.6,-122.3"); //Random Lat Long
                showMap(uri);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Go button pressed!", Toast.LENGTH_LONG).show();
                String location = txtSearch.getText().toString();
                if (!location.equals("")) {
                    Log.d("CATLOG", location);
                    String searchStr = "geo:0,0?q=" + location;
                    Uri uri = Uri.parse(searchStr);
                    showMap(uri);
                }
            }

        });

        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Go button pressed!", Toast.LENGTH_LONG).show();
                String category = txtCategory.getText().toString();
                if (category != "") {
                    String searchStr = "geo:33.893896, -84.459075?q=" + category;
                    Uri uri = Uri.parse(searchStr);
                    showMap(uri);
                }
            }
        });

        btnNavigate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Go button pressed!", Toast.LENGTH_LONG).show();
                String source = txtSource.getText().toString();
                String destination = txtDestination.getText().toString();
                //String searchStr = "google.navigation?q=" + destination + "," + source;
                //Log.d("CATLOG", searchStr);
                Uri uri = Uri.parse("http://maps.google.com/maps?saddr=" + source + "&daddr=" + destination);
                showMap(uri);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}