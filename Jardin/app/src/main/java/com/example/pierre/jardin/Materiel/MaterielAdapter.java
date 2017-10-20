package com.example.pierre.jardin.Materiel;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pierre.jardin.Client.ClientDetail;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 16/09/2017.
 */

public class MaterielAdapter extends RecyclerView.Adapter<MaterielAdapter.ViewHolder> {

    private ArrayList<ParseObject> listMateriel;

    public MaterielAdapter(ArrayList<ParseObject> listMateriel) {
        this.listMateriel=listMateriel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_materiel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listMateriel.get(position));
    }

    @Override
    public int getItemCount() {
        return listMateriel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        private ParseObject materiel;
        public ViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomMateriel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickMateriel(itemView);
                }
            });
        }





        public void display(ParseObject materiel){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            this.materiel = materiel;
            nom.setText(materiel.getString(ClientAPI.COLUMN_NOM));

        }



        private void onClickMateriel(View itemView){
            Intent myIntent = new Intent(itemView.getContext(),MaterielDetail.class);
            myIntent.putExtra("Materiel",materiel ); //Optional parameters
            itemView.getContext().startActivity(myIntent);
        }

    }
}
