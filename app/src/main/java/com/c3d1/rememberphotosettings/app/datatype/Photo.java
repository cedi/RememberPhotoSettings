package com.c3d1.rememberphotosettings.app.datatype;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import com.c3d1.rememberphotosettings.app.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Photo
{

	private long           m_lID;
	private String         m_sTitle;
	private long           m_lFilmID;
	private String         m_sAperture;
	private String         m_sShutter;
	private boolean        m_bMonochrom;
	private String         m_sLens;
	private String         m_sDescription;
	private SQLiteDatabase m_MyDatabase;

	public Photo()
	{
		this( null );
	}

	public Photo( SQLiteDatabase MyDatabase )
	{
		m_MyDatabase = MyDatabase;
	}

	public long getID()
	{
		return m_lID;
	}

	private void setID( long lID )
	{
		this.m_lID = lID;
	}

	public long getFilmID()
	{
		return m_lFilmID;
	}

	public void setFilmID( long lFilmID )
	{
		this.m_lFilmID = lFilmID;
	}

	public String getAperture()
	{
		return m_sAperture;
	}

	public void setAperture( String sAperture )
	{
		m_sAperture = sAperture;
	}

	public String getShutter()
	{
		return m_sShutter;
	}

	public void setShutter( String sShutter )
	{
		m_sShutter = sShutter;
	}

	public boolean isMonochrom()
	{
		return m_bMonochrom;
	}

	public void setMonochrom( boolean bMonocrhom )
	{
		m_bMonochrom = bMonocrhom;
	}

	public String getLens()
	{
		return m_sLens;
	}

	public void setLens( String sLens )
	{
		m_sLens = sLens;
	}

	public String getTitle()
	{
		return m_sTitle;
	}

	public void setTitle( String sTitle )
	{
		m_sTitle = sTitle;
	}

	public String getDescription()
	{
		return m_sDescription;
	}

	public void setDescription( String sDescription )
	{
		m_sDescription = sDescription;
	}

	public int getISO()
	{
		Cursor cursor = m_MyDatabase.rawQuery(
				"SELECT " + DatabaseHelper.COL_FILM_PHOTOS + " FROM " + DatabaseHelper.TABLE_FILM_NAME + ", " +
				DatabaseHelper.TABLE_PHOTO_NAME + " WHERE " + DatabaseHelper.COL_FILM_ID + " == " +
				DatabaseHelper.COL_PHOTO_ID, null
											 );

		if( !cursor.moveToFirst() )
		{
			return -1;
		}

		return cursor.getInt( 0 );
	}

	public boolean saveFilm()
	{
		// Database vorhanden?
		if( m_MyDatabase == null || !m_MyDatabase.isOpen() )
		{
			return false;
		}

		ContentValues values = new ContentValues();
		values.put( DatabaseHelper.COL_PHOTO_TITLE, getTitle() );
		values.put( DatabaseHelper.COL_PHOTO_FILMID, getFilmID() );
		values.put( DatabaseHelper.COL_PHOTO_APERTURE, getAperture() );
		values.put( DatabaseHelper.COL_PHOTO_SHUTTER, getShutter() );
		values.put( DatabaseHelper.COL_PHOTO_MONOCHROM, isMonochrom() );
		values.put( DatabaseHelper.COL_PHOTO_LENS, getLens() );
		values.put( DatabaseHelper.COL_PHOTO_DESCRIPTION, getDescription() );

		setID( m_MyDatabase.insert( DatabaseHelper.TABLE_PHOTO_NAME, null, values ) );

		if( getID() > -1 )
		{
			return true;
		}

		return false;
	}

	public boolean getPhoto( long lID )
	{
		if( m_MyDatabase == null || !m_MyDatabase.isOpen() )
		{
			return false;
		}

		String[] asColumns = new String[] { DatabaseHelper.COL_PHOTO_ID, DatabaseHelper.COL_PHOTO_TITLE,
											DatabaseHelper.COL_PHOTO_FILMID, DatabaseHelper.COL_PHOTO_APERTURE,
											DatabaseHelper.COL_PHOTO_SHUTTER, DatabaseHelper.COL_PHOTO_MONOCHROM,
											DatabaseHelper.COL_PHOTO_LENS, DatabaseHelper.COL_PHOTO_DESCRIPTION };
		String[] asParams = new String[] { "" + lID };

		Cursor cursor = m_MyDatabase
				.query( DatabaseHelper.TABLE_PHOTO_NAME, asColumns, DatabaseHelper.COL_PHOTO_ID + "= ?", asParams, null,
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
		setTitle( cursor.getString( 1 ) );
		setFilmID( cursor.getLong( 2 ) );
		setAperture( cursor.getString( 3 ) );
		setShutter( cursor.getString( 4 ) );
		setMonochrom( cursor.getInt( 5 ) == 0 ? false : true );
		setLens( cursor.getString( 6 ) );
		setDescription( cursor.getString( 7 ) );

		return true;
	}

	public static List<Photo> getPhotos( SQLiteDatabase MyDatabase )
	{
		ArrayList<Photo> aPhotos = new ArrayList<Photo>();

		String[] asColumns = new String[] { DatabaseHelper.COL_PHOTO_ID, DatabaseHelper.COL_PHOTO_TITLE,
											DatabaseHelper.COL_PHOTO_FILMID, DatabaseHelper.COL_PHOTO_APERTURE,
											DatabaseHelper.COL_PHOTO_SHUTTER, DatabaseHelper.COL_PHOTO_MONOCHROM,
											DatabaseHelper.COL_PHOTO_LENS, DatabaseHelper.COL_PHOTO_DESCRIPTION };
		String sOrderBy = DatabaseHelper.COL_PHOTO_ID;

		Cursor cursor = MyDatabase
				.query( DatabaseHelper.TABLE_PHOTO_NAME, asColumns, null, null, null, null, sOrderBy );

		if( cursor.moveToFirst() )
		{
			Photo MyPhoto = new Photo();

			do
			{
				MyPhoto.setID( cursor.getLong( 0 ) );
				MyPhoto.setTitle( cursor.getString( 1 ) );
				MyPhoto.setFilmID( cursor.getLong( 2 ) );
				MyPhoto.setAperture( cursor.getString( 3 ) );
				MyPhoto.setShutter( cursor.getString( 4 ) );
				MyPhoto.setMonochrom( cursor.getInt( 5 ) == 0 ? false : true );
				MyPhoto.setLens( cursor.getString( 6 ) );
				MyPhoto.setDescription( cursor.getString( 7 ) );

				aPhotos.add( MyPhoto );

			} while( cursor.moveToNext() );
		}

		return aPhotos;
	}
}
