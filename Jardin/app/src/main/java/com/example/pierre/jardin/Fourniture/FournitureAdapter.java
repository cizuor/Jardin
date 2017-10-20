package com.example.pierre.jardin.Fourniture;

import android.content.Intent;
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

public class FournitureAdapter extends RecyclerView.Adapter<FournitureAdapter.ViewHolder> {


    private ArrayList<ParseObject> listFourniture;

    public FournitureAdapter(ArrayList<ParseObject> listFourniture) {
        this.listFourniture = listFourniture;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_fourniture, parent, false);
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
        private ParseObject fourniture;
        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomFourniture);
            prix = (TextView) itemView.findViewById(R.id.prixFourniture);
        }


        public void display (ParseObject Fourniture){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            this.fourniture=Fourniture;
            int pu = Fourniture.getInt(FournitureAPI.COLUMN_PRIX);
            nom.setText(Fourniture.getString(FournitureAPI.COLUMN_NOM));
            prix.setText(Integer.toString(pu));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickFourniture(itemView);
                }
            });

        }

        private void onClickFourniture(View itemView){
            Intent myIntent = new Intent(itemView.getContext(),FournitureDetail.class);
            myIntent.putExtra("Fourniture",fourniture ); //Optional parameters
            itemView.getContext().startActivity(myIntent);
        }
    }
}
