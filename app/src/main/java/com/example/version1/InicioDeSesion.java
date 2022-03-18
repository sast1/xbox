package com.example.version1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InicioDeSesion extends AppCompatActivity {


    BaseDeDatos bd = new BaseDeDatos(this);
    Usuario usuario = new Usuario();
    EditText et_gmail, et_Contraseña;
    Button bt_acceder, atras;
    String fechaa;
    String fechas2;

    private static final long TIEMPO_MINIMO = 3000;
    private long ultimoClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);


        et_gmail = findViewById(R.id.et_gmail);
        et_Contraseña = findViewById(R.id.et_Contraseña);
        bt_acceder = findViewById(R.id.bt_verificar);
        atras = findViewById(R.id.atras);






        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        bt_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                ultimoClick = SystemClock.elapsedRealtime();


                if (et_gmail.length() >= 26 || et_Contraseña.length() >= 13) {

                    Toast.makeText(InicioDeSesion.this, "No Puedes Exceder Los Carácteres Indicados", Toast.LENGTH_SHORT).show();

                } else if (validar(et_gmail.getText().toString()) == false) {

                    Toast.makeText(InicioDeSesion.this, "CORREO NO VÁLIDO", Toast.LENGTH_SHORT).show();

                } else if (et_gmail.getText().toString().equals("") && et_Contraseña.getText().toString().equals("")) {
                    Toast.makeText(InicioDeSesion.this, "No Pueden tener los campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    if (bd.validarUsuario(et_gmail.getText().toString(), et_Contraseña.getText().toString())) {


                        String gmail = et_gmail.getText().toString();
                        String contraseña = et_Contraseña.getText().toString();
                        usuario.setGmail(gmail);
                        usuario = bd.leergmail(gmail);
                        fechaa = usuario.getFecha();

                        Date date= new Date();
                        SimpleDateFormat fecha= new SimpleDateFormat("d'/'M'/'yyyy");
                        String fecha2= fecha.format(date);


                        fechas2= fecha2;



                        if (fechaa.equals(fechas2)){

                            Toast.makeText( InicioDeSesion.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        }else{

                            Date datee= new Date();
                            SimpleDateFormat fechaaa= new SimpleDateFormat("d'/'M'/'yyyy");
                            String fecha3= fechaaa.format(datee);

                            fechas2= fecha3;
                            bd.RecargarSaldoUsuario(gmail);
                            bd.actualizrfecha(fechas2, gmail);
                            bd.RecargarlimiteUsuario( gmail);
                        }


                        usuario.setContraseña(contraseña);
                        Intent bienvenida = new Intent(InicioDeSesion.this, ventas.class);
                        bienvenida.putExtra("gmail", gmail);
                        Toast.makeText(InicioDeSesion.this, "Inicio de sesión éxitoso", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(bienvenida);
                    } else {
                        Toast.makeText(InicioDeSesion.this, "Inicio de sesión Fallido", Toast.LENGTH_SHORT).show();

                    }


                }


            }


        });

    }


    boolean validar(CharSequence gmail) {

        return Patterns.EMAIL_ADDRESS.matcher(gmail).matches();


    }

}