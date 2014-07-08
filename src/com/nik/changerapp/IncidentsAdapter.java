package com.nik.changerapp;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nik.changerapp.utils.Utility;

/**
 * ListView adapter for showing incident logs.
 * 
 * @author Niket
 */

public class IncidentsAdapter extends BaseAdapter
{
	private Context mContext;
	private ChangerApplication mChangerApplication;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param listCapabilities
	 */
	public IncidentsAdapter(Context context)
	{
		mContext = context;
		mChangerApplication = (ChangerApplication) mContext.getApplicationContext();
	}

	public int getCount()
	{
		return(mChangerApplication.mIncidentsArrayList == null ? 0 : mChangerApplication.mIncidentsArrayList.size());
	}

	public Object getItem(int position)
	{
		return position;
	}

	public long getItemId(int position)
	{
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ItemHolder itemHolder;
		if(convertView == null)
		{
			itemHolder = new ItemHolder();

			LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ViewGroup viewGroup = (ViewGroup) mLayoutInflater.inflate(R.layout.list_view_item, null);

			itemHolder.mRelativeLayoutItem = (RelativeLayout) viewGroup.findViewById(R.id.rel_lay_list_view_item);
			itemHolder.mTextViewTitle = (TextView) viewGroup.findViewById(R.id.text_view_title);
			itemHolder.mTextViewSeverity = (TextView) viewGroup.findViewById(R.id.text_view_star_severity);
			itemHolder.mTextViewIncidentDate = (TextView) viewGroup.findViewById(R.id.text_view_incident_date);
			itemHolder.mTextViewUserName = (TextView) viewGroup.findViewById(R.id.text_view_user);
			itemHolder.mTextViewRefNumber = (TextView) viewGroup.findViewById(R.id.text_view_ref_number);
			itemHolder.mImageViewAttachments = (ImageView) viewGroup.findViewById(R.id.image_view_attachments);

			convertView = viewGroup;
			convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
			convertView.setTag(itemHolder);
		}
		else
		{
			itemHolder = (ItemHolder) convertView.getTag();
		}

		if(mChangerApplication.mIncidentsArrayList.get(position).mArrayListAttachments != null && mChangerApplication.mIncidentsArrayList.get(position).mArrayListAttachments.size() > 0)
			itemHolder.mImageViewAttachments.setVisibility(View.VISIBLE);
		else
			itemHolder.mImageViewAttachments.setVisibility(View.GONE);

		if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(position).mTitle))
			itemHolder.mTextViewTitle.setText(mChangerApplication.mIncidentsArrayList.get(position).mTitle);
		else
			itemHolder.mTextViewTitle.setText("No Title");

		if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(position).mStarSeverityText))
			itemHolder.mTextViewSeverity.setText(mChangerApplication.mIncidentsArrayList.get(position).mStarSeverity + "-" + mChangerApplication.mIncidentsArrayList.get(position).mStarSeverityText);
		else
			itemHolder.mTextViewSeverity.setText(String.valueOf(mChangerApplication.mIncidentsArrayList.get(position).mStarSeverity));

		if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(position).mIncidentDate))
			itemHolder.mTextViewIncidentDate.setText(Utility.getFormattedDate(mChangerApplication.mIncidentsArrayList.get(position).mIncidentDate));
		else
			itemHolder.mTextViewIncidentDate.setText(Utility.getFormattedDate("1900-02-01T00:00:00.000Z"));

		itemHolder.mTextViewUserName.setText(mChangerApplication.mIncidentsArrayList.get(position).mUser.mFirstName + " " + mChangerApplication.mIncidentsArrayList.get(position).mUser.mLastName);

		if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(position).mRefNumber))
			itemHolder.mTextViewRefNumber.setText("#" + mChangerApplication.mIncidentsArrayList.get(position).mRefNumber);
		else
			itemHolder.mTextViewRefNumber.setText("#0000");

		convertView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(mContext, IncidentDetailsActivity.class);
				intent.putExtra(IncidentDetailsActivity.INCIDENT_INDEX, position);
				mContext.startActivity(intent);
			}
		});

		return convertView;
	}

	class ItemHolder
	{
		RelativeLayout mRelativeLayoutItem;
		TextView mTextViewTitle, mTextViewSeverity, mTextViewIncidentDate, mTextViewUserName, mTextViewRefNumber;
		ImageView mImageViewThumbnail, mImageViewAttachments;
	}

	/**
	 * Call this method to notify data set changes.
	 * 
	 * @param listCapabilities
	 */
	public void notifyDataSetChanged(ArrayList<String> listCapabilities)
	{
		this.notifyDataSetChanged();
	}
}
