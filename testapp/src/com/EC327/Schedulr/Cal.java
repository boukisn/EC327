package com.EC327.Schedulr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

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
		String accountType = "com.google";
		
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
	
	public void addEvent(String className, String ourDateFormat, String startDateString,
			String endDateString, Activity active)
	{
		try {
			
			//Parses information in "ourDateFormat"
    		String eventDateAndTime = ourDateFormat; // "Monday: 7:00AM.11:00PM"
    		String delims = "[ ]+";
    		String[] tokens1 = eventDateAndTime.split(delims); //tokens1[0] = "Monday:", tokens1[1] = "7:00AM.11:00PM"
    		String eventBeginTime = startDateString;
    		delims = "[.]";
    		String[] tokens2 = tokens1[1].split(delims); //tokens2[0] = "7:00AM", tokens2[1] = "11:00PM"
    		eventBeginTime += " ";
    		eventBeginTime += tokens2[0];//"09/14/2014 7:00AM"

    		//Get milliseconds since epoch for semester start date with start time
    		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mma");
			Date startDateBeginningTime = dateFormat.parse(eventBeginTime);
			DateFormat startFormat = new SimpleDateFormat("EEE");
			String dayOfWeek = startFormat.format(startDateBeginningTime);
			long eventBeginTimeLong = startDateBeginningTime.getTime();
			
			//Get milliseconds since epoch for semester start date with end time
			String eventEndTime = startDateString;
			eventEndTime += " ";
			eventEndTime += tokens2[1];
    		Date startDateEndTime = dateFormat.parse(eventEndTime);
			
    		//Get milliseconds since epoch for semester end date
			SimpleDateFormat newDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date endDateDate = newDateFormat.parse(endDateString);
			
			//Convert day of week to given format ("Monday" > "MO", "Wednesday" > "WE", etc.)
			String rruleDay = tokens1[0].substring(0,2).toUpperCase();
			
			//Algorithm for next occurrence of day of week since start date
			long actualStartDate = 0L;
			if(dayOfWeek.equals(rruleDay) == false)
			{
				int nextDay = 0;
				
				if(rruleDay.equals("SU"))
					nextDay = 1;
				
				else if(rruleDay.equals("MO"))
					nextDay = 2;
				
				else if(rruleDay.equals("TU"))
					nextDay = 3;
				
				else if(rruleDay.equals("WE"))
					nextDay = 4;
				
				else if(rruleDay.equals("TH"))
					nextDay = 5;
				
				else if(rruleDay.equals("FR"))
					nextDay = 6;
				
				else if(rruleDay.equals("SA"))
					nextDay = 7;
			
				Calendar cal = Calendar.getInstance();  
			    cal.setTime(startDateBeginningTime);  
				int dow = cal.get(Calendar.DAY_OF_WEEK);
				
				if(dow == nextDay)
				{
					actualStartDate = eventBeginTimeLong;
				}
				
				else{
					int numDays = 7 - ((dow - nextDay) % 7 + 7) % 7;  
					cal.add(Calendar.DAY_OF_YEAR, numDays);  
					actualStartDate = cal.getTime().getTime(); 
				}
			}
			
			//Duration in seconds of class
			SimpleDateFormat format = new SimpleDateFormat("h:mma");
			Date date1 = format.parse(tokens2[0]);
			Date date2 = format.parse(tokens2[1]);
			long duration = (date2.getTime() - date1.getTime()) / 1000;
			
			//For event creation formatting
			String durationString = "P" + duration + "S";
			
			//Put end date in given format for event creation
			SimpleDateFormat untilFormat = new SimpleDateFormat("yyyyMMdd");
			endDateDate.setTime(endDateDate.getTime() + 86400000);
			String untilString = untilFormat.format(endDateDate);
			
			//Create the event for the given class!
			ContentResolver cr = active.getContentResolver();
			ContentValues values = new ContentValues();
			values.put(Events.DTSTART, actualStartDate);
			System.out.println("eventBeginTimeLong " + eventBeginTimeLong);
			values.put(Events.DURATION, durationString);
			System.out.println("duration " + durationString);
			values.put(Events.TITLE, className);
			values.put(Events.RRULE, ("FREQ=WEEKLY;BYDAY=" + rruleDay + ";UNTIL=" + untilString + "T000000Z"));
			System.out.println("rruleDay " + rruleDay);
			values.put(Events.CALENDAR_ID, calID);
    	    values.put(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
			values.put(Events.EVENT_TIMEZONE, "America/New York");
			Uri uri = cr.insert(Events.CONTENT_URI, values);
			eventIDs.add(Long.parseLong(uri.getLastPathSegment()));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}