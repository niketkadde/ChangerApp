package com.nik.changerapp;

import java.util.ArrayList;

import android.app.Application;

import com.nik.changerapp.incidentmanager.Incidents;

/**
 * Android Application class, a singleton class for access of data through out
 * the application.
 * 
 * @author Niket K
 */

public class ChangerApplication extends Application
{
	public ArrayList<Incidents> mIncidentsArrayList = null;

	public void onCreate()
	{
		super.onCreate();
	}
}
