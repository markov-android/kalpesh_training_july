package com.android4dev.navigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {

    private static final String jsonUrl = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/anycat_products.json?catid=";

    private static final String TAG_LISTINGS = "Listings";
    private static final String TAG_ID = "ProductId";
    private static final String TAG_TITLE = "Title";
    private static final String TAG_PRICE = "CurrentPrice";
    private static final String TAG_BRAND = "Brand";
    private static final String TAG_IMAGE_URL = "ProductImageUrl";

    ItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        JSONArray contacts = null;
        List<Item> items = new ArrayList<Item>();

        String catid = getArguments().getString("catid");
        makeJsonObjReq(v, catid);



        /*items.add(new Item(getArguments().getString("catid"), "Item1", "150.00", "Brand1", "ImageUrl1"));
        items.add(new Item(getArguments().getString("catid"), "Item2", "200.00", "Brand2", "ImageUrl2"));

//Initializing the Recycler View
        RecyclerView recList = (RecyclerView) v.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        // specify an adapter (see also next example)
        ItemAdapter mAdapter = new ItemAdapter(items);
        recList.setAdapter(mAdapter);*/

        /*@Override
        public void onClick(View v) {
            setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
        }*/

        return v;
    }

    /**
     * Making json object request
     * */
    private void makeJsonObjReq(View v, String catid) {
        //showProgressDialog();
        Log.d("CATLOG", "JSON");
        String url = jsonUrl + catid;


        //items.add(new Item(url, "Item1", url, "Brand1", "ImageUrl1"));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                url ,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("CATLOG", response.toString());
                        //msgResponse.setText(response.toString());
                        String r = response.toString();
                        List<Item> items = new ArrayList<Item>();

                        if (r != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(r);

                                // Getting JSON Array node
                                JSONArray products = jsonObj.getJSONArray(TAG_LISTINGS);
                                //List<Item> items = new ArrayList<Item>();
                                // looping through All Contacts
                                for (int i = 0; i < products.length(); i++) {
                                    JSONObject c = products.getJSONObject(i);

                                    String id = c.getString(TAG_ID);
                                    String title = c.getString(TAG_TITLE);
                                    String price = c.getString(TAG_PRICE);
                                    String brand = c.getString(TAG_BRAND);
                                    JSONArray array_image_url = c.getJSONArray(TAG_IMAGE_URL);
                                    String image_url = array_image_url.getString(0);

                                    // tmp hashmap for single contact
                                    //HashMap<String, String> prod = new HashMap<String, String>();


                                    // adding each child node to HashMap key => value
                                    items.add(new Item(id, title, price, brand, image_url));

                                    /*Log.d("CATLOG", image_url);
                                    Picasso.with(getActivity())
                                            .load(image_url)
                                            .into(imageView);*/

                                }

                                //Initializing the Recycler View
                                /*RecyclerView recList = (RecyclerView) getView().findViewById(R.id.cardList);
                                recList.setHasFixedSize(true);
                                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                recList.setLayoutManager(llm);

                                // specify an adapter (see also next example)
                                ItemAdapter mAdapter = new ItemAdapter(getActivity(), items);
                                recList.setAdapter(mAdapter);*/




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            RecyclerView recList = (RecyclerView) getActivity().findViewById(R.id.cardList);
                            recList.setHasFixedSize(true);
                            GridLayoutManager llm = new GridLayoutManager(getActivity(),3);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            recList.setLayoutManager(llm);

                            // specify an adapter (see also next example)
                            mAdapter = new ItemAdapter(getActivity(), items);
                            recList.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.e("ServiceHandler", "Couldn't get any data from the url");
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley", "Error: " + error.getMessage());
            }
        });

        /**
         * Passing some request headers
         * */
        // Adding request to request queue
        //AppNavigator.getInstance().addToRequestQueue(jsonObjReq,
        //        "");

        AppController.getInstance().addToRequestQueue(jsonObjReq);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);

    }
}
