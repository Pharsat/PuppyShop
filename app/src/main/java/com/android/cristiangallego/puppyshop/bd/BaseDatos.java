package com.android.cristiangallego.puppyshop.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.cristiangallego.puppyshop.R;
import com.android.cristiangallego.puppyshop.pojo.FotoMascota;
import com.android.cristiangallego.puppyshop.pojo.Mascota;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by USUARIO on 16/04/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaFotoMascota = MessageFormat.format("CREATE TABLE {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT,{2} INTEGER,{3} INTEGER)"
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO);

        String queryCrearTablaMascota = MessageFormat.format("CREATE TABLE {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT,{2} INTEGER,{3} INTEGER,{4} INTEGER,{5} TEXT)"
                , ConstantesBaseDatos.TABLE_MASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_ID
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOPRINCIPAL
                , ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA
                , ConstantesBaseDatos.TABLE_MASCOTA_RAITING
                , ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE);

        String queryCrearTablaMascotaXFotoMascota = MessageFormat.format("CREATE TABLE {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT,{2} INTEGER,{3} INTEGER)"
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA);

        db.execSQL(queryCrearTablaFotoMascota);
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaMascotaXFotoMascota);

        CrearDatosDummy(R.drawable.juancho, db, "Juancho");
        CrearDatosDummy(R.drawable.azulado, db, "Perron Ojoz Azules");
        CrearDatosDummy(R.drawable.hqdefault, db, "Firulais");
        CrearDatosDummy(R.drawable.hqdefault_dos, db, "Lukas");
        CrearDatosDummy(R.drawable.hqdefault_tres, db, "Salchicha");
        CrearDatosDummy(R.drawable.hqdefault_uno, db, "Bruno");
        CrearDatosDummy(R.drawable.peluche, db, "Azard");
        CrearDatosDummy(R.drawable.labrador, db, "Rolo");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_FOTOMASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> otenerListaPrincipal() {
        String query = MessageFormat.format("SELECT M.{0},M.{1},M.{2},M.{3},FM.{4} AS id2,FM.{5},FM.{6},M.{11} FROM {7} AS M INNER JOIN {8} FM ON M.{9} = FM.{10}"
                , ConstantesBaseDatos.TABLE_MASCOTA_ID
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOPRINCIPAL
                , ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA
                , ConstantesBaseDatos.TABLE_MASCOTA_RAITING
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO
                , ConstantesBaseDatos.TABLE_MASCOTA
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOPRINCIPAL
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID
                , ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        ArrayList<Mascota> resultado = new ArrayList<>();
        while (registros.moveToNext()) {
            Mascota actual = new Mascota();
            actual.setId(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_ID)));
            actual.setFotoPrincipalMascota(new FotoMascota(
                    Integer.valueOf(registros.getString(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO)))
                    , Integer.valueOf(registros.getString(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES)))
                    , Integer.valueOf(registros.getString(registros.getColumnIndex("id2")))));
            actual.setNombre(registros.getString(registros.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE)));
            actual.setFotosSecundarias(null);
            actual.setMeGusta(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA)) == 1);
            actual.setRating(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_MASCOTA_RAITING)));
            actual.setFotosSecundarias(new ArrayList<FotoMascota>());
            resultado.add(actual);
        }

        db.close();
        return resultado;
    }

    public ArrayList<FotoMascota> obtenerFotosMascotas(int idMascota) {
        String query = MessageFormat.format("SELECT FM.{0}, FM.{1} FROM {2} FM INNER JOIN {3} MFM ON MFM.{4} = FM.{5} WHERE MFM.{6} = {7}"
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID
                , ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA
                , String.valueOf(idMascota));

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        ArrayList<FotoMascota> resultado = new ArrayList<>();
        while (registros.moveToNext()) {
            FotoMascota actual = new FotoMascota();
            actual.setNroFoto(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO)));
            actual.setNroLikes(registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES)));
            resultado.add(actual);
        }

        db.close();
        return resultado;
    }

    public void darLike(int idMascota, boolean like, int idFotoPrincipal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if (like) {
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA, 1);
        } else {
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA, 0);
        }

        String where = MessageFormat.format("{0}={1}", ConstantesBaseDatos.TABLE_MASCOTA_ID, String.valueOf(idMascota));
        db.update(ConstantesBaseDatos.TABLE_MASCOTA, contentValues, where, null);

        String query = MessageFormat.format("SELECT FM.{0}, FM.{4} FROM {1} FM WHERE FM.{2} = {3}"
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID
                , String.valueOf(idFotoPrincipal)
                , ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID);

        Cursor registros = db.rawQuery(query, null);
        int nroLikes = 0;
        int idFotoMascota = 0;
        while (registros.moveToNext()) {
            nroLikes = registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES));
            idFotoMascota = registros.getInt(registros.getColumnIndex(ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID));
        }

        contentValues = new ContentValues();
        if (like) {
            contentValues.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, nroLikes + 1);
        } else {
            contentValues.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, nroLikes - 1);
        }
        where = MessageFormat.format("{0}={1}", ConstantesBaseDatos.TABLE_FOTOMASCOTA_ID, String.valueOf(idFotoMascota));
        db.update(ConstantesBaseDatos.TABLE_FOTOMASCOTA, contentValues, where, null);

        db.close();
    }

    private void CrearDatosDummy(int foto, SQLiteDatabase db, String mascota) {
        Long[] fotosSecundarias = new Long[5];
        ContentValues contentValuesFotosSecundarias = new ContentValues();
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 15);
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        fotosSecundarias[0] = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotosSecundarias);

        contentValuesFotosSecundarias = new ContentValues();
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 9);
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        fotosSecundarias[1] = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotosSecundarias);

        contentValuesFotosSecundarias = new ContentValues();
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 8);
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        fotosSecundarias[2] = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotosSecundarias);

        contentValuesFotosSecundarias = new ContentValues();
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 21);
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        fotosSecundarias[3] = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotosSecundarias);

        contentValuesFotosSecundarias = new ContentValues();
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 6);
        contentValuesFotosSecundarias.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        fotosSecundarias[4] = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotosSecundarias);

        ContentValues contentValuesFotoMascotaPrincipal = new ContentValues();
        contentValuesFotoMascotaPrincipal.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_LIKES, 9);
        contentValuesFotoMascotaPrincipal.put(ConstantesBaseDatos.TABLE_FOTOMASCOTA_NROFOTO, foto);
        Long idFotoMascotaPrincipal = db.insert(ConstantesBaseDatos.TABLE_FOTOMASCOTA, null, contentValuesFotoMascotaPrincipal);

        ContentValues contentValuesMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOPRINCIPAL, idFotoMascotaPrincipal);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_MEGUSTA, 0);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_RAITING, 0);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, mascota);
        Long idMascota = db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValuesMascota);

        ContentValues contentValuesMascotaXFotoMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA, fotosSecundarias[0]);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA, idMascota);
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA, null, contentValuesMascotaXFotoMascota);

        contentValuesMascotaXFotoMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA, fotosSecundarias[1]);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA, idMascota);
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA, null, contentValuesMascotaXFotoMascota);

        contentValuesMascotaXFotoMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA, fotosSecundarias[2]);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA, idMascota);
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA, null, contentValuesMascotaXFotoMascota);

        contentValuesMascotaXFotoMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA, fotosSecundarias[3]);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA, idMascota);
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA, null, contentValuesMascotaXFotoMascota);

        contentValuesMascotaXFotoMascota = new ContentValues();
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA, fotosSecundarias[4]);
        contentValuesMascota.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA, idMascota);
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_FOTOMASCOTA, null, contentValuesMascotaXFotoMascota);
    }
}
