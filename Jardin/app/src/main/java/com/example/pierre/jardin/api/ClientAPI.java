package com.example.pierre.jardin.api;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierre on 16/09/2017.
 */

public class ClientAPI {
    public static final String TABLE_CLIENT = "Client";
    public static final String COLUMN_NOM = "Nom";





    public ArrayList<ParseObject> getClients (){
        /*ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList );
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });*/
        ParseQuery<ParseObject > query = ParseQuery.getQuery("Client");
        ArrayList<ParseObject> listClients = new ArrayList<ParseObject>();
        try {
            listClients = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listClients;
    }
}
