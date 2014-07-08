package com.nik.changerapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nik.changerapp.incidentmanager.Attachments;
import com.nik.changerapp.incidentmanager.IncidentManager;
import com.nik.changerapp.incidentmanager.Incidents;
import com.nik.changerapp.utils.Utility;

/**
 * IncidentDetailsActivity used for showing all details of incidents.
 * 
 * @author Niket K
 */

public class IncidentDetailsActivity extends Activity
{
	/* XML layout declarations */
	private ImageView mImageViewUserStatus;
	private TextView mTextViewName, mTextViewDob, mTextViewEmail, mTextViewMobile;
	private TextView mTextViewIncidentDate, mTextViewCreatedDate, mTextViewProcessedDate, mTextViewNote, mTextViewLocation;
	private LinearLayout mLinLayAttachments, mLinLayAttchmentsBlock;

	/* Class declarations */
	IncidentManager mIncidentManager;
	ChangerApplication mChangerApplication;
	IncidentsAdapter mIncidentsAdapter;

	/* Other declarations */
	public static final String INCIDENT_INDEX = "CurrentIncidentIndex";
	private int mIncidentListIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

		setContentView(R.layout.activity_incident_details);

		/* Start - Finding XML layout views */
		mImageViewUserStatus = (ImageView) findViewById(R.id.image_view_user);
		mTextViewName = (TextView) findViewById(R.id.text_view_name);
		mTextViewDob = (TextView) findViewById(R.id.text_view_dob);
		mTextViewEmail = (TextView) findViewById(R.id.text_view_email);
		mTextViewMobile = (TextView) findViewById(R.id.text_view_mobile);
		mTextViewIncidentDate = (TextView) findViewById(R.id.text_view_incident_date_);
		mTextViewCreatedDate = (TextView) findViewById(R.id.text_view_created_date);
		mTextViewProcessedDate = (TextView) findViewById(R.id.text_view_processed_date);
		mTextViewNote = (TextView) findViewById(R.id.text_view_note);
		mTextViewLocation = (TextView) findViewById(R.id.text_view_location);
		mLinLayAttchmentsBlock = (LinearLayout) findViewById(R.id.lin_lay_attachments_block);
		mLinLayAttachments = (LinearLayout) findViewById(R.id.lin_lay_attachments);
		/* End - Finding XML layout views */

		mChangerApplication = (ChangerApplication) getApplication();
		Bundle bundle = getIntent().getExtras();
		if(bundle != null)
			mIncidentListIndex = bundle.getInt(INCIDENT_INDEX);

		setIncidentDetails();
	}

	/**
	 * Sets incident log data to layout views.
	 */
	private void setIncidentDetails()
	{
		try
		{
			if(mChangerApplication.mIncidentsArrayList != null && mChangerApplication.mIncidentsArrayList.size() > 0)
			{
				Incidents incidents = mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex);
				if(incidents.mUser.mIsInActive)
					mImageViewUserStatus.setImageResource(R.drawable.inactive_user);
				else
					mImageViewUserStatus.setImageResource(R.drawable.active_user);

				mTextViewName.setText(incidents.mUser.mFirstName + " " + incidents.mUser.mLastName);

				try
				{
					mTextViewDob.setText(Utility.getFormattedDate(incidents.mUser.mDateOfBirth).split("\\@")[0]);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					mTextViewDob.setText(incidents.mUser.mDateOfBirth);
				}

				mTextViewEmail.setText(incidents.mUser.mEmail);
				mTextViewMobile.setText(incidents.mUser.mMobile);

				if(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mArrayListAttachments != null && mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mArrayListAttachments.size() > 0)
				{
					mLinLayAttchmentsBlock.setVisibility(View.VISIBLE);

					for(Attachments attachment : mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mArrayListAttachments)
					{
						ImageView imageView = new ImageView(IncidentDetailsActivity.this);
						imageView.setBackgroundColor(0xFFDDDDDD);
						imageView.setImageResource(R.drawable.attachments);

						LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Utility.getDipValue(40, mChangerApplication), Utility.getDipValue(40, mChangerApplication));
						params.rightMargin = Utility.getDipValue(15, mChangerApplication);
						mLinLayAttachments.addView(imageView, params);

						imageView.setTag(attachment);
						imageView.setOnClickListener(new OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								Attachments attachmentTemp = (Attachments) v.getTag();
								Toast.makeText(IncidentDetailsActivity.this, attachmentTemp.mType + ": " + attachmentTemp.mName + "\n\nUrl: " + attachmentTemp.mUrl, Toast.LENGTH_SHORT).show();
							}
						});
					}
				}
				else
					mLinLayAttchmentsBlock.setVisibility(View.GONE);

				mTextViewIncidentDate.setText(Html.fromHtml("Incident Date<br><font color=\"#FF0000\">" + Utility.getFormattedDate(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mIncidentDate) + "</font>"));
				mTextViewCreatedDate.setText(Html.fromHtml("Created Date<br><font color=\"#FF0000\">" + Utility.getFormattedDate(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mDateCreated) + "</font>"));
				if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mDateProcessed))
					mTextViewProcessedDate.setText(Html.fromHtml("Processed Date<br><font color=\"#167432\">" + Utility.getFormattedDate(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mDateProcessed) + "</font>"));
				else
					mTextViewProcessedDate.setText(Html.fromHtml("Processed Date<br><font color=\"#167432\">Not yet processed.</font>"));

				if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mNotes))
					mTextViewNote.setText(Html.fromHtml("Note<br><font color=\"#444444\">" + mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mNotes + "</font>"));
				else
					mTextViewNote.setText(Html.fromHtml("Note<br><font color=\"#444444\">Not added.</font>"));

				if(!TextUtils.isEmpty(mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mLocation))
					mTextViewLocation.setText(Html.fromHtml("Location<br><font color=\"#444444\">" + mChangerApplication.mIncidentsArrayList.get(mIncidentListIndex).mLocation + "</font>"));
				else
					mTextViewLocation.setText(Html.fromHtml("Location<br><font color=\"#444444\">Not available.</font>"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
