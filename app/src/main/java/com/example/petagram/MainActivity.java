package com.example.petagram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout sfiMiIndicadorRefresh;
    ListView lstmiLista;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        agregarFAB();

        //Encontrar el id del SwipeRefresh
        sfiMiIndicadorRefresh = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicadorRefresh);
        //Encontrar el id de la lista
        lstmiLista = (ListView) findViewById(R.id.lstMiLista);

        //crear un array con lista de strings
        String[] planetas = getResources().getStringArray(R.array.planetas);

        //Adaptar la lista
        lstmiLista.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,planetas));
        //Cuando se resetea, estar atento a un reseteo
        sfiMiIndicadorRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescandoContenido();
            }
        });
    }

    public void refrescandoContenido(){
        String[] planetas = getResources().getStringArray(R.array.planetas);
        lstmiLista.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,planetas));
        sfiMiIndicadorRefresh.setRefreshing(false);
    }

    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabMiFab);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje), Toast.LENGTH_SHORT).show();
                Snackbar.make(view, getResources().getString(R.string.mensaje),Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.texto_accion), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("SNACKBAR","Click en Snackbar");
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            }
        });
    }

}