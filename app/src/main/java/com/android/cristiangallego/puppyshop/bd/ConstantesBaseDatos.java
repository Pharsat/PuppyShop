package com.android.cristiangallego.puppyshop.bd;

/**
 * Created by USUARIO on 16/04/2017.
 */

public class ConstantesBaseDatos {
    public static final String DATABASE_NAME = "Mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA = "Mascota";
    public static final String TABLE_MASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_NOMBRE = "nombre";
    public static final String TABLE_MASCOTA_FOTOPRINCIPAL = "idFotoMascota";
    public static final String TABLE_MASCOTA_RAITING = "raiting";
    public static final String TABLE_MASCOTA_MEGUSTA = "meGusta";

    public static final String TABLE_FOTOMASCOTA = "FotoMascota";
    public static final String TABLE_FOTOMASCOTA_ID = "id";
    public static final String TABLE_FOTOMASCOTA_NROFOTO = "nroFoto";
    public static final String TABLE_FOTOMASCOTA_LIKES = "likes";

    public static final String TABLE_MASCOTA_FOTOMASCOTA = "MascotaXFotoMascota";
    public static final String TABLE_MASCOTA_FOTOMASCOTA_ID = "id";
    public static final String TABLE_MASCOTA_FOTOMASCOTA_ID_MASCOTA = "idMascota";
    public static final String TABLE_MASCOTA_FOTOMASCOTA_ID_FOTOMASCOTA = "idFotoMascota";

}
