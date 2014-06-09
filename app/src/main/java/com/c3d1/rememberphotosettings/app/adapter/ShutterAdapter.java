package com.c3d1.rememberphotosettings.app.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by cedric on 29.05.14.
 */
public class ShutterAdapter implements SpinnerAdapter
{
	private ArrayList<String> m_aShutter;

	public ShutterAdapter()
	{
		m_aShutter.add( "2" );
		m_aShutter.add( "1" );
		m_aShutter.add( "1/2" );
		m_aShutter.add( "1/4" );
		m_aShutter.add( "1/8" );
		m_aShutter.add( "1/16" );
		m_aShutter.add( "1/32" );
		m_aShutter.add( "1/60" );
		m_aShutter.add( "1/125" );
		m_aShutter.add( "1/250" );
		m_aShutter.add( "1/500" );
		m_aShutter.add( "1/1000" );
	}

	@Override
	public View getDropDownView( int position, View convertView, ViewGroup parent )
	{
		return null;
	}

	@Override
	public void registerDataSetObserver( DataSetObserver observer )
	{

	}

	@Override
	public void unregisterDataSetObserver( DataSetObserver observer )
	{

	}

	@Override
	public int getCount()
	{
		return m_aShutter.size();
	}

	@Override
	public Object getItem( int nPos )
	{
		return m_aShutter.get( nPos );
	}

	@Override
	public long getItemId( int nPos )
	{
		return -1;
	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public View getView( int nPos, View convertView, ViewGroup parent )
	{
		return null;
	}

	@Override
	public int getItemViewType( int nPos )
	{
		return 0;
	}

	@Override
	public int getViewTypeCount()
	{
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}
}
