package com.example.testapp;


import android.graphics.Bitmap;
import java.util.*;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.File;


public class Imagereader {
	
	public void foo(){
		
		int[] pixels;
		File file = new File("/storage/emulated/0/Download/ScheduleImageServlet.gif");
		Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath().toString());
		int height = bmp.getHeight();
		int width =  bmp.getWidth();
		
		pixels = new int[height*width];
		
		bmp.getPixels(pixels, 0, width, 1, 1, width - 1, height - 1);
		ArrayList<Integer> colors = new ArrayList<Integer>();
		//Log.i("OutPut", "Colors: ");
		
		for(int i =0; i < 100; i++)
		{
			if(colors.size() == 0)
				colors.add(pixels[i]);
			
			for(int j = 0; j < colors.size(); j++)
			{
				if(pixels[i] != colors.get(j))
				{
					colors.add(pixels[i]);
				}
			}
		}
		
		//System.out.print("Colors: ");
		Log.i("OutPut", "Colors: ");
		
		for(int j = 0; j < colors.size(); j++)
		{
				Log.i("OutPut", colors.get(j) + ", ");
				
		}
		
	
	
  
	}
}
