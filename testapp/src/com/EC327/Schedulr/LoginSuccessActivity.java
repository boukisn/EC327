package com.EC327.Schedulr;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.EC327.Schedulr.R;
import com.EC327.Schedulr.R.id;
import com.EC327.Schedulr.R.layout;
import com.EC327.Schedulr.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginSuccessActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_success);
		Button uploadButton = (Button) findViewById(R.id.button1);
		Button nextButton = (Button) findViewById(R.id.button2);
		TextView success = (TextView) findViewById(R.id.textView1);
		nextButton.setVisibility(View.GONE);
		success.setVisibility(View.GONE);
		
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
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();

	                //OI FILE Manager
	                filemanagerstring = selectedImageUri.getPath();

	                //MEDIA GALLERY
	                selectedImagePath = getPath(selectedImageUri);

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
	                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	                Button uploadButton = (Button) findViewById(R.id.button1);
	        		Button nextButton = (Button) findViewById(R.id.button2);
	        		TextView success = (TextView) findViewById(R.id.textView1);
	                uploadButton.setVisibility(View.GONE);
	                nextButton.setVisibility(View.VISIBLE);
	                success.setVisibility(View.VISIBLE);
	                
	                ImageView iv = (ImageView)findViewById(R.id.imageView1);
	                iv.setImageBitmap(yourSelectedImage);
	            }
	        }
	    }

	    public void nextPage(View view) {
	    	Intent intent = new Intent(this, Schedule.class);
        	startActivity(intent);
        	overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
