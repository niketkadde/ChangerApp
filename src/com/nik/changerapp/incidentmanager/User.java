package com.nik.changerapp.incidentmanager;

/**
 * User data structure.
 * 
 * @author Niket K
 */

public class User
{

	/**
	 * Default User Constructor
	 */
	public User()
	{
		super();
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param id
	 * @param isInActive
	 * @param dateOfBirth
	 * @param mobile
	 * @param email
	 * @param lastName
	 * @param firstName
	 * @param userName
	 */
	public User(String id, boolean isInActive, String dateOfBirth, String mobile, String email, String lastName, String firstName, String userName)
	{
		super();

		this.mId = id;
		this.mIsInActive = isInActive;
		this.mDateOfBirth = dateOfBirth;
		this.mMobile = mobile;
		this.mEmail = email;
		this.mLastName = lastName;
		this.mFirstName = firstName;
		this.mUserName = userName;
	}

	public String mId;
	public boolean mIsInActive;
	public String mDateOfBirth;
	public String mMobile;
	public String mEmail;
	public String mLastName;
	public String mFirstName;
	public String mUserName;
}
