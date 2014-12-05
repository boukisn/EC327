package com.EC327.Schedulr;

import java.util.ArrayList;
import java.util.HashMap;

import com.EC327.Schedulr.R;
import com.EC327.Schedulr.R.id;
import com.EC327.Schedulr.R.layout;
import com.EC327.Schedulr.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		
		ArrayList<String> boukis_list = getIntent().getStringArrayListExtra("boukis_list");
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Schedule.this, android.R.layout.simple_spinner_item, boukis_list);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        
        TextView saved = (TextView)findViewById(R.id.textView1);
        saved.setVisibility(View.GONE);
        
        for(int i = 0; i < boukis_list.size(); i++)
        {
        	eventList.put(boukis_list.get(i), "");
        }
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
    	Intent intent = new Intent(this, Done.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
	
	public void saveName(View view) {
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
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
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		EditText className = (EditText)findViewById(R.id.editText1);
		String selection = spinner.getSelectedItem().toString();
		className.setText(eventList.get(selection), TextView.BufferType.EDITABLE);
		
		TextView saved = (TextView)findViewById(R.id.textView1);
		if(className.getText().toString().equals("") == false)
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
