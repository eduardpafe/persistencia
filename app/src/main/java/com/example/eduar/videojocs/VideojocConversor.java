package com.example.eduar.videojocs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eduar.videojocs.DB.VideojocSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by eduar on 25/02/2018.
 */

public class VideojocConversor {
    private VideojocSQLiteHelper helper;

    /**
     * Consructor per defecte
     */
    public VideojocConversor() {

    }

    /**
     * Constructor amb paràmetres
     * @param helper l'ajudant de la BD de Titulars
     */
    public VideojocConversor(VideojocSQLiteHelper helper) {
        this.helper = helper;
    }

    /**
     * Desa un nou titular a la taula
     * @param joc l'objecte a desar
     * @return l'id del nou titular desat
     */
    public long save(Joc joc) {
        long index = -1;
        // s'agafa l'objecte base de dades en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();
        // es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir
        ContentValues dades = new ContentValues();

        dades.put("nom", joc.getNom());
        dades.put("desenvolupador", joc.getDesenvolupador());
        dades.put("genere", joc.getGenere());
        dades.put("jugador", joc.getJugador());
        dades.put("edat", joc.getEdat());
        try {
            index = db.insertOrThrow("Jocs", null, dades);
            // exemple: volem veure en el log el que passa
            Log.i("Jocs", dades.toString() + " afegit amb codi " + index);
        }
        catch(Exception e) {
            // volem reflectir en ellog que hi ha hagut un error
            Log.e("Jocs", e.getMessage());
        }
        return index;
    }

    /**
     * Retorna un cursor amb totes les dades de la taula
     * @return
     */
    public Cursor getAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        return db.query(true, "Jocs",
                new String[]{"_id","nom","desenvolupador","genere","jugador","edat"},
                null, null, null, null, null, null);
    }

    /**
     * Obtenir tots els videojocs en forma de llista
     * @return la llista que conté tots els videojocs
     */
    public ArrayList<Joc> getAllAsList() {
        ArrayList<Joc> llista = new ArrayList<Joc>();
        Cursor c = getAll();
        Joc t = null;

        while(c.moveToNext()) {
            t = new Joc(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5));
            llista.add(t);
        }

        return llista;
    }

    /**
     * Obtenir un titular a partir del seu id (primary key)
     * @param id el codi de titular que es vol obtenir
     * @return el titular que coincideix amb l'id introduit o bé null si no el troba
     */
    public Joc getById(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Joc joc= null;
        Cursor c = db.query(true, "Jocs",
                new String[]{"_id","Nom","Desenvolupador","Gènere","Jugadors","Edat"},
                "_id = ?", new String[]{id+""}, null, null, null, null);

        if(c.moveToNext()) {
            joc =  new Joc(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5));
        }
        return joc;
    }
    /**
     * Esborra el titular passat per paràmetre
     * @param id el codi titular a esborrar
     * @return la quantitat de videojocs eliminats
     */
    public boolean removeById(int id) {
        // obtenir l'objecte BD en mode esriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Jocs", "_id=" + id,null ) > 0;
    }
    /**
     * Esborra tots els videojocs de la taula
     * @return
     */
    public boolean removeAll() {
        // obtenir l'objecte BD en mode escriptura
        SQLiteDatabase db = helper.getWritableDatabase();

        return db.delete("Jocs", null, null ) > 0;
    }
}