package com.example.pierre.jardin.Client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Button;

import com.example.pierre.jardin.R;
import com.example.pierre.jardin.api.ClientAPI;

public class PageClient extends AppCompatActivity {


    private Button gNouveauClient;
    private android.widget.SearchView gSearchViewClient;
    private RecyclerView gRecyclerViewClient;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager gLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_client);
        gNouveauClient = (Button) findViewById(R.id.newClientButton);
        gSearchViewClient = (android.widget.SearchView) findViewById(R.id.SearchClient);
        gRecyclerViewClient = (RecyclerView) findViewById(R.id.recyclerviewclient);
        gRecyclerViewClient.setHasFixedSize(true);

        // use a linear layout manager
        gLayoutManager = new LinearLayoutManager(this);
        gRecyclerViewClient.setLayoutManager(gLayoutManager);

        // specify an adapter (see also next example)
        ClientAPI clientAPI = new ClientAPI();
        gAdapter = new ClientAdapter(clientAPI.getClients());
        gRecyclerViewClient.setAdapter(gAdapter);

    }


}
