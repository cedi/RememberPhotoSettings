package com.c3d1.rememberphotosettings.app;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.c3d1.rememberphotosettings.app.adapter.ApertureAdapter;
import com.c3d1.rememberphotosettings.app.adapter.ShutterAdapter;
import com.c3d1.rememberphotosettings.app.database.DatabaseHelper;
import com.c3d1.rememberphotosettings.app.datatype.Photo;


public class AddPhotoActivity extends Activity
{
	private ApertureAdapter m_MyApertureAdapter;
	private SpinnerAdapter  m_MyShutterAdapter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_add_photo );

		initSpinner();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.add_photo, menu );
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
			{
				savePhoto();
				finish();
			}
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

	protected void initSpinner()
	{
		Spinner spAperture = ( Spinner )findViewById( R.id.spAperture );
		Spinner spShutter = ( Spinner )findViewById( R.id.spShutter );

		m_MyApertureAdapter = new ApertureAdapter();
		m_MyShutterAdapter = new ShutterAdapter();

		spAperture.setAdapter( m_MyApertureAdapter );
		spShutter.setAdapter( m_MyShutterAdapter );
	}

	protected void savePhoto()
	{
		// Elemente holen
		EditText etTitle = ( EditText )findViewById( R.id.etTitel );
		EditText etISO = ( EditText )findViewById( R.id.etISO );
		TextView tvAperture = ( TextView )findViewById( R.id.tvAperture );
		TextView tvShutter = ( TextView )findViewById( R.id.tvShutter );
		Switch swMonochrom = ( Switch )findViewById( R.id.swMonochrom );
		EditText etLens = ( EditText )findViewById( R.id.etLens );
		EditText etDescription = ( EditText )findViewById( R.id.etDescription );

		// Foto Objekt erstellen
		Photo MyPhoto = new Photo();
		MyPhoto.setTitle( etTitle.getText().toString() );
		MyPhoto.setFilmID( 0 );
		MyPhoto.setAperture( tvAperture.getText().toString() );
		MyPhoto.setShutter( tvShutter.getText().toString() );
		MyPhoto.setMonochrom( swMonochrom.isChecked() );
		MyPhoto.setLens( etLens.getText().toString() );
		MyPhoto.setDescription( etDescription.getText().toString() );

		// Datenbank erstellen
		DatabaseHelper MyDatabaseHelper = new DatabaseHelper( getApplicationContext() );
		SQLiteDatabase MyDatabase = MyDatabaseHelper.getWritableDatabase();

		// In die Datenbank einfügen
		MyPhoto.saveFilm();

		// User benachrichtigen
		Toast.makeText( getApplicationContext(), "Erfolgreich gespeichert", Toast.LENGTH_SHORT ).show();
	}

}
