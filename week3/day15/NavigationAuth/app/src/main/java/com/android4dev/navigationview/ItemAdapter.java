package com.android4dev.navigationview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Exchange on 8/10/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Activity act;
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView vId;
        protected TextView vTitle;
        protected TextView vPrice;
        protected TextView vBrand;
        protected TextView vImageUrl;
        protected ImageView vImageView;

        public ItemViewHolder(View v) {
            super(v);
            String id;
            String title;
            String price;
            String brand;
            String imageurl;
            vId =  (TextView) v.findViewById( R.id.id);
            vTitle = (TextView)  v.findViewById(R.id.title);
            vPrice = (TextView)  v.findViewById(R.id.price);
            vBrand = (TextView)  v.findViewById(R.id.brand);
            vImageUrl = (TextView)  v.findViewById(R.id.imageurl);
            vImageView = (ImageView)  v.findViewById(R.id.imgview);
            List<Item> items = new ArrayList<Item>();
        }
    }


    List<Item> items;

    ItemAdapter(Activity act2, List<Item> items){
        Log.d("CATLOG", "ENTRO");
        this.act = act2;
        this.items = items;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        //itemViewHolder.vId.setText(items.get(i).id);
        //itemViewHolder.vTitle.setText(items.get(i).title);
        //itemViewHolder.vBrand.setText(items.get(i).brand);
        //itemViewHolder.vPrice.setText(items.get(i).price);
        //itemViewHolder.vImageUrl.setText(items.get(i).imageurl);
        //itemViewHolder.vImageView
        //ImageView imgview = itemViewHolder.vImageView;

        //Log.d("CATLOG", image_url);
        Picasso.with(act)
                .load(items.get(i).imageurl)
                .into(itemViewHolder.vImageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
