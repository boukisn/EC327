package com.EC327.Schedulr;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.EC327.Schedulr.R;
import com.EC327.Schedulr.R.id;
import com.EC327.Schedulr.R.layout;
import com.EC327.Schedulr.R.menu;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.CalendarContract.Calendars;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity {

	public Imagereader imageReader = new Imagereader();
	private static boolean isInvalid = false;
	private static boolean isGal = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_success);
		
		//Find page elements
		Button nextButton = (Button) findViewById(R.id.button2);
		TextView success = (TextView) findViewById(R.id.textView1);
		TextView invalid = (TextView) findViewById(R.id.TextView01);
		TextView almostThere = (TextView) findViewById(R.id.TextView02);
		TextView galleryError= (TextView) findViewById(R.id.TextView03);
		
		//Set fonts
		String fontPath = "OpenSans-Light.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        success.setTypeface(tf);
        invalid.setTypeface(tf);
        almostThere.setTypeface(tf);
        galleryError.setTypeface(tf);
        
        //Set visibilities
		EditText email = (EditText) findViewById(R.id.editText1);
		nextButton.setVisibility(View.GONE);
		success.setVisibility(View.GONE);
		almostThere.setVisibility(View.GONE);
		galleryError.setVisibility(View.GONE);
		
		//Checks if isInvalid and isGal flags are thrown
		if(isInvalid == false)
			invalid.setVisibility(View.GONE);
		else
			invalid.setVisibility(View.VISIBLE);
		if(isGal == false)
			galleryError.setVisibility(View.GONE);
		else
			galleryError.setVisibility(View.VISIBLE);
		
		email.setVisibility(View.GONE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_success, menu);
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
	
	public void upload(View view) {
		
		//On upload button being pressed, restart invalid flags
		isInvalid = false;
		isGal = false;
		
		//Open image chooser
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }


    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    //ADDED
    private String filemanagerstring;

    //UPDATED
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                //OI FILE Manager
                filemanagerstring = selectedImageUri.getPath();
                String fileDelims = "[/]";
                String[] fileTokens = filemanagerstring.split(fileDelims);
                
                //Makes sure file is in Gallery
                if( fileTokens[1].equals("external") == true)
                {
	                selectedImagePath = getPath(selectedImageUri);
	                
	                String delims = "[.]";
		    		String[] tokens = selectedImagePath.split(delims);
		    		
		    		//Checks if file ends in ".gif"
		    		if(tokens[1].equals("gif"))
		    		{
		                InputStream imageStream = null;
		                
						try {
							imageStream = getContentResolver().openInputStream(selectedImageUri);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//Get the Bitmap object from the image selected
		                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
		                
		                //Get all the objects in the activity
		                Button uploadButton = (Button) findViewById(R.id.button1);
		        		Button nextButton = (Button) findViewById(R.id.button2);
		        		TextView success = (TextView) findViewById(R.id.textView1);
		        		TextView invalid = (TextView) findViewById(R.id.TextView01);
		        		TextView almostThere = (TextView) findViewById(R.id.TextView02);
		        		TextView galleryError= (TextView) findViewById(R.id.TextView03);
	                	
		                //Process the image to get events
		                imageReader.bmp = yourSelectedImage;
		                imageReader.crop();
		                imageReader.getPixels(imageReader.rebmp);
		                imageReader.dimensionfy();
		                imageReader.getHMday();
		                imageReader.get_times();
		                imageReader.HM_to_string();
		                
		                //Display the image
		                ImageView iv = (ImageView)findViewById(R.id.imageView1);
		                iv.setImageBitmap(yourSelectedImage);
		                
		                //Check the image size
		                if ((imageReader.bmp.getHeight() == 412) && (imageReader.bmp.getWidth() == 631) )
		                {
		                	//Set visibilities
		                	uploadButton.setVisibility(View.GONE);
			                success.setVisibility(View.VISIBLE);
			                nextButton.setVisibility(View.VISIBLE);
			                almostThere.setVisibility(View.VISIBLE);
			                invalid.setVisibility(View.GONE);
			                galleryError.setVisibility(View.GONE);
			                
			                EditText email = (EditText) findViewById(R.id.editText1);
			                email.setVisibility(View.VISIBLE);
			                email.setText("Enter Google account email:");
		                	
		                }
		                else
		                {
		                	//Restart if wrong size
		                	Intent intent = getIntent();
		                	finish();
		                	startActivity(intent);
		                	isInvalid = true;
		                	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		                }
		                
		                
		            }
		    		else
			        {
		    			//Restart if doesn't end in ".gif"
			        	Intent intent = getIntent();
		            	finish();
		            	startActivity(intent);
		            	isInvalid = true;
		            	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			        }
		        }
                else
                {
                	//Restart if not from Gallery
                	TextView galleryError= (TextView) findViewById(R.id.TextView03);
                	galleryError.setVisibility(View.VISIBLE);
                	Intent intent = getIntent();
                	finish();
                	isGal = true;
                	startActivity(intent);
                	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }
        }
        
    }

    public void nextPage(View view) {
    	
    	//****************** Google account validation ******************
    	Intent intent = new Intent(this, Schedule.class);
    	EditText email = (EditText) findViewById(R.id.editText1);
    	String googleAccount = email.getText().toString();
    	String accountType = "com.google";
 		
         String[] EVENT_PROJECTION = new String[] {
        	 Calendars._ID,                           // 0
        	 Calendars.ACCOUNT_NAME,                  // 1
        	 Calendars.CALENDAR_DISPLAY_NAME,         // 2
        	 Calendars.OWNER_ACCOUNT                  // 3
        	};
         
 		//Run query
 		Cursor cur = null;
 		ContentResolver cr = this.getContentResolver();
 		Uri uri = Calendars.CONTENT_URI;
 		String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" 
 		                     + Calendars.ACCOUNT_TYPE + " = ?) AND ("
 		                     + Calendars.OWNER_ACCOUNT + " = ?))";
 		String[] selectionArgs = new String[] {googleAccount, accountType, googleAccount}; 
 		
 		//Submit the query and get a Cursor object back. 
 		cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

 		//If nothing was returned, tell the user the email was invalid
 		if (cur.moveToFirst() == false)
 			email.setText("Invalid email");
 		
 		else{
 			
 			//Pass the email on to the next activity
 			ArrayList<String> boukis_list = imageReader.final_list;
    		intent.putStringArrayListExtra("boukis_list", boukis_list);
    		intent.putExtra("googleAccount", googleAccount);
    		startActivity(intent);
    		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
 		}
    }
    

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null)
        {
            int column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }
    
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
