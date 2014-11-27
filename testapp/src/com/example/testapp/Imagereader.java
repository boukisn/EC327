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
	
		
		for(int i =0, j = 0; i < 100; i++, j++)
		{
			
			Log.d("Pixels", "Pixel is :"+pixels[i]);
			
			/*if(colors.size() == 0)
				colors.add(pixels[i]);
			
			else if(pixels[i] != colors.get(j))
			{
				colors.add(pixels[i]);
			}*/
			
			/*for(int j = 0; j < colors.size(); j++)
			{
				if(pixels[i] != colors.get(j))
				{
					colors.add(pixels[i]);
				}
			}*/
		}
		
		//System.out.print("Colors: ");
		Log.i("OutPut", "Colors: ");
		Integer size = pixels.length;
		Integer size1 = colors.size();
		String f = size.toString();
		String f1 = size1.toString();
		Log.i("Pixels", f);
		Log.i("Colors", f1);
		
		/*for(int j = 0; j < colors.size(); j++)
		{
				Integer msg = colors.get(j);
				String str1= msg.toString();
				Log.i("OutPut", str1 + ", ");
				
		}*/
		
	
	
  
	}
}
