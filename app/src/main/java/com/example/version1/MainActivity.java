package com.example.version1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.version1.base.BaseDeDatos;
import com.example.version1.roles.Usuario;

public class MainActivity extends AppCompatActivity {

    Button bt_ir_a_registro, bt_acceder;
    EditText et_gmail, et_Contraseña;
    BaseDeDatos bd = new BaseDeDatos(this);
    Usuario usuario = new Usuario();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        et_gmail = findViewById(R.id.et_gmail);
        et_Contraseña = findViewById(R.id.et_Contraseña);
        bt_ir_a_registro = findViewById(R.id.bt_Ir_A_Registro);
        bt_acceder = findViewById(R.id.INICIAR_SESION);


        bt_ir_a_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, Registro.class);
                startActivity(registro);

            }
        });

        bt_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent INICIARSESION = new Intent(MainActivity.this, InicioDeSesion.class);
                startActivity(INICIARSESION);

            }
        });





    }
}