package com.rsp.refereeprematch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EstadiosAdapter extends ArrayAdapter<Estadio>{

    public EstadiosAdapter(Context context, List<Estadio> object){

        super(context,0, object);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_estadios, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Estadio estadio = getItem(position);

        // initializing our UI components of list view item.
        TextView EstadioText = listitemView.findViewById(R.id.EstadioText);
        ImageView EstadioFoto = listitemView.findViewById(R.id.EstadioFoto);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        EstadioText.setText(estadio.getNombre());
        Picasso.get().load(estadio.getFoto()).into(EstadioFoto);

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Intent intent = new Intent (getContext(), InfoEstadio.class);
                Bundle b = new Bundle();
                b.putString("id", estadio.getId());
                intent.putExtras(b);
                getContext().startActivity(intent);
            }
        });
        return listitemView;
    }

}
