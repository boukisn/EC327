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
		Button uploadButton = (Button) findViewById(R.id.button1);
		Button nextButton = (Button) findViewById(R.id.button2);
		TextView success = (TextView) findViewById(R.id.textView1);
		TextView invalid = (TextView) findViewById(R.id.TextView01);
		TextView almostThere = (TextView) findViewById(R.id.TextView02);
		TextView galleryError= (TextView) findViewById(R.id.TextView03);
		String fontPath = "OpenSans-Light.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        success.setTypeface(tf);
        invalid.setTypeface(tf);
        almostThere.setTypeface(tf);
        galleryError.setTypeface(tf);
		EditText email = (EditText) findViewById(R.id.editText1);
		nextButton.setVisibility(View.GONE);
		success.setVisibility(View.GONE);
		almostThere.setVisibility(View.GONE);
		galleryError.setVisibility(View.GONE);
		
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
		isInvalid = false;
		isGal = false;
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }


	    //YOU CAN EDIT THIS TO WHATEVER YOU WANT
	    private static final int SELECT_PICTURE = 1;

	    private String selectedImagePath;
	    //ADDED
	    private String filemanagerstring;

	    //UPDATED
	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	System.out.println("**********************ASdfasdfa************************");
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                Log.i("help!", selectedImageUri + "");

	                //OI FILE Manager
	                filemanagerstring = selectedImageUri.getPath();
	                Log.i("help!!", filemanagerstring+ "");
	                String fileDelims = "[/]";
	                String[] fileTokens = filemanagerstring.split(fileDelims);
	                
	                Log.i("blaze", fileTokens[1] + "");
	                if( fileTokens[1].equals("external") == true)
	                {
	                	
	                

		                //CHECK IF NULL
		                selectedImagePath = getPath(selectedImageUri);
		                
		                Log.i("help!!1", selectedImagePath + "");
		                String delims = "[.]";
			    		String[] tokens = selectedImagePath.split(delims);
			    		Log.i("help!!!!", selectedImagePath + "");
			    		if(tokens[1].equals("gif"))
			    		{
			                //DEBUG PURPOSE - you can delete this if you want
			                if(selectedImagePath!=null)
			                    System.out.println(selectedImagePath);
			                else System.out.println("selectedImagePath is null");
			                if(filemanagerstring!=null)
			                    System.out.println(filemanagerstring);
			                else System.out.println("filemanagerstring is null");
		
			                //NOW WE HAVE OUR WANTED STRING
			                if(selectedImagePath!=null)
			                    System.out.println("selectedImagePath is the right one for you!");
			                else
			                    System.out.println("filemanagerstring is the right one for you!");
			                
			                InputStream imageStream = null;
							try {
								imageStream = getContentResolver().openInputStream(selectedImageUri);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Log.i("fun", selectedImagePath + "");
							
				    		
			                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
			                Button uploadButton = (Button) findViewById(R.id.button1);
			        		Button nextButton = (Button) findViewById(R.id.button2);
			        		TextView success = (TextView) findViewById(R.id.textView1);
			        		TextView invalid = (TextView) findViewById(R.id.TextView01);
			        		TextView almostThere = (TextView) findViewById(R.id.TextView02);
			        		TextView galleryError= (TextView) findViewById(R.id.TextView03);
		                	
			                
			                imageReader.bmp = yourSelectedImage;
			                imageReader.crop();
			                imageReader.getPixels(imageReader.rebmp);
			                imageReader.dimensionfy();
			                imageReader.getHMday();
			                imageReader.get_times();
			                imageReader.HM_to_string();
			                
			                ImageView iv = (ImageView)findViewById(R.id.imageView1);
			                iv.setImageBitmap(yourSelectedImage);
			                
			                
			                if ((imageReader.bmp.getHeight() == 412) && (imageReader.bmp.getWidth() == 631) )
			                {
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
			                	Intent intent = getIntent();
			                	finish();
			                	startActivity(intent);
			                	isInvalid = true;
			                	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			                }
			                
			                
			            }
			    		else
				        {
				        	Intent intent = getIntent();
			            	finish();
			            	startActivity(intent);
			            	isInvalid = true;
			            	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				        }
			        }
	                else
	                {
		                Log.i("blaze", "idk");
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
     		//Use the cursor to step through the returned records
     		if (cur.moveToFirst() == false)
     			email.setText("Invalid email");
     		
     		else{
     			ArrayList<String> boukis_list = imageReader.final_list;
	    		intent.putStringArrayListExtra("boukis_list", boukis_list);
	    		intent.putExtra("googleAccount", googleAccount);
        		startActivity(intent);
        		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
     		}
	    }
	    
	    //UPDATED!
	    public String getPath(Uri uri) {
	        String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        if(cursor!=null)
	        {
	            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
	            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
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
