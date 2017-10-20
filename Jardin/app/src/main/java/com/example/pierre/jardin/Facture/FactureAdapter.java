package com.example.pierre.jardin.Facture;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.FactureAPI;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pierre on 21/09/2017.
 */

public class FactureAdapter extends RecyclerView.Adapter<FactureAdapter.ViewHolder> {



    private ArrayList<ParseObject> listFacture;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    public FactureAdapter(ArrayList<ParseObject> listFacture) {
        this.listFacture=listFacture;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_facture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listFacture.get(position));
    }

    @Override
    public int getItemCount() {
        return listFacture.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        private TextView date;
        private TextView prix;
        private LinearLayout lignefacture;
        private ParseObject facture;
        public ViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomFacture);
            date = (TextView) itemView.findViewById(R.id.dateFacture);
            prix = (TextView) itemView.findViewById(R.id.prixFacture);
            lignefacture = (LinearLayout) itemView.findViewById(R.id.linearadapteurfacture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickFacture(itemView);
                }
            });
        }





        public void display(ParseObject facture){
            if (facture.getBoolean(FactureAPI.COLUMN_PAYER)){
                lignefacture.setBackgroundColor(0xFF08DF01);
            }else {
                lignefacture.setBackgroundColor(0xFFFA0000);
            }
            this.facture = facture;
            nom.setText(facture.getString(FactureAPI.COLUMN_NOM));
            date.setText(sdf.format(facture.getDate(FactureAPI.COLUMN_DATE)));
            prix.setText(Integer.toString(facture.getInt(FactureAPI.COLUMN_PRIX)));

        }



        private void onClickFacture(View itemView){
            Intent myIntent = new Intent(itemView.getContext(),FactureDetail.class);
            myIntent.putExtra("Facture", facture); //Optional parameters
            itemView.getContext().startActivity(myIntent);
        }

    }
}
