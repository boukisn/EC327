package com.EC327.Schedulr;

import com.EC327.Schedulr.R;
import com.EC327.Schedulr.R.id;
import com.EC327.Schedulr.R.layout;
import com.EC327.Schedulr.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Done extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.done, menu);
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
	
	public void open(View view) {
    	//...
    }
	
	public void another(View view) {
    	Intent intent = new Intent(this, LoginSuccessActivity.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}