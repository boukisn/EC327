package com.EC327.Schedulr;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.widget.DatePicker;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

public class Cal {
	
	//Projection array. Creating indices for this array instead of doing
	//dynamic lookups improves performance.
	public static final String[] EVENT_PROJECTION = new String[] {
	 Calendars._ID,                           // 0
	 Calendars.ACCOUNT_NAME,                  // 1
	 Calendars.CALENDAR_DISPLAY_NAME,         // 2
	 Calendars.OWNER_ACCOUNT                  // 3
	};

	//The indices for the projection array above.
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
	private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
	private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
	
	//Calendar Information
	long calID;
	String displayName = null;
	String accountName = null;
	String ownerName = null;
	
	//Event IDs list - keeps track of what events have been added and their IDs
	ArrayList<Long> eventIDs = new ArrayList<Long>();

	Cal(String googleAccount, Activity activity)
	{
		String splitGoogleAccount[] = googleAccount.split("."); 
		String accountType = splitGoogleAccount[2] + "." + splitGoogleAccount[0];
		
		//Run query
		Cursor cur = null;
		ContentResolver cr = activity.getContentResolver();
		Uri uri = Calendars.CONTENT_URI;
		String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" 
		                     + Calendars.ACCOUNT_TYPE + " = ?) AND ("
		                     + Calendars.OWNER_ACCOUNT + " = ?))";
		String[] selectionArgs = new String[] {googleAccount, accountType, googleAccount}; 
		//Submit the query and get a Cursor object back. 
		cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
		//Use the cursor to step through the returned records
		if (cur.moveToFirst())
		{
			calID = cur.getLong(PROJECTION_ID_INDEX);
			displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
			accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
			ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
			Log.i("displayName", displayName);
			Log.i("accountName", accountName);
			Log.i("ownerName", ownerName);
		}
	}
	
	public void addEvent(String className, DatePicker 
			startDate, int startHour, int startMin, DatePicker 
			endDate, int endHour, int endMin, Activity active)
	{
		long startMillis = 0; 
		long endMillis = 0;   
		
		Calendar beginTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		
		beginTime.set(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMin);
		endTime.set(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endHour, endMin);

		startMillis = beginTime.getTimeInMillis();
		endMillis = endTime.getTimeInMillis();
		
		ContentResolver cr = active.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, className);
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE, "America/New York");
		Uri uri = cr.insert(Events.CONTENT_URI, values);
		eventIDs.add(Long.parseLong(uri.getLastPathSegment()));
	}	
}



