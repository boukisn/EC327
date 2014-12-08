package com.EC327.Schedulr;

import com.EC327.Schedulr.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Sets font for title
        String fontPath = "OpenSans-Light.ttf";
        TextView txtTitle = (TextView) findViewById(R.id.textView1);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtTitle.setTypeface(tf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void login(View view) {
    	
    	//Move on to the upload image activity
    	Intent intent = new Intent(this, LoginSuccessActivity.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    
    public void goToAbout(View view) {
    	
    	//Go to the About Page
    	Intent intent = new Intent(this, About.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
