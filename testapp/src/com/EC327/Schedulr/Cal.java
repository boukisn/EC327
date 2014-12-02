package com.EC327.Schedulr;

import java.util.Calendar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.example.testapp.R;

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
	
	public Calendar cal;
	
	Cal(Activity activity)
	{
		//Run query
		Cursor cur = null;
		ContentResolver cr = activity.getContentResolver();
		Uri uri = Calendars.CONTENT_URI;   
		String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" 
		                     + Calendars.ACCOUNT_TYPE + " = ?) AND ("
		                     + Calendars.OWNER_ACCOUNT + " = ?))";
		String[] selectionArgs = new String[] {"nodroids327@gmail.com", "com.google",
		     "nodroids327@gmail.com"}; 
		//Submit the query and get a Cursor object back. 
		cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

		//Use the cursor to step through the returned records
		while (cur.moveToNext()) {
		 long calID = 0;
		 String displayName = null;
		 String accountName = null;
		 String ownerName = null;
		
		 // Get the field values
		 calID = cur.getLong(PROJECTION_ID_INDEX);
		 displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
		 accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
		 ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
		}
	}
	public void addEvent(Activity active)
	{
		long calID = 3;
		long startMillis = 0; 
		long endMillis = 0;     
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(2012, 9, 14, 7, 30);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(2012, 9, 14, 8, 45);
		endMillis = endTime.getTimeInMillis();

		ContentResolver cr = active.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, "Jazzercise");
		values.put(Events.DESCRIPTION, "Group workout");
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE, "America/Los_Angeles");
		Uri uri = cr.insert(Events.CONTENT_URI, values);
	}
}



