package com.example.version1.adaptadores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.version1.MainActivity;
import com.example.version1.R;
import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Comprador;
import com.example.version1.roles.Usuario;
import com.example.version1.ventas;

public class Descripcion extends AppCompatActivity {


    /*grupo*/
    public TextView tvSaldoo, tvnombree;
    TextView et_1;
    TextView et_2;
    String vv;
    TextView et_3;
    TextView et_4;
    TextView et_5;
    TextView resul;
    TextView tvnombre;
    TextView tvgmail;
    TextView ll;
    TextView lis_resu;
    String gmail;
    String saldo_restante;
    String limite_restante;

    int saldo_rest ;
    TextView tvcedula;
    TextView tvtelefono;
    TextView tvsaldo;
    Usuario usuario;



    /*grupo*/


    ImageView portada;
    Button bt_cpmprar, atras;
    BaseDeDatos bd = new BaseDeDatos(this);
    Comprador comprador;






    /* objetos*/

    private static final long TIEMPO_MINIMO = 3000;
    private long ultimoClick = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);


        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");


        bd = new BaseDeDatos(this);
        comprador = new Comprador();
        usuario = new Usuario();
        usuario = bd.leergmail(gmail);







        /*grupo*/
        et_1 = findViewById(R.id.et_nombre_de_dsp);
        et_2 = findViewById(R.id.et_empresa_de_dsp);
        et_3 = findViewById(R.id.comofue);
        et_4 = findViewById(R.id.et_Valor);
        et_5 = findViewById(R.id.et_total);
        portada = findViewById(R.id.portada);
        tvSaldoo = findViewById(R.id.tvSaldo);
        resul = findViewById(R.id.tvresultado);
        tvnombre = findViewById(R.id.tvnombre);
        tvgmail = findViewById(R.id.gmail_comprador);
        lis_resu= findViewById(R.id.lis_resu);
        tvcedula = findViewById(R.id.cedula_comprador);
        ll = findViewById(R.id.lis);
        tvtelefono = findViewById(R.id.telefono_comprador);
        tvsaldo = findViewById(R.id.saldo_comprador);
        atras = findViewById(R.id.atras);




        bt_cpmprar = findViewById(R.id.bt_comprar);
        bt_cpmprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                ultimoClick = SystemClock.elapsedRealtime();

                String saldo = tvsaldo.getText().toString();

                String valorjj = et_4.getText().toString();

                int intsaldo = Integer.parseInt(saldo);
                int costo_juego = Integer.parseInt(valorjj);

                if (intsaldo >= costo_juego) {

                    String total_cpmpras = ll.getText().toString();
                    int total_cpmprass = Integer.parseInt(total_cpmpras);

                    if (total_cpmprass > 0) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(Descripcion.this);
                        builder.setMessage("¿Quieres comprar este juego?").setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                comprador.setComprador(tvnombre.getText().toString().trim());
                                comprador.setGmailcom(tvgmail.getText().toString().trim());
                                comprador.setCedulacom(tvcedula.getText().toString().trim());
                                comprador.setTelefonocom(tvtelefono.getText().toString().trim());
                                comprador.setJuego(et_1.getText().toString().trim());
                                comprador.setCosto(et_4.getText().toString().trim());



                                gmail = tvgmail.getText().toString();


                                bd.actualizarSaldoUsuario(saldo_restante, gmail);
                                bd.actualizarlimiteUsuario(limite_restante, gmail);




                                if (bd.addComprador(comprador)) {



                                    Toast.makeText(getApplicationContext(), "Juego comprado!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Descripcion.this, ventas.class);
                                    i.putExtra("gmail", gmail);
                                    startActivity(i);
                                    finish();
                                }


                            }
                        })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.show();


                    } else {

                        Toast.makeText(getApplicationContext(), "No puedes realizar más de 4 comprás al día!!", Toast.LENGTH_SHORT).show();


                    }

                }else {

                    Toast.makeText(getApplicationContext(), "Sin saldó", Toast.LENGTH_SHORT).show();


                }
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gmail = tvgmail.getText().toString();
                Intent i = new Intent(Descripcion.this, ventas.class);
                i.putExtra("gmail", gmail );
                startActivity(i);
                finish();

            }
        });


        portada.setImageResource(element.getPortada());
        et_1.setText(element.getNombre());
        et_2.setText(element.getEmpresa());
        et_3.setText(element.getJ());
        et_4.setText(element.getPresio());
        et_5.setText(element.getPresio());
        tvnombre.setText(element.getNombreusu());
        tvgmail.setText(element.getGmailcom());
        tvcedula.setText(element.getCedulacom());
        tvtelefono.setText(element.getTelefonocom());
        ll.setText(element.getEl_limite());
        tvsaldo.setText(element.getCosto());





        /*esto es del saldo*/
        String saldo = tvsaldo.getText().toString();
        String valorjj = et_4.getText().toString();


        int intsaldo = Integer.parseInt(saldo);
        int costo_juego = Integer.parseInt(valorjj);

        int resta = intsaldo - costo_juego;

        String resu = String.valueOf(resta);

        saldo_restante = String.valueOf(resta);

        resul.setText(resu);

        int saldio= 1000000;

        vv=  String.valueOf(saldio);

        /*esto es del saldo*/







        /*esto es del limite*/

        String limitee = ll.getText().toString();

        int intlimitee = Integer.parseInt(limitee);

        int restas = intlimitee - 1;

        String resultado = String.valueOf(restas);

        lis_resu.setText(resultado);

        limite_restante= String.valueOf(restas);

        /*esto es del limite*/




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        gmail = tvgmail.getText().toString();
        Intent i = new Intent(Descripcion.this, ventas.class);
        i.putExtra("gmail", gmail );
        startActivity(i);
        finish();

    }



}