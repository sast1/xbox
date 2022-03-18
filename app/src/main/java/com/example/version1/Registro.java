package com.example.version1;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends AppCompatActivity {
    BaseDeDatos bd = new BaseDeDatos(this);

    EditText nombre, gmail, cedula, telefono, contraseña, segundacontraseña, saldo;
    Usuario usuario;

    Button bt_registrar, atras;


    private static final long TIEMPO_MINIMO = 3000;
    private long ultimoClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        usuario = new Usuario();
        nombre = findViewById(R.id.et_nombre);
        gmail = findViewById(R.id.et_gmail);
        cedula = findViewById(R.id.et_cedula);
        telefono = findViewById(R.id.et_telefono);
        contraseña = findViewById(R.id.et_contraseña);
        segundacontraseña= findViewById(R.id.et_contraseña2);
        bt_registrar = findViewById(R.id.bt_registrar);
        atras = findViewById(R.id.atras);

        Date date= new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("d'/'M'/'yyyy");
        String fechas= fecha.format(date);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                ultimoClick = SystemClock.elapsedRealtime();

                if (nombre.getText().toString().equals("") || cedula.getText().toString().equals("") ||
                        gmail.getText().toString().equals("") || telefono.getText().toString().equals("") || contraseña.getText().toString().equals("")
                        || segundacontraseña.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No puedes tener campos vacíos", Toast.LENGTH_SHORT).show();
                } else if (validar(gmail.getText().toString()) == false) {

                    Toast.makeText(Registro.this, "CORREO NO VALIDO", Toast.LENGTH_SHORT).show();

                } else if (nombre.getText().length() < 3) {

                    Toast.makeText(Registro.this, "El nombre debe tener mas de 3 carácteres ", Toast.LENGTH_SHORT).show();
                } else if (cedula.getText().length() < 8) {

                    Toast.makeText(Registro.this, "La cedula debe tener mas de 8 dijitos", Toast.LENGTH_SHORT).show();
                } else if (telefono.getText().length() < 10) {

                    Toast.makeText(Registro.this, "El numero telefonico debe tener mas de 10 dijitos", Toast.LENGTH_SHORT).show();
                } else if (contraseña.getText().length() < 4) {

                    Toast.makeText(Registro.this, "La contraseña debe tener mas de 4 carácteres", Toast.LENGTH_SHORT).show();


                } else if (contraseña.getText().toString().equals(segundacontraseña.getText().toString())){


                    usuario.setNombre(nombre.getText().toString().trim());
                    usuario.setGmail(gmail.getText().toString().trim());
                    usuario.setCedula(cedula.getText().toString().trim());
                    usuario.setTelefono(telefono.getText().toString().trim());
                    usuario.setContraseña(contraseña.getText().toString().trim());
                    usuario.setSaldo("1000000");
                    usuario.setLimite("4");
                    usuario.setFecha(fechas);

                    if (bd.addUsuario(usuario)) {
                        Toast.makeText(getApplicationContext(), "Usuario agregado con éxito!", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Fallo al agregar el usuario!", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(Registro.this, "La contraseñas deben coincidir", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    boolean validar(CharSequence gmail) {

        return Patterns.EMAIL_ADDRESS.matcher(gmail).matches();


    }
}