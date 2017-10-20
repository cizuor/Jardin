package com.example.pierre.jardin.Employer;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.EmployerAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 01/10/2017.
 */

public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.ViewHolder> {


    private ArrayList<ParseObject> listEmployer;

    public EmployerAdapter(ArrayList<ParseObject> listEmployer) {
        this.listEmployer = listEmployer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_employer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listEmployer.get(position));
    }

    @Override
    public int getItemCount() {
        return listEmployer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        private ParseObject employer;
        public ViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomEmployer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEmployer(itemView);
                }
            });
        }



        public void display (ParseObject Employer){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            employer=Employer;
            nom.setText(Employer.getString(EmployerAPI.COLUMN_NOM));
        }



        private void onClickEmployer(View itemView){
            Intent myIntent = new Intent(itemView.getContext(),EmployerDetail.class);
            myIntent.putExtra("Employer", employer); //Optional parameters
            itemView.getContext().startActivity(myIntent);
        }
    }

}
