package com.rsp.refereeprematch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecibosAdapter extends ArrayAdapter<Recibo> {

    int idrecibo;
    public RecibosAdapter(Context context, List<Recibo> object,int idrecibo) {

        super(context, 0, object);
        this.idrecibo = idrecibo;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_recibos, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Recibo recibo = getItem(position);

        // initializing our UI components of list view item.
        TextView reciboText = listitemView.findViewById(R.id.reciboText);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        String recibot = recibo.getPagoTotal();
        reciboText.setText(recibot);

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Intent intent = new Intent (getContext(), InfoRecibo.class);
                Bundle b = new Bundle();
                b.putInt("id", idrecibo);
                b.putString("nombre", recibo.getNombre());
                intent.putExtras(b);
                getContext().startActivity(intent);
            }
        });
        return listitemView;
    }
}