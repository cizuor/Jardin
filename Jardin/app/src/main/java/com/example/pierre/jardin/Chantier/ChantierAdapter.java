package com.example.pierre.jardin.Chantier;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pierre.jardin.Facture.FactureAdapter;
import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ChantierAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pierre on 27/09/2017.
 */

public class ChantierAdapter extends RecyclerView.Adapter<ChantierAdapter.ViewHolder> {


    private ArrayList<ParseObject> listChantier = new ArrayList<ParseObject>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

    public ChantierAdapter(ArrayList<ParseObject> listChantier) {
        this.listChantier = listChantier;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_chantier, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.dispay(listChantier.get(position));
    }

    @Override
    public int getItemCount() {
        return listChantier.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nom;
        private TextView date;
        private TextView nbEmployer;
        private TextView nbHeur;
        private TextView fourniture;
        private LinearLayout ligneChantier;
        private ChantierAPI chantierAPI;
        private ParseObject chantier;

        public ViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom_chantier);
            date = (TextView) itemView.findViewById(R.id.dateChantier);
            nbEmployer = (TextView) itemView.findViewById(R.id.nbEmployerChantier);
            nbHeur = (TextView) itemView.findViewById(R.id.nbHeurChantier);
            fourniture = (TextView) itemView.findViewById(R.id.fournitureChantier);
            ligneChantier = (LinearLayout) itemView.findViewById(R.id.ligneChantier);
            chantierAPI = new ChantierAPI();
            ligneChantier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickChantier(itemView);
                }
            });
        }




        public void dispay(ParseObject chantier){
            this.chantier=chantier;
            if (getAdapterPosition()%2==0){
                ligneChantier.setBackgroundColor(0xFFFFFFFF);
            }
            nom.setText(chantier.getString(ChantierAPI.COLUMN_NOM));
            date.setText(sdf.format(chantier.getDate(ChantierAPI.COLUMN_DATE)));
            nbHeur.setText(Integer.toString(chantier.getInt(ChantierAPI.COLUMN_NBHEURS)));
            nbEmployer.setText(Integer.toString(chantierAPI.getNBEmployer(chantier)));
            if (chantierAPI.getFourniture(chantier).size()!=0){
                fourniture.setText("fourniture");
            }else {
                fourniture.setText("sans");
            }
        }

        private void onClickChantier(View itemView){
            Intent myIntent = new Intent(itemView.getContext(),ChantierDetail.class);
            myIntent.putExtra("Chantier", chantier); //Optional parameters
            itemView.getContext().startActivity(myIntent);
        }

    }
}
