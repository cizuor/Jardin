package com.example.pierre.jardin.Fourniture;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FournitureAPI;
import com.example.pierre.jardin.api.NBFournitureAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 01/10/2017.
 */

public class FournitureChantierAdapter extends RecyclerView.Adapter<FournitureChantierAdapter.ViewHolder> {


    private ArrayList<ParseObject> listFourniture;

    public FournitureChantierAdapter(ArrayList<ParseObject> listFourniture) {
        this.listFourniture = listFourniture;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_fourniture_chantier, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listFourniture.get(position));
    }

    @Override
    public int getItemCount() {
        return listFourniture.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        private TextView prix;
        private TextView nombre;
        private TextView prixtotal;
        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomFourniture);
            prix = (TextView) itemView.findViewById(R.id.prixFourniture);
            nombre = (TextView) itemView.findViewById(R.id.NBFourniture);
            prixtotal = (TextView) itemView.findViewById(R.id.prixTotalFourniture);
        }


        public void display (ParseObject Fourniture){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            int nb= Fourniture.getInt(NBFournitureAPI.COLUMN_NOMBRE);
            int pu = Fourniture.getParseObject(NBFournitureAPI.COLUMN_FOURNITURE).getInt(FournitureAPI.COLUMN_PRIX);
            nombre.setText(Integer.toString(nb));
            nom.setText(Fourniture.getParseObject(NBFournitureAPI.COLUMN_FOURNITURE).getString(FournitureAPI.COLUMN_NOM));
            prix.setText(Integer.toString(pu));
            prixtotal.setText(Integer.toString(nb*pu));

        }
    }
}
