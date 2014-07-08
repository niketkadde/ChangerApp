package com.nik.changerapp.incidentmanager;

/**
 * Attachments data structure.
 * 
 * @author Niket K
 */

public class Attachments
{
	/**
	 * Attachments default constructor.
	 */
	public Attachments()
	{
		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param id
	 * @param thumbnailUrl
	 * @param url
	 * @param type
	 * @param name
	 */
	public Attachments(String id, String thumbnailUrl, String url, String type, String name)
	{
		super();

		this.mId = id;
		this.mThumbnailUrl = thumbnailUrl;
		this.mUrl = url;
		this.mType = type;
		this.mName = name;
	}

	public String mId;
	public String mThumbnailUrl;
	public String mUrl;
	public String mType;
	public String mName;
}
