package com.example.version1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version1.adaptadores.Descripcion;
import com.example.version1.adaptadores.ListAdapter;
import com.example.version1.adaptadores.ListElement;
import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ventas extends AppCompatActivity {

    public static String ss;


    TextView tvSaldo, tvnombre;
    String gmail;
    String saldooo;
    BaseDeDatos bd;
    Usuario usuario;
    int valor=1;
    ImageButton compras;
    List<ListElement> elements;


    /* para la base de datos cpmprador*/


    String comprador;
    String gmailcomp;
    String cedulacomp;
    String telefonocomp;
    String saldo;
    String fecha;
    String fechas2;
    String limites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        usuario = new Usuario();
        bd = new BaseDeDatos(this);


        gmail = getIntent().getStringExtra("gmail");



        saldooo = getIntent().getStringExtra("saldooooo");
        usuario = bd.leergmail(gmail);


        tvSaldo = findViewById(R.id.tvSaldo);
        tvnombre = findViewById(R.id.tv_nombre);
        compras = findViewById(R.id.compras);



        tvnombre.setText(usuario.getNombre());
        tvSaldo.setText(usuario.getSaldo());





        comprador = usuario.getNombre();
        gmailcomp = usuario.getGmail();
        cedulacomp = usuario.getCedula();
        telefonocomp = usuario.getTelefono();
        limites = usuario.getLimite();
        saldo = usuario.getSaldo();
        fecha = usuario.getFecha();



      /*  Date date= new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("d'/'M'/'yyyy");
        String fecha2= fecha.format(date);


        fechas2= fecha2;



        if (fecha.equals(fechas2)){

            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        }else{
            bd.RecargarSaldoUsuario(gmail);

            bd.RecargarlimiteUsuario( gmail);
        }
*/



        compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ventas.this, historial.class);
                gmail = getIntent().getStringExtra("gmail");
                i.putExtra("gmail", gmail);
                startActivity(i);

            }
        });


        init();
    }


    public void init() {


        elements = new ArrayList<>();

        elements.add(new ListElement(R.drawable.halo, "Halo Infinite", "Activision Blizzard.", "600000"
                , "Halo Infinite es un videojuego " +
                "de disparos en primera persona de la franquicia de videojuegos de ciencia ficción creada " +
                "por Bungie Studios y actualmente desarrollada por 343 Industries. Es exclusivo para las plataf" +
                "ormas Xbox One, Microsoft Windows y Xbox", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));
        elements.add(new ListElement(R.drawable.forzahorizon, "Forza Horizon 5", "Take-Two Interactive.", "180000"
                , "Forza Horizon 5 es un " +
                "videojuego de carreras multijugador en linea desarrollado " +
                "por Playground Games y publicado por Xbox Game Studios. Es el quinto título de Forza Horizon y la" +
                " duodécima entrega principal de la serie Forza. El juego está ambientado en una representación ficticia de México", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));


        elements.add(new ListElement(R.drawable.bioshock2, "BioShock 2 ", "Electronic Arts.", "200000"
                , "BioShock 2 es un videojuego de terror " +
                "y de disparos en primera persona, " +
                "desarrollado por 2K Marin, y la segunda parte y secuela de BioShock.", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));


        elements.add(new ListElement(R.drawable.minevraf, "Minecraft ", "Nintendo", "220000"
                , "Minecraft es un videojuego de construcción de tipo " +
                "«mundo abierto» o sandbox creado originalmente por el sueco Markus Persson,y posteriormente desarrollado por Mojang Studios", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));


        elements.add(new ListElement(R.drawable.gta, "Grand Theft Auto", "Xbox Game Studios", "165000"
                , "Grand Theft Auto es una serie de videojuegos " +
                "creada por David Jones y por los hermanos Sam y Dan Houser. Originalmente fue desarrollada por la compañía británica DMA Design, que posteriormente pasó a llamarse " +
                "Rockstar North, de la empresa Rockstar Games", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));


        elements.add(new ListElement(R.drawable.fornite, "Fortnite", "Square Enix", "168000"
                , "Fortnite es un videojuego del año 2017 desarrollado por la empresa " +
                "Epic Games, lanzado como diferentes paquetes de software que presentan diferentes modos de juego, pero que comparten el mismo motor de juego y mecánicas. Fue anunciado en los " +
                "Spike Video Game Awards en 2011", comprador, gmailcomp, cedulacomp, telefonocomp, saldo, limites));

        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

                moveToDescription(item);

            }
        });
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);


    }


    public void moveToDescription(ListElement item) {


        Intent intent = new Intent(this, Descripcion.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres Cerrar Sesion ?").setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }





}