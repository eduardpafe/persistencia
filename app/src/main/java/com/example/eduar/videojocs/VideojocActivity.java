package com.example.eduar.videojocs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.eduar.videojocs.DB.Constants;
import com.example.eduar.videojocs.DB.Dialeg;
import com.example.eduar.videojocs.DB.VideojocSQLiteHelper;

public class VideojocActivity extends Activity {
    private VideojocSQLiteHelper sqLiteHelper;
    private SimpleCursorAdapter adapter;
    private VideojocConversor videojocConversor;
    private ListView llista;
    private Cursor dades;
    private ActionMode mActionMode;
    private ActionMode.Callback implementActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojoc);

        sqLiteHelper = new VideojocSQLiteHelper(getBaseContext(), Constants.DB_FILE_NAME, null, Constants.DB_VERSION);
        videojocConversor = new VideojocConversor(sqLiteHelper);

        // crear l'adapter
        llista = ((ListView) findViewById(R.id.listJocs));
        refreshData();

        llista.setSelected(true);
        llista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        llista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean checked) {

                // Capture total checked items
                final int checkedCount = llista.getCheckedItemCount();
                // Set the title according to total checked items
                actionMode.setTitle(checkedCount + " seleccionats");

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.contextual_listview, menu);
                return true;            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete:
                        esborrarSeleccionats();
                        return true;
                }
                return false;            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

    }

    private void esborrarSeleccionats() {
        SparseBooleanArray seleccionats = llista.getCheckedItemPositions();
        for (int i = seleccionats.size() - 1; i >= 0; i--) {
            if (seleccionats.valueAt(i)) {
                videojocConversor.removeById((int) adapter.getItemId(i));
            }
        }
        refreshData();
    }



    private void refreshData() {
        String[] columnes = new String[]{"nom","desenvolupador","genere","jugador","edat"};
        int[] views = new int[]{R.id.txtNom, R.id.txtDesenvolupador, R.id.txtGenere, R.id.txtJugador, R.id.txtEdat};

        dades = videojocConversor.getAll();
        adapter = new SimpleCursorAdapter(getBaseContext(),R.layout.llista_jocs,dades,
                columnes, views,0);
        llista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.videojocs, menu);
        return true;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        Dialeg dialeg = new Dialeg();

        dialeg.setMissatge(((Joc) adapter.getItem(position)).getNom() + "\n" + ((Joc) adapter.getItem(position)).getDesenvolupador());
        dialeg.setNom("Videojoc seleccionat");
        dialeg.show(getFragmentManager(), "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: afegirJoc(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void afegirJoc() {
        Intent i = new Intent(getBaseContext(),NouVideojocActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Joc t = (Joc)data.getExtras().getSerializable("dades");
            videojocConversor.save(t);
            refreshData();
        }
    }

}