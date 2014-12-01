package com.EC327.Schedulr;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentValues;
import android.content.Intent;
import com.example.testapp.R;

public class Cal {
	public Calendar cal;
	
	Cal()
	{
		cal = Calendar.getInstance();
	}
	
	public void addEvent(Activity active)
	{
		
		//final ContentValues event = new ContentValues();
		//event.put(Events, value)
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
	    intent.putExtra("beginTime", cal.getTimeInMillis());
	    intent.putExtra("allDay", true);
	    intent.putExtra("rrule", "FREQ=YEARLY");
	    intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
	    intent.putExtra("title", "EVENTTTT");
	    active.startActivity(intent);
	}
}