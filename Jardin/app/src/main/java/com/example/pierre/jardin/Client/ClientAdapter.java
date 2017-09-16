package com.example.pierre.jardin.Client;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 16/09/2017.
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private ArrayList<ParseObject> listClients;

    public ClientAdapter(ArrayList<ParseObject> listClients) {
        this.listClients=listClients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listClients.get(position));
    }

    @Override
    public int getItemCount() {
        return listClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomClient);
        }





        public void display(ParseObject client){


            nom.setText(client.getString(ClientAPI.COLUMN_NOM));

        }
    }
}