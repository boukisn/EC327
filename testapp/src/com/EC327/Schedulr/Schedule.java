package com.EC327.Schedulr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.EC327.Schedulr.R;
import com.EC327.Schedulr.R.id;
import com.EC327.Schedulr.R.layout;
import com.EC327.Schedulr.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Schedule extends Activity implements OnItemSelectedListener{

	HashMap<String, String> eventList = new HashMap<String, String>();
	private static boolean clicked = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		
		ArrayList<String> boukis_list = getIntent().getStringArrayListExtra("boukis_list");
		Spinner spinner = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Schedule.this, android.R.layout.simple_spinner_item, boukis_list);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        
        TextView saved = (TextView)findViewById(R.id.textView1);
        saved.setVisibility(View.GONE);
        
        for(int i = 0; i < boukis_list.size(); i++)
        {
        	eventList.put(boukis_list.get(i), "");
        }
        
        int classCounter = 1;
		for(String key : eventList.keySet())
    	{
    		eventList.put(key, "Class " + classCounter);
    		classCounter++;
    	}
		
		
		TextView startDate = (TextView)findViewById(R.id.TextView01);
		TextView selectClasses = (TextView)findViewById(R.id.TextView02);
		TextView className = (TextView)findViewById(R.id.textView3);
		TextView endDate = (TextView)findViewById(R.id.TextView04);
		TextView invalidEnd = (TextView)findViewById(R.id.TextView05);
		TextView savedName = (TextView)findViewById(R.id.textView1);
		TextView invalidStart = (TextView)findViewById(R.id.textView2);
		
		String fontPath = "OpenSans-Light.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        startDate.setTypeface(tf);
        selectClasses.setTypeface(tf);
        className.setTypeface(tf);
        endDate.setTypeface(tf);
        invalidEnd.setTypeface(tf);
        savedName.setTypeface(tf);
        invalidStart.setTypeface(tf);
        
		invalidStart.setVisibility(View.GONE);
		invalidEnd.setVisibility(View.GONE);
		clicked = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void generate(View view) {
		if(clicked == false)
		{
			Intent intent = new Intent(this, Done.class);
	    	EditText startDate = (EditText)findViewById(R.id.editText2);
			String startDateString = startDate.getText().toString();
			
			EditText endDate = (EditText)findViewById(R.id.EditText01);
			String endDateString = endDate.getText().toString();
			Pattern pat = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d");
			Matcher matstart = pat.matcher(startDateString);
			Matcher matend = pat.matcher(endDateString);
			TextView invalidStart = (TextView)findViewById(R.id.textView2);
			TextView invalidEnd = (TextView)findViewById(R.id.TextView05);
			
			if(matstart.matches() == false || isValidDate(startDateString) == false)
				invalidStart.setVisibility(View.VISIBLE);
			else
				invalidStart.setVisibility(View.GONE);
				
			if(matend.matches() == false || isValidDate(endDateString) == false)
				invalidEnd.setVisibility(View.VISIBLE);
			else
				invalidEnd.setVisibility(View.GONE);
			boolean isInvalid = false;
			
			if (matstart.matches() && matend.matches())
			{
				if(isValidDate(startDateString) && isValidDate(endDateString))
				{
					
					
					Date semesterStartDate;
					try {
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						semesterStartDate = dateFormat.parse(startDateString);
						Date semesterEndDate = dateFormat.parse(endDateString);
						long semesterStartDateLong = semesterStartDate.getTime();
						long semesterEndDateLong = semesterEndDate.getTime();
						if((semesterEndDateLong - semesterStartDateLong) < 0)
						{
							invalidStart.setVisibility(View.VISIBLE);
							invalidEnd.setVisibility(View.VISIBLE);
							isInvalid = true;
						}
						
						else
						{
							invalidStart.setVisibility(View.GONE);
							invalidEnd.setVisibility(View.GONE);
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}
				
			}
			
			if (matstart.matches() && matend.matches())
			{
				if(isValidDate(startDateString) && isValidDate(endDateString) && isInvalid == false)
				{
					clicked = true;
					String googleAccount = getIntent().getStringExtra("googleAccount");
					Cal eventCreator = new Cal(googleAccount, this);
					
			        
			    	for(String key : eventList.keySet())
			    	{
			    		eventCreator.addEvent(eventList.get(key), key, startDateString, endDateString, this);
			    	}
			    	startActivity(intent);
			    	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				}
				
			}
		}

    }
	
	private static boolean isValidDate(String input) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            format.setLenient(false);
            format.parse(input);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }
	
	public void saveName(View view) {
		Spinner spinner = (Spinner)findViewById(R.id.spinner2);
		String selection = spinner.getSelectedItem().toString();
		EditText className = (EditText)findViewById(R.id.editText1);
		String classNameString = className.getText().toString();
		eventList.put(selection, classNameString);
		TextView saved = (TextView)findViewById(R.id.textView1);
		saved.setVisibility(View.VISIBLE);
		System.out.println("Info- " + selection + " " + classNameString);
		
    }
	
	public void onItemSelected(AdapterView<?> spinner1, View view,int pos, long id)
	{
		Spinner spinner = (Spinner)findViewById(R.id.spinner2);
		EditText className = (EditText)findViewById(R.id.editText1);
		String selection = spinner.getSelectedItem().toString();
		className.setText(eventList.get(selection), TextView.BufferType.EDITABLE);
		
		TextView saved = (TextView)findViewById(R.id.textView1);
		if(className.getText().toString().equals("") == false && className.getText().toString().substring(0, 5).equals("Class") == false)
		{
			saved.setVisibility(View.VISIBLE);
		}
		
		else
			saved.setVisibility(View.GONE);

	}
	
	public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
	
	

}
