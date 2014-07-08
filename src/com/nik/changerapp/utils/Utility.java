package com.nik.changerapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.nik.changerapp.R;

/**
 * Holds all the Utility apis used by the application.
 * 
 * @author Niket K
 */

public class Utility
{
	/**
	 * Member variable declarations
	 */
	private static Utility _instance = null;
	private Context mContext = null;
	private Typeface mTypeface = null, mTypefaceBold = null;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	private Utility(Context context)
	{
		mContext = context;
	}

	/**
	 * Singleton class to instantiate the class variable
	 * 
	 * @param context
	 * @return
	 */
	public static Utility getInstance(Context context)
	{
		if(_instance == null)
		{
			_instance = new Utility(context);
		}
		return _instance;
	}

	/**
	 * Returns font type used by the application.
	 * 
	 * @return
	 */
	public Typeface getFontTypeface()
	{
		if(mTypeface == null)
		{
			mTypeface = Typeface.createFromAsset(mContext.getAssets(), "font/" + mContext.getResources().getString(R.string.type_face_regular));
		}
		return mTypeface;
	}

	/**
	 * Returns bolt font type used by the application.
	 * 
	 * @return
	 */
	public Typeface getBoldFontTypeface()
	{
		if(mTypefaceBold == null)
		{
			mTypefaceBold = Typeface.createFromAsset(mContext.getAssets(), "font/" + mContext.getResources().getString(R.string.type_face_bold));
		}
		return mTypefaceBold;
	}

	/**
	 * Returns DIP value.
	 * 
	 * @param value
	 * @param context
	 * @return
	 */
	static float scale = 0;

	public static int getDipValue(int value, Context context)
	{
		if(scale == 0)
			scale = context.getResources().getDisplayMetrics().density;

		return (int) (value * scale + 0.5f);
	}

	/**
	 * Returns specified formatted date from specified format.
	 * 
	 * @param date
	 *            in the form of "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
	 * 
	 * @return formatted date in the form of "EEE, d MMM yyyy '@' HH:mm:ss"
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getFormattedDate(String date)
	{
		if(TextUtils.isEmpty(date))
			return "";

		String formattedDate = date;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat output = new SimpleDateFormat("EEE, d MMM yyyy '@' HH:mm:ss");
			Date d = sdf.parse(date);
			formattedDate = output.format(d);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			formattedDate = date;
		}
		return formattedDate;
	}
}
