package com.c3d1.rememberphotosettings.app.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.c3d1.rememberphotosettings.app.R;
import com.c3d1.rememberphotosettings.app.database.DatabaseHelper;
import com.c3d1.rememberphotosettings.app.datatype.Photo;

import java.util.List;

public class OverviewAdapter extends BaseAdapter
{
	private Context        m_MyContext;
	private DatabaseHelper m_MyDatabaseHelper;
	private SQLiteDatabase m_MyDatabase;

	public OverviewAdapter( Context MyContext )
	{
		m_MyContext = MyContext;
		m_MyDatabaseHelper = new DatabaseHelper( m_MyContext );
		m_MyDatabase = m_MyDatabaseHelper.getWritableDatabase();
	}

	@Override
	public Photo getItem( int nPos )
	{
		Photo MyPhoto = new Photo( m_MyDatabase );
		if( MyPhoto.getPhoto( nPos ) )
		{
			return MyPhoto;
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getCount()
	{
		Cursor cursor = m_MyDatabase.rawQuery( "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_PHOTO_NAME, null );

		if( cursor.moveToFirst() )
		{
			return cursor.getInt( 0 );
		}
		else
		{
			return -1;
		}

		//return m_aPhotos.size();
	}

	@Override
	public long getItemId( int nPos )
	{
		return nPos;
	}

	@Override
	public View getView( int nPos, View convertView, ViewGroup parent )
	{
		// convertView anlegen falls nicht eh schon geschehen
		if( convertView == null )
		{
			convertView = LayoutInflater.from( m_MyContext ).inflate( R.layout.overview_listitem, parent, false );
		}

		do
		{
			// überhaupt einträge da?
			if( getCount() <= 0 )
			{
				break;
			}

			// Photo Objekt anlegen
			Photo MyPhoto = new Photo( m_MyDatabase );

			// Und aus der DB holen mit der eindeutigen ID
			if( !MyPhoto.getPhoto( nPos ) )
			{
				break;
			};

			// Die TextViews holen und bestücken
			TextView tvTitle = ( TextView )convertView.findViewById( R.id.tvTitle );
			TextView tvISO = ( TextView )convertView.findViewById( R.id.tvISO );
			TextView tvShutter = ( TextView )convertView.findViewById( R.id.tvShutter );
			TextView tvAperture = ( TextView )convertView.findViewById( R.id.tvAperture );
			TextView tvLens = ( TextView )convertView.findViewById( R.id.tvLens );

			tvTitle.setText( MyPhoto.getTitle() );
			tvISO.setText( MyPhoto.getISO() );
			tvShutter.setText( MyPhoto.getShutter() );
			tvAperture.setText( MyPhoto.getAperture() );
			tvLens.setText( MyPhoto.getLens() );

			if( MyPhoto.getLens().isEmpty() )
				tvLens.setVisibility( View.GONE );

		} while( false );

		return convertView;
	}
}
