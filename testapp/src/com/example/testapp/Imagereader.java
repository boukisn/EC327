package com.example.testapp;


import android.graphics.Bitmap;
import java.util.*;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.File;


public class Imagereader {
	
	public Bitmap bmp;
	public int height;
	public int width;
	public int[] pixels = new int[1];
	
	public void pixelize(){
		
		
		File file = new File("/storage/emulated/0/Download/ScheduleImageServlet.gif");
		BitmapFactory.Options options = new BitmapFactory.Options();
		bmp = BitmapFactory.decodeFile(file.getAbsolutePath().toString(), options);
		height = bmp.getHeight();
		width =  bmp.getWidth();
	
		if(bmp !=null)
		{
			pixels = new int[height*width];
			
			bmp.getPixels(pixels, 0, width, 0, 0, width , height );
			//ArrayList<Integer> colors = new ArrayList<Integer>();
		}
	}
		
	public void output(){
		
	
		for(int i =0; i < pixels.length; i++)
		{
			if (pixels[i] != -1)
			Log.d("Pixels", "Pixel is :"+pixels[i]);
			
		}
	}
		
	
			
		
		
	
		
  

}
