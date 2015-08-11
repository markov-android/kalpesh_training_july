package com.brighterbrain.recyclervolley;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by Exchange on 8/5/2015.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder {
    protected TextView vName;
    protected TextView vSurname;

    public PersonViewHolder(View v) {
        super(v);
        vName =  (TextView) v.findViewById(R.id.txtName);
        vSurname = (TextView)  v.findViewById(R.id.txtSurname);
        vEmail = (TextView)  v.findViewById(R.id.txtEmail);
        vTitle = (TextView) v.findViewById(R.id.title);
    }
}
