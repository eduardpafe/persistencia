package com.example.eduar.videojocs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by eduar on 25/02/2018.
 */

public class VideojocAdapter extends ArrayAdapter<Joc> {
    //Classe que guarda les referències als elements de la vista

    class Vista {
        public TextView nom;
        public TextView desenvolupador;
        public TextView genere;
        public TextView jugador;
        public TextView edat;
    }

    private Joc[] dades;

    VideojocAdapter(Activity context, Joc[] dades) {
        super(context, R.layout.llista_jocs, dades);
        this.dades = dades;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;
        Vista vista;

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.llista_jocs, null);

            vista = new Vista();
            vista.nom = (TextView) element.findViewById(R.id.txtNom);
            vista.desenvolupador = (TextView) element.findViewById(R.id.txtDesenvolupador);
            vista.genere = (TextView) element.findViewById(R.id.txtGenere);
            vista.edat = (TextView) element.findViewById(R.id.txtEdat);
            vista.jugador = (TextView) element.findViewById(R.id.txtJugador);

            element.setTag(vista);
        } else {
            vista = (Vista) element.getTag();
        }

        vista.nom.setText(dades[position].getNom());
        vista.desenvolupador.setText(dades[position].getDesenvolupador());
        vista.genere.setText(dades[position].getGenere());
        vista.edat.setText(dades[position].getEdat());
        vista.jugador.setText(dades[position].getJugador());

        return element;
    }

}
