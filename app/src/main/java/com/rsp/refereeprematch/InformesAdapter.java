package com.rsp.refereeprematch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class InformesAdapter extends ArrayAdapter<Informe> {

    public InformesAdapter(Context context, List<Informe> object) {

        super(context, 0, object);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_informes, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Informe informe = getItem(position);

        // initializing our UI components of list view item.
        TextView partidotxt = listitemView.findViewById(R.id.partidoTexti);
        TextView informerecibido = listitemView.findViewById(R.id.designadotvi);
        TextView arbitxt = listitemView.findViewById(R.id.arbitrodesi);
        TextView a2txt = listitemView.findViewById(R.id.a2desi);
        TextView a1txt = listitemView.findViewById(R.id.a1desi);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        partidotxt.setText(informe.getPartido());
        arbitxt.setText(informe.getArbitroPrincipal());
        a1txt.setText(informe.getAsistente1());
        a2txt.setText(informe.getAsistente2());

        if (informe.getEmailArbitroI().equalsIgnoreCase(Constantes.EMAILUSER) || informe.getEmailA1I().equalsIgnoreCase(Constantes.EMAILUSER) || informe.getEmailA2I().equalsIgnoreCase(Constantes.EMAILUSER)) {

                informerecibido.setVisibility(View.VISIBLE);



        }else{
            informerecibido.setVisibility(View.GONE);
        }

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(informe.getEmailArbitroI().equalsIgnoreCase(Constantes.EMAILUSER) ||informe.getEmailA1I().equalsIgnoreCase(Constantes.EMAILUSER) ||informe.getEmailA2I().equalsIgnoreCase(Constantes.EMAILUSER) ){

                        Intent intent = new Intent (getContext(), InfoInforme.class);
                        Bundle b = new Bundle();
                        b.putString("nombre", informe.getNombre());
                        intent.putExtras(b);
                        getContext().startActivity(intent);

                    }else{

                        Toast.makeText(getContext(), "Informe no disponible, no puede ver la información", Toast.LENGTH_SHORT).show();
                    }

            }

        });
        return listitemView;
    }
}