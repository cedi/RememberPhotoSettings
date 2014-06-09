package com.c3d1.rememberphotosettings.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.c3d1.rememberphotosettings.app.datatype.Film;


public class AddFilmActivity extends Activity
{

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_add_film );
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.add_film, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch( id )
		{
			case R.id.action_save:
				SaveFilm();
				finish();
				return true;
		}

		return super.onOptionsItemSelected( item );
	}

	private void SaveFilm()
	{
		EditText etHersteller = ( EditText )findViewById( R.id.etHersteller );
		EditText etISO = ( EditText )findViewById( R.id.etISO );
		EditText etPictures = ( EditText )findViewById( R.id.etPictures );
		EditText etDescriptin = ( EditText )findViewById( R.id.etDescription );
		Switch swMonochrom = ( Switch )findViewById( R.id.swMonochrom );

		Film MyFilm = new Film();
		MyFilm.saveFilm();

		// User benachrichtigen
		Toast.makeText( getApplicationContext(), "Erfolgreich gespeichert", Toast.LENGTH_SHORT ).show();
	}
}