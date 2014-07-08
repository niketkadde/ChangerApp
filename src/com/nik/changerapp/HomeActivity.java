package com.nik.changerapp;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.nik.changerapp.incidentmanager.IncidentManager;
import com.nik.changerapp.incidentmanager.Incidents;

/**
 * HomeActivity is the landing screen of the app, shows list of incidents parsed
 * from json file.
 * 
 * @author Niket K
 */

public class HomeActivity extends Activity
{
	/* XML layout declarations */
	ListView mListViewIncidents;

	/* Class declarations */
	ChangerApplication mChangerApplication;
	IncidentManager mIncidentManager;
	IncidentsAdapter mIncidentsAdapter;

	/* Other declarations */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		/* Start - Finding XML layout views */
		mListViewIncidents = (ListView) findViewById(R.id.list_view_incidents);
		/* End - Finding XML layout views */

		mChangerApplication = (ChangerApplication) getApplication();
		new IncidentManager(HomeActivity.this).getIncidentsList(mHandler);

		mIncidentsAdapter = new IncidentsAdapter(HomeActivity.this);
		mListViewIncidents.setAdapter(mIncidentsAdapter);

		mListViewIncidents.setEmptyView(findViewById(R.id.text_view_no_record_found));
	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler()
	{
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg)
		{
			if(msg.arg2 == 0)
			{
				mChangerApplication.mIncidentsArrayList = (ArrayList<Incidents>) msg.obj;
				runOnUiThread(new Runnable()
				{
					public void run()
					{
						System.out.println(">>> Total Incidents: " + mChangerApplication.mIncidentsArrayList.size());
						mIncidentsAdapter.notifyDataSetChanged();
					}
				});
			}
			else
			{
				runOnUiThread(new Runnable()
				{
					public void run()
					{
						Toast.makeText(HomeActivity.this, "Incidents not found, please try again.", Toast.LENGTH_LONG).show();
					}
				});
			}
		}
	};
}
