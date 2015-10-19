package com.mobileapps.superfling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Exchange on 10/16/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<Item> items;
    //REMEMBER: THERE ARE FOR METHODS TO BE ALWAYS MENTIONED IN THIS CLASS OF ADAPTER
    // 1. Constructor of the class
    // 2. Static inner class to initialize the views of rows
    // 3. onCreateViewHolder(): specify the row layout file and click for each row
    // 4. onBindViewHolder(): load data in each row element
    // 5. getItemCount(): check the size

    public ItemAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewEmail;
        ImageView imageViewIcon;

        public String versionName;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewEmail = (TextView) itemView.findViewById(R.id.textViewEmail);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);
//Click event
        view.setOnClickListener(MainActivity.myOnClickListener);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewEmail = holder.textViewEmail;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(items.get(listPosition).getTitle());
        textViewEmail.setText(items.get(listPosition).getUsername());
        //imageView.setImageResource(items.get(listPosition).getImage());
        holder.versionName=textViewName.getText().toString();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
