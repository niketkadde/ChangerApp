package com.nik.changerapp.incidentmanager;

import java.util.ArrayList;

/**
 * Incidents data structure.
 * 
 * @author Niket K
 */

public class Incidents
{
	/**
	 * Default Incidents Constructor
	 */
	public Incidents()
	{
		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param id
	 * @param eventId
	 * @param containerKey
	 * @param title
	 * @param thumbnail
	 * @param organization
	 * @param notes
	 * @param location
	 * @param locationType
	 * @param locationExtra
	 * @param isInUse
	 * @param isArchived
	 * @param incidentDate
	 * @param dateCreated
	 * @param dateProcessed
	 * @param refNumber
	 * @param starSeverity
	 * @param starSeverityText
	 */
	public Incidents(String id, String eventId, String containerKey, String title, String thumbnail, String organization, String notes, String location, String locationType, String locationExtra, boolean isInUse, boolean isArchived, String incidentDate, String dateCreated, String dateProcessed, String refNumber, int starSeverity, String starSeverityText)
	{
		super();

		this.mId = id;

		this.mEventId = eventId;
		this.mContainerKey = containerKey;

		this.mTitle = title;
		this.mThumbnail = thumbnail;
		this.mOrganization = organization;
		this.mNotes = notes;

		this.mLocation = location;
		this.mLocationType = locationType;
		this.mLocationExtra = locationExtra;

		this.mIsInUse = isInUse;
		this.mIsArchived = isArchived;

		this.mIncidentDate = incidentDate;
		this.mDateCreated = dateCreated;
		this.mDateProcessed = dateProcessed;

		this.mRefNumber = refNumber;
		this.mStarSeverity = starSeverity;
		this.mStarSeverityText = starSeverityText;
	}

	public String mId;
	public String mEventId;
	public String mContainerKey;

	public String mTitle;
	public String mThumbnail;
	public String mOrganization;
	public String mNotes;

	public String mLocation;
	public String mLocationType;
	public String mLocationExtra;

	public boolean mIsInUse;
	public boolean mIsArchived;

	public String mIncidentDate;
	public String mDateCreated;
	public String mDateProcessed;

	public String mRefNumber;
	public int mStarSeverity;
	public String mStarSeverityText;

	public User mUser;
	public ArrayList<Attachments> mArrayListAttachments;

}
