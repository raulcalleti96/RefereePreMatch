package com.rsp.refereeprematch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PartidosAdapter extends ArrayAdapter<Partido> {

    public PartidosAdapter(Context context, List<Partido> object) {

        super(context, 0, object);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_partidos, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Partido partido = getItem(position);

        // initializing our UI components of list view item.
        TextView partidoText = listitemView.findViewById(R.id.partidoText);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        partidoText.setText(partido.getNombre());

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : " + partido.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}
