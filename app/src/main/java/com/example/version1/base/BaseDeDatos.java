package com.example.version1.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.version1.roles.Comprador;
import com.example.version1.roles.Usuario;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final String name = "registrar.bd";
    private static final int version = 1;

    private static final String tableusuarios= "Usuarios";
    private static final String column_id_usuarios="id";
    private static final String column_id_nombre="nombre";
    private static final String column_id_gmail="gmail";
    private static final String column_id_cedula= "cedula";
    private static final String column_id_telefono="telefono";
    private static final String column_id_contraseña="contraseña";
    private static final String column_id_saldo = "saldo";
    private static final String column_id_cantidad="limite";
    private static final String column_id_fecha="fecha";


    private static final String tablecompras= "compras";
    private static final String column_id_id="idcom";
    private static final String column_id_comprador="comprador";
    private static final String column_id_gmailcom="gmailcom";
    private static final String column_id_cedulacom="cedulacom";
    private static final String column_id_telefonocom="telefonocom";
    private static final String column_id_juego="juego";
    private static final String column_id_costo="costo";



    public BaseDeDatos(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUsuario =
                "Create table " + tableusuarios +
                        "(" + column_id_usuarios + " INTEGER primary key AUTOINCREMENT, " +
                        column_id_nombre + " text, " +
                        column_id_gmail + " text unique, " +
                        column_id_cedula + " text, " +
                        column_id_telefono + " text,  " +
                        column_id_contraseña+ " text unique, " +
                        column_id_saldo + " text,  " +
                        column_id_cantidad + " text,  " +
                        column_id_fecha  + " text);";
        db.execSQL(queryUsuario);


        String queryComprador =
                "Create table " + tablecompras +
                        "(" +  column_id_id + " INTEGER primary key AUTOINCREMENT, " +
                        column_id_comprador + " text, " +
                        column_id_gmailcom + " text, " +
                        column_id_cedulacom + " text, " +
                        column_id_telefonocom+ " text, " +
                        column_id_juego + " text,  " +
                        column_id_costo  + " text);";
        db.execSQL(queryComprador);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableusuarios);
    }

    public boolean addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_id_nombre, usuario.getNombre());
        cv.put(column_id_gmail, usuario.getGmail());
        cv.put(column_id_cedula, usuario.getCedula());
        cv.put(column_id_telefono, usuario.getTelefono());
        cv.put(column_id_contraseña, usuario.getContraseña());
        cv.put(column_id_saldo, usuario.getSaldo());
        cv.put(column_id_cantidad, usuario.getLimite());
        cv.put(column_id_fecha, usuario.getFecha());

        long rest = db.insert(tableusuarios, null, cv);
        if (rest == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean addComprador(Comprador comprador) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_id_comprador, comprador.getComprador());
        cv.put(column_id_gmailcom, comprador.getGmailcom());
        cv.put(column_id_cedulacom, comprador.getCedulacom());
        cv.put(column_id_telefonocom, comprador.getTelefonocom());
        cv.put(column_id_juego, comprador.getJuego());
        cv.put(column_id_costo, comprador.getCosto());

        long rest = db.insert(tablecompras, null, cv);
        if (rest == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean validarUsuario(String gmail, String contraseña) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableusuarios + " WHERE " + column_id_gmail + " = '" + gmail + "' and "
                + column_id_contraseña + " = '" + contraseña + "' ";
        Usuario usuario = new Usuario();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                usuario.setNombre(cursor.getString(1));
                usuario.setGmail(cursor.getString(2));
                usuario.setCedula(cursor.getString(3));
                usuario.setTelefono(cursor.getString(4));
                usuario.setContraseña(cursor.getString(5));
                usuario.setSaldo(cursor.getString(6));
            }
            if (usuario.getGmail()  != null) {
                return contraseña.equals(usuario.getContraseña());
            }
        }
        return false;
    }


    public Usuario leergmail(String gmail) {

        String query = "SELECT * FROM " + tableusuarios + " WHERE " + column_id_gmail + " = '" + gmail + "' ";
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = null;
        Usuario usuario = null;
        if (bd != null) {
            cursor = bd.rawQuery(query, null);
            while (cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setNombre(cursor.getString(1));
                usuario.setGmail(cursor.getString(2));
                usuario.setCedula(cursor.getString(3));
                usuario.setTelefono(cursor.getString(4));
                usuario.setContraseña(cursor.getString(5));
                usuario.setSaldo(cursor.getString(6));
                usuario.setLimite(cursor.getString(7));
                usuario.setFecha(cursor.getString(8));
            }
        }
        return usuario;
    }



    public Comprador leergmailcom(String gmail) {

        String query = "SELECT * FROM " + tablecompras + " WHERE " + column_id_gmailcom + " = '" + gmail + "' ";
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = null;
        Comprador comprador = null;
        if (bd != null) {
            cursor = bd.rawQuery(query, null);
            while (cursor.moveToNext()) {
                comprador = new Comprador();
                comprador.setComprador(cursor.getString(1));
                comprador.setGmailcom(cursor.getString(2));
                comprador.setCedulacom(cursor.getString(3));
                comprador.setTelefonocom(cursor.getString(4));
                comprador.setJuego(cursor.getString(5));
                comprador.setCosto(cursor.getString(6));

            }
        }
        return comprador;
    }

    public ArrayList<Comprador> leercompras(String gmail) {
        ArrayList<Comprador> lista = new ArrayList<>();
        String query =
                "SELECT * FROM " + tablecompras + " WHERE " + column_id_gmailcom + " = '" + gmail + "'" + " ORDER BY " +  column_id_id + " DESC " ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Comprador comprador = new Comprador();
                comprador.setComprador(cursor.getString(1));
                comprador.setGmailcom(cursor.getString(2));
                comprador.setCedulacom(cursor.getString(3));
                comprador.setTelefonocom(cursor.getString(4));
                comprador.setJuego(cursor.getString(5));
                comprador.setCosto(cursor.getString(6));
                lista.add(comprador);
            }
        }
        return lista;
    }


    public void actualizarSaldoUsuario (String lis_resu, String gmail) {
        SQLiteDatabase db =
                this.getReadableDatabase();
        String query =
                "UPDATE " + tableusuarios + " SET " + column_id_saldo + " = '" + lis_resu + "'"
                        + " WHERE  " + column_id_gmail + " = '" + gmail + "'";
        db.execSQL(query);
    }


    public void actualizarlimiteUsuario (String resultado, String gmail) {
        SQLiteDatabase db =
                this.getReadableDatabase();
        String query =
                "UPDATE " + tableusuarios + " SET " + column_id_cantidad + " = '" + resultado + "'"
                        + " WHERE  " + column_id_gmail + " = '" + gmail + "'";
        db.execSQL(query);
    }


    public void RecargarSaldoUsuario (String gmail) {
        SQLiteDatabase db =
                this.getReadableDatabase();
        String query =
                "UPDATE " + tableusuarios + " SET " + column_id_saldo + " = '" + 1000000 + "'"
                        + " WHERE  " + column_id_gmail + " = '" + gmail + "'";
        db.execSQL(query);
    }



    public void RecargarlimiteUsuario (String gmail) {
        SQLiteDatabase db =
                this.getReadableDatabase();
        String query =
                "UPDATE " + tableusuarios + " SET " + column_id_cantidad + " = '" + 4 + "'"
                        + " WHERE  " + column_id_gmail + " = '" + gmail + "'";
        db.execSQL(query);
    }


    public void actualizrfecha (String fechas2 , String gmail) {
        SQLiteDatabase db =
                this.getReadableDatabase();
        String query =
                "UPDATE " + tableusuarios + " SET " + column_id_fecha + " = '" + fechas2 + "'"
                        + " WHERE  " + column_id_gmail + " = '" + gmail + "'";
        db.execSQL(query);
    }
}
