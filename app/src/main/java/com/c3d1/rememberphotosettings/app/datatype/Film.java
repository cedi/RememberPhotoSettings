package com.c3d1.rememberphotosettings.app.datatype;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.c3d1.rememberphotosettings.app.database.DatabaseHelper;

public class Film
{
	private long           m_lID;
	private String         m_sHersteller;
	private int            m_nISO;
	private int            m_nPictures;
	private String         m_sDescription;
	private boolean        m_bSW;
	private SQLiteDatabase m_MyDatabase;

	public long getID()
	{
		return m_lID;
	}

	public void setID( long lID )
	{
		this.m_lID = lID;
	}

	public String getHersteller()
	{
		return m_sHersteller;
	}

	public void setHersteller( String sHersteller )
	{
		this.m_sHersteller = sHersteller;
	}

	public int getISO()
	{
		return m_nISO;
	}

	public void setISO( int nISO )
	{
		this.m_nISO = nISO;
	}

	public int getPictures()
	{
		return m_nPictures;
	}

	public void setPictures( int nPictures )
	{
		this.m_nPictures = nPictures;
	}

	public String getDescription()
	{
		return m_sDescription;
	}

	public void setDescription( String sDescription )
	{
		this.m_sDescription = sDescription;
	}

	public boolean isSW()
	{
		return m_bSW;
	}

	public void setSW( boolean bSW )
	{
		this.m_bSW = bSW;
	}

	public boolean getFilm( long lID )
	{
		if( m_MyDatabase == null || !m_MyDatabase.isOpen() )
		{
			return false;
		}

		String[] asColumns = new String[] { DatabaseHelper.COL_FILM_ID, DatabaseHelper.COL_FILM_ISO,
											DatabaseHelper.COL_FILM_PHOTOS, DatabaseHelper.COL_FILM_DESCRIPTION,
											DatabaseHelper.COL_FILM_SW };
		String[] asParams = new String[] { "" + lID };

		Cursor cursor = m_MyDatabase
				.query( DatabaseHelper.TABLE_FILM_NAME, asColumns, DatabaseHelper.COL_PHOTO_ID + "= ?", asParams, null,
						null, null );

		// das erste Ergebnis holen
		cursor.moveToFirst();

		// falls kein record da ist stimmt was nicht
		if( cursor.getCount() == 0 )
		{
			return false;
		}

		// Sollte nur ein Eintrag zurück liefern sonst stimmt was nicht
		if( cursor.getCount() > 1 )
		{
			return false;
		}

		// das erste Ergebnis holen
		cursor.moveToFirst();

		setID( cursor.getLong( 0 ) );
		setISO( cursor.getInt( 1 ) );
		setPictures( cursor.getInt( 2 ) );
		setDescription( cursor.getString( 3 ) );
		setSW( cursor.getInt( 4 ) == 0 ? false : true );

		return true;
	}

	public boolean saveFilm()
	{
		if( m_MyDatabase == null || !m_MyDatabase.isOpen() )
		{
			return false;
		}

		ContentValues values = new ContentValues();
		values.put( DatabaseHelper.COL_FILM_HERSTELLER, getHersteller() );
		values.put( DatabaseHelper.COL_FILM_ISO, getISO() );
		values.put( DatabaseHelper.COL_FILM_PHOTOS, getPictures() );
		values.put( DatabaseHelper.COL_FILM_DESCRIPTION, getDescription() );
		values.put( DatabaseHelper.COL_FILM_SW, isSW() ? 1 : 0 );

		setID( m_MyDatabase.insert( DatabaseHelper.TABLE_FILM_NAME, null, values ) );

		if( getID() > -1 )
		{
			return true;
		}

		return false;
	}
}
