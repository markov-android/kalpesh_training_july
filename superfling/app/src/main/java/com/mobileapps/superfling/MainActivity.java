package com.mobileapps.superfling;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Item> items;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private static final String url = "http://challenge.superfling.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOnClickListener = new MyOnClickListener(this);
        //STEP 1: Initialize the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //STEP 2: Set the Layout
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //STEP 3: Set the Animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        items = new ArrayList<Item>();

        removedItems = new ArrayList<Integer>();

        //STEP 4: Set the Adapter
        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        //Request Queue
        RequestQueue rq = Volley.newRequestQueue(this);

        //Filling with JSON Data
        JsonArrayRequest itemReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("CATLOG", "Downloading the Cakes");
                        //hidePDialog();

                        try {
                            //JSONArray json_cakes = response.(0);
                            //JSONObject sector = response.getJSONObject(0);
                            //ArrayList<String>  = new ArrayList<String>();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject obj = response.getJSONObject(i);
                                Log.d("CATLOG", obj.toString());
                                //rest.setName(obj.getString("Name"));
                                //rest.setAddress(obj.getString("Address"));
                                //rest.setRating(obj.getDouble("RatingStars"));
                                //JSONArray logo = obj.getJSONArray("Logo");
                                //JSONObject logoUrl = logo.getJSONObject(0);
                                Item itemToAdd = new Item(i, obj.getInt("ImageID"), obj.getInt("UserID"), obj.getString("UserName"), obj.getString("Title"));
                                items.add(itemToAdd);

                            }

                            Log.d("CATLOG", items.size() + " total");
                            //RestaurantListAdapter adapter = new RestaurantListAdapter<>();
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CATLOG", "error: " + error.toString());
                VolleyLog.d("CATLOG", "Error: " + error.getMessage());
            }
        });
        rq.add(itemReq);

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            /*for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }*/
            removedItems.add(selectedItemId);
            items.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
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
}
