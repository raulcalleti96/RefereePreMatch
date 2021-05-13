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

public class PartidosAdapter extends ArrayAdapter<Partido> {

    int idPartidos;
    public PartidosAdapter(Context context, List<Partido> object, int idPartidos) {

        super(context, 0, object);
        this.idPartidos = idPartidos;
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
        TextView designadoaviso = listitemView.findViewById(R.id.designadotv);
        TextView arbides = listitemView.findViewById(R.id.arbitrodes);
        TextView asis1des = listitemView.findViewById(R.id.a1des);
        TextView asis2des = listitemView.findViewById(R.id.a2des);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        partidoText.setText(partido.getNombre());
        arbides.setText(partido.getArbitro());
        asis1des.setText(partido.getAsistente1());
        asis2des.setText(partido.getAsistente2());

        if(Constantes.IDUSUARIO == 0) {
            if(partido.getEmailArbitro().equalsIgnoreCase(Constantes.EMAILUSER) ||partido.getEmailA1().equalsIgnoreCase(Constantes.EMAILUSER) ||partido.getEmailA2().equalsIgnoreCase(Constantes.EMAILUSER) ){
                designadoaviso.setVisibility(View.VISIBLE);
            }else{
                designadoaviso.setVisibility(View.GONE);

            }
        }else {
            designadoaviso.setVisibility(View.GONE);
        }


        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Constantes.IDUSUARIO == 0) {
                    if(partido.getEmailArbitro().equalsIgnoreCase(Constantes.EMAILUSER) ||partido.getEmailA1().equalsIgnoreCase(Constantes.EMAILUSER) ||partido.getEmailA2().equalsIgnoreCase(Constantes.EMAILUSER) ){

                        Intent intent = new Intent (getContext(), InfoPartido.class);
                        Bundle b = new Bundle();
                        b.putString("nombre", partido.getNombre());
                        b.putInt("id", idPartidos);
                        intent.putExtras(b);
                        getContext().startActivity(intent);

                    }else{

                        Toast.makeText(getContext(), "Partido no designado, no puede ver la información", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent (getContext(), InfoPartido.class);
                    Bundle b = new Bundle();
                    b.putString("nombre", partido.getNombre());
                    b.putInt("id", idPartidos);
                    intent.putExtras(b);
                    getContext().startActivity(intent);
                }

            }
        });
        return listitemView;
    }
}
