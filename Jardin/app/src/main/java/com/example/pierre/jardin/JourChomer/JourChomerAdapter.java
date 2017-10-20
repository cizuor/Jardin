package com.example.pierre.jardin.JourChomer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.JourChomerAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pierre on 04/10/2017.
 */

public class JourChomerAdapter extends RecyclerView.Adapter<JourChomerAdapter.ViewHolder>{

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    private ArrayList<ParseObject> listJourChomer;

    public JourChomerAdapter(ArrayList<ParseObject> listJourChomer) {
        this.listJourChomer = listJourChomer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_jour_chomer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listJourChomer.get(position));
    }

    @Override
    public int getItemCount() {
        return listJourChomer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dateDebut;
        private TextView dateFin;
        private TextView raison;
        private ParseObject jourChomer;
        public ViewHolder(View itemView) {
            super(itemView);
            dateDebut = (TextView) itemView.findViewById(R.id.adapterJourChomerDateDebut);
            dateFin = (TextView) itemView.findViewById(R.id.adapterJourChomerDateFin);
            raison = (TextView) itemView.findViewById(R.id.adapterJourChomerRaison);

        }

        public void display (ParseObject jourChomer){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            this.jourChomer = jourChomer;
            dateDebut.setText(sdf.format(jourChomer.getDate(JourChomerAPI.COLUMN_DATEDEBUT)));
            dateFin.setText(sdf.format(jourChomer.getDate(JourChomerAPI.COLUMN_DATEFIN)));
            if (jourChomer.getBoolean(JourChomerAPI.COLUMN_ISMALADE)){
                raison.setText("Maladie");
            }else if(jourChomer.getBoolean(JourChomerAPI.COLUMN_ISVACANCE)){
                raison.setText("Vacance");
            }else {
                raison.setText("Sans raison ");
            }
        }
    }
}
