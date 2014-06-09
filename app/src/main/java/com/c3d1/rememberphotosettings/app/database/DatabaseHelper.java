package com.c3d1.rememberphotosettings.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String	DATABASE_NAME			= "PhotoSettings";
    private static final int	DATABASE_VERSION		= 3;
    public static final String	TABLE_PHOTO_NAME		= "Photos";
    public static final String	COL_PHOTO_ID			= "photos_id";
	public static final String	COL_PHOTO_TITLE			= "Title";
    public static final String	COL_PHOTO_FILMID		= "Film_id";
    public static final String	COL_PHOTO_APERTURE		= "Aperture";
    public static final String	COL_PHOTO_SHUTTER		= "Shutter";
    public static final String	COL_PHOTO_MONOCHROM		= "Monochrom";
    public static final String	COL_PHOTO_LENS		    = "Lens";
	public static final String	COL_PHOTO_DESCRIPTION   = "Description";

	public static final String	TABLE_FILM_NAME			= "Film";
	public static final String	COL_FILM_ID				= "film_id";
	public static final String	COL_FILM_HERSTELLER		= "Hersteller";
	public static final String	COL_FILM_ISO			= "ISO";
	public static final String	COL_FILM_PHOTOS			= "Photos";
	public static final String	COL_FILM_DESCRIPTION	= "Description";
	public static final String	COL_FILM_SW				= "SW";

    private static final String	DATABASE_PHOTO_CREATE	=
            "CREATE TABLE " + TABLE_PHOTO_NAME + " ( "
            + COL_PHOTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_PHOTO_TITLE + " TEXT NOT NULL,  "
            + COL_PHOTO_FILMID + " INTEGER NOT NULL, "
            + COL_PHOTO_APERTURE + " TEXT NOT NULL, "
            + COL_PHOTO_SHUTTER + " TEXT NOT NULL, "
            + COL_PHOTO_MONOCHROM + " INTEGER, "
            + COL_PHOTO_LENS + " TEXT, "
			+ COL_PHOTO_DESCRIPTION + " TEXT "
            + ")";

	private static final String DATABASE_FILM_CREATE =
			"CREATE TABLE " + TABLE_FILM_NAME + " ( "
			+ COL_FILM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COL_FILM_HERSTELLER + " TEXT NOT NULL, "
			+ COL_FILM_ISO + " INTEGER NOT NULL, "
			+ COL_FILM_PHOTOS + " INTEGER NOT NULL, "
			+ COL_FILM_DESCRIPTION + " TEXT, "
			+ COL_FILM_SW + "INTEGER "
			+ " ) ";

    private Context				mMyContext;

    public DatabaseHelper( Context context )
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
        mMyContext = context;
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( DATABASE_PHOTO_CREATE );
		db.execSQL( DATABASE_FILM_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        Log.w( SQLiteOpenHelper.class.getName(), "Upgraiding database " + DATABASE_NAME + "from Version "
                + oldVersion + " to " + newVersion + ". Old Data will be destroyed" );

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_PHOTO_NAME );
        onCreate( db );
    }
}
