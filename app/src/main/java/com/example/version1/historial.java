package com.example.version1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.version1.adaptadores.adaptadorHistorial;
import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Comprador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class historial extends AppCompatActivity {


    String gmail;
    BaseDeDatos bd;
    ArrayList<Comprador> listaArrayListComprador;
    Button atras;


    RecyclerView listaComponentes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        gmail = getIntent().getStringExtra("gmail");
        bd = new BaseDeDatos(this);

        listaComponentes=findViewById(R.id.listaComponentes);
        listaComponentes.setLayoutManager(new LinearLayoutManager(this));

        listaArrayListComprador = new ArrayList<>();

        adaptadorHistorial adapter= new adaptadorHistorial(bd.leercompras(gmail));
        listaComponentes.setAdapter(adapter);


        atras=findViewById(R.id.atrass);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }
}