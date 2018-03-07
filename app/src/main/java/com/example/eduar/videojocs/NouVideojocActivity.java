package com.example.eduar.videojocs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NouVideojocActivity extends Activity implements View.OnClickListener {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou_videojoc);
        Button b = (Button) findViewById(R.id.botoAcceptar);
        b.setOnClickListener(this);
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nou_videojoc, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }

@Override
public void onClick(View view) {
        Intent i = new Intent();
        Bundle b = new Bundle();

        EditText nom = (EditText) findViewById(R.id.txtNom);
        EditText desenvolupador = (EditText) findViewById(R.id.txtDesenvolupador);
        EditText genere = (EditText) findViewById(R.id.txtGenere);
        EditText jugador = (EditText) findViewById(R.id.txtJugador);
        EditText edat = (EditText) findViewById(R.id.txtEdat);

        Joc t = new Joc(nom.getText().toString(), desenvolupador.getText().toString(),genere.getText().toString(),jugador.getText().toString(),edat.getText().toString());
        b.putSerializable("dades",t);
        i.putExtras(b);
        setResult(RESULT_OK, i);
        finish();
        }

        }
