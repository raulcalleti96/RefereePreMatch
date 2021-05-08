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

public class ArbitrosAdapter extends ArrayAdapter<Arbitro> {


    public ArbitrosAdapter(Context context, List<Arbitro> object){

        super(context,0, object);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.items_arbitros, parent, false);
        }

        Arbitro users = getItem(position);

        TextView nameTV = listitemView.findViewById(R.id.UserText);

        nameTV.setText(users.getNombre());

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (getContext(), InfoContacto.class);
                Bundle b = new Bundle();
                b.putString("email", users.getEmail());
                intent.putExtras(b);
                getContext().startActivity(intent);
            }
        });
        return listitemView;
    }

}
