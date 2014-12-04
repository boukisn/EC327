package com.EC327.Schedulr;

import com.EC327.Schedulr.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MainActivity extends Activity {
	ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Imagereader image = new Imagereader();
        image.pixelize();
        image.dimensionfy();
        //image.getRectangle();
        image.crop();
        image.getPixels(image.rebmp);
        image.dimensionfy();
        image.output(2);
        //image.get2Darray();
        image.getHMday();
        /*Log.i("Yay", "Sunday");
        image.outputMap(image.pixel_countSu);
        Log.i("Yay", "Monday");
        image.outputMap(image.pixel_countMo);
        Log.i("Yay", "Tuesday");
        image.outputMap(image.pixel_countTu);
        Log.i("Yay", "Wednesday");
        image.outputMap(image.pixel_countWe);
        Log.i("Yay", "Thursday");
        image.outputMap(image.pixel_countTh);
        Log.i("Yay", "Friday");
        image.outputMap(image.pixel_countFr);
        Log.i("Yay", "Saturday");
        image.outputMap(image.pixel_countSa);*/
        image.get_times();
        iv = (ImageView)findViewById(R.id.imageView1);
        iv.setImageBitmap(image.rebmp);
        
        //image.show_bit();
        //image.output(2);
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
}
