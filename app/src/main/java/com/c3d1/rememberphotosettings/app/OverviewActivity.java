package com.c3d1.rememberphotosettings.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.c3d1.rememberphotosettings.app.adapter.OverviewAdapter;
import com.c3d1.rememberphotosettings.app.database.DatabaseHelper;


public class OverviewActivity extends Activity
{
	private OverviewAdapter m_MyAdapter;
	private ListView        m_MyListView;
	private DatabaseHelper  m_MyDatabaseHelper;
	private SQLiteDatabase  m_MyDatabase;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_overview );

		initDatabase();
		initOverviewList();

		( ( BaseAdapter )m_MyAdapter ).notifyDataSetChanged();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.overview, menu );
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
			case R.id.action_add_photo:
			{
				startActivity( new Intent( this, AddPhotoActivity.class ) );
			}
			return true;

			case R.id.action_add_film:
			{
				startActivity( new Intent( this, AddFilmActivity.class ) );
			}
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

	/**
	 * Liste initalisieren
	 */
	void initOverviewList()
	{
		m_MyListView = ( ListView )findViewById( R.id.listview );

		m_MyAdapter = new OverviewAdapter( getApplicationContext() );
		m_MyListView.setAdapter( m_MyAdapter );
	}

	void initDatabase()
	{
		m_MyDatabaseHelper = new DatabaseHelper( getApplicationContext() );
		m_MyDatabase = m_MyDatabaseHelper.getWritableDatabase();
	}
}