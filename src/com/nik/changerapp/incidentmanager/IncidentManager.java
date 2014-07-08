package com.nik.changerapp.incidentmanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.nik.changerapp.R;

/**
 * IncidentManager class, parses JSON and provide related apis.
 * 
 * @author Niket K
 */

public class IncidentManager
{
	private Context mContext;

	/**
	 * Default Constructor.
	 * 
	 * @param context
	 */
	public IncidentManager(Context context)
	{
		this.mContext = context;
	}

	/**
	 * Parses the given incidentlogs.json file and returns incident list.
	 * 
	 * @param handler
	 *            - called after the execution is completed. <br>
	 *            Contains the Message object holding:
	 *            <ul>
	 *            <li>obj = Incidents array list.</li>
	 *            <li>arg2 = flag set to 1 if error occurred else 0</li>
	 *            </ul>
	 */
	public void getIncidentsList(final Handler handler)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Message message = new Message();
					message.arg2 = 0;

					InputStream inputStream = mContext.getResources().openRawResource(R.raw.incidentlogs);
					String jsonString = null;
					try
					{
						BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
						StringBuilder sb = new StringBuilder();
						String line = null;
						while((line = reader.readLine()) != null)
						{
							sb.append(line + "\n");
						}
						inputStream.close();
						jsonString = sb.toString();
					}
					catch(Exception e)
					{
						Log.e("Buffer Error", "Error converting result " + e.toString());
						message.arg2 = 1;
					}

					try
					{
						if(!TextUtils.isEmpty(jsonString))
						{
							ArrayList<Incidents> incidentsList = parseJson(new JSONArray(jsonString));
							message.obj = incidentsList;
						}
					}
					catch(JSONException e)
					{
						Log.e("JSON Parser", "Error parsing data " + e.toString());
						message.arg2 = 1;
					}

					if(handler != null)
						handler.handleMessage(message);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * Parses the given JSONArray and returns incident list.
	 * 
	 * @param jsonArray
	 * @return
	 */
	private ArrayList<Incidents> parseJson(JSONArray jsonArray)
	{
		if(jsonArray == null)
			return null;

		ArrayList<Incidents> incidentsList = new ArrayList<Incidents>();
		try
		{
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Incidents incidents = new Incidents();

				if(jsonObject.has("id"))
					incidents.mId = jsonObject.getString("id");

				if(jsonObject.has("_id"))
					incidents.mId = jsonObject.getString("_id");

				if(jsonObject.has("eventId"))
					incidents.mEventId = jsonObject.getString("eventId");

				if(jsonObject.has("containerKey"))
					incidents.mContainerKey = jsonObject.getString("containerKey");

				if(jsonObject.has("title"))
					incidents.mTitle = jsonObject.getString("title");

				if(jsonObject.has("thumbnail"))
					incidents.mThumbnail = jsonObject.getString("thumbnail");

				if(jsonObject.has("organization"))
					incidents.mOrganization = jsonObject.getString("organization");

				if(jsonObject.has("notes"))
				{
					JSONArray jsonArrayNote = jsonObject.getJSONArray("notes");
					StringBuilder stringBuilder = new StringBuilder();
					for(int k = 0; k < jsonArrayNote.length(); k++)
					{
						stringBuilder.append(jsonArrayNote.getString(k));
					}
					incidents.mNotes = stringBuilder.toString();
				}

				if(jsonObject.has("location"))
					incidents.mLocation = jsonObject.getString("location");

				if(jsonObject.has("locationType"))
					incidents.mLocationType = jsonObject.getString("locationType");

				if(jsonObject.has("locationExtra"))
					incidents.mLocationExtra = jsonObject.getString("locationExtra");

				if(jsonObject.has("inUse"))
					incidents.mIsInUse = jsonObject.getString("inUse").equalsIgnoreCase("true") ? true : false;

				if(jsonObject.has("archived"))
					incidents.mIsArchived = jsonObject.getString("archived").equalsIgnoreCase("true") ? true : false;

				if(jsonObject.has("incidentDate"))
					incidents.mIncidentDate = jsonObject.getString("incidentDate");

				if(jsonObject.has("dateCreated"))
					incidents.mDateCreated = jsonObject.getString("dateCreated");

				if(jsonObject.has("dateProcessed"))
					incidents.mDateProcessed = jsonObject.getString("dateProcessed");

				if(jsonObject.has("refNumber"))
					incidents.mRefNumber = jsonObject.getString("refNumber");

				if(jsonObject.has("starSeverity"))
					incidents.mStarSeverity = jsonObject.getInt("starSeverity");

				if(jsonObject.has("starSeverityText"))
					incidents.mStarSeverityText = jsonObject.getString("starSeverityText");

				if(jsonObject.has("user"))
				{
					JSONObject jsonObjectUser = jsonObject.getJSONObject("user");
					User user = new User();

					if(jsonObjectUser.has("id"))
						user.mId = jsonObjectUser.getString("id");

					if(jsonObjectUser.has("_id"))
						user.mId = jsonObjectUser.getString("_id");

					if(jsonObjectUser.has("inactive"))
						user.mIsInActive = jsonObjectUser.getString("inactive").equalsIgnoreCase("true") ? true : false;

					if(jsonObjectUser.has("date_of_birth"))
						user.mDateOfBirth = jsonObjectUser.getString("date_of_birth");

					if(jsonObjectUser.has("mobile"))
						user.mMobile = jsonObjectUser.getString("mobile");

					if(jsonObjectUser.has("email"))
						user.mEmail = jsonObjectUser.getString("email");

					if(jsonObjectUser.has("lastname"))
						user.mLastName = jsonObjectUser.getString("lastname");

					if(jsonObjectUser.has("firstname"))
						user.mFirstName = jsonObjectUser.getString("firstname");

					if(jsonObjectUser.has("username"))
						user.mUserName = jsonObjectUser.getString("username");

					incidents.mUser = user;
				}

				if(jsonObject.has("attachments"))
				{
					JSONArray jsonArrayAttach = jsonObject.getJSONArray("attachments");
					ArrayList<Attachments> attachmentList = new ArrayList<Attachments>();
					for(int j = 0; j < jsonArrayAttach.length(); j++)
					{
						JSONObject jsonObjectAttach = jsonArrayAttach.getJSONObject(j);
						Attachments attachment = new Attachments();

						if(jsonObjectAttach.has("id"))
							attachment.mId = jsonObjectAttach.getString("id");

						if(jsonObjectAttach.has("_id"))
							attachment.mId = jsonObjectAttach.getString("_id");

						if(jsonObjectAttach.has("thumbnailUrl"))
							attachment.mThumbnailUrl = jsonObjectAttach.getString("thumbnailUrl");
						if(jsonObjectAttach.has("url"))
							attachment.mUrl = jsonObjectAttach.getString("url");
						if(jsonObjectAttach.has("type"))
							attachment.mType = jsonObjectAttach.getString("type");
						if(jsonObjectAttach.has("name"))
							attachment.mName = jsonObjectAttach.getString("name");

						attachmentList.add(attachment);
					}

					incidents.mArrayListAttachments = attachmentList;
				}

				incidentsList.add(incidents);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return incidentsList;
	}
}
