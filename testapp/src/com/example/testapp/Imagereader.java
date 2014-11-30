package com.example.testapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.app.Activity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import java.io.File;


public class Imagereader extends Activity{
	
	
	public Bitmap bmp;// bitmap of whole image
	public Bitmap rebmp;//bitmap of grid
	public Bitmap rect;//bitmap of 2 hour time period
	public int height;
	public int width;
	public int nheight;
	public int nwidth;
	public int[] pixels = new int[1]; // pixals of 
	public int[] pixar= new int[1];
	public int[][]pixel2D = new int[1][1];
	public HashMap<Integer, Integer> color_count = new HashMap<Integer, Integer>();
	
	
	public void pixelize(){ // sets bitmap of whole image and saves it to pixals
		
		
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
		
	public void output(int dimen){// reads off pixels on a pixel array
		
		if (dimen == 1)
		{
			int count = 0;
			for(int i =0; i < pixels.length; i++)//5856 is about 4 hours
			{
				if (pixels[i] == -65332)
				{
					count++;
				}
				
			}
			Log.i("Pixal Count", count + "");
		}
		else if (dimen == 2)
		{
			
			
			for(int i =0; i < pixar.length; i++)//rectangle is about 61 pixels in width
			{
				if ((pixar[i] != -1) && (pixar[i] != -3355444))//excludes white and gray colors
				{
					if (color_count.containsKey(pixar[i]))
					{
						color_count.put(pixar[i], color_count.get(pixar[i])+1);
					}
					else
					{
						color_count.put(pixar[i], 1);
					}
				}
				
				//Log.d("Pixels", "Pixel is :"+ pixar[i]);
				
			}
			Log.i("Pixal Count", color_count.get(-65332) + "");
			Set<Integer> keyset = color_count.keySet();
			for(Integer s: keyset)//displays how many pixels of each color
			{
				Log.i(s+ "", color_count.get(s)+"");
			}
			//Log.i("Keys",keyset  + "" );
		}
		
	
	}
	public void dimensionfy(){// ignore this for now
		
		pixel2D = new int[nheight][nwidth];
		
		for(int i=0; i < nheight; i++)
		{
			for(int j=0; j< nwidth; j++)
			{
				pixel2D[i][j]= pixar[j + (i*nwidth)];
			}
		}
	}
	
	public void crop()// crops the larger image to just the graph
	{
		rebmp = Bitmap.createBitmap(bmp, 55, 20, width-195, height-50);
		Log.i("height",height + "");
		Log.i("width",width + "");
		nheight = rebmp.getHeight();
		nwidth =  rebmp.getWidth();
	}
	
	public void getRectangle()//gets rectangle(2 hour pink rectangle)
	{
		rect = Bitmap.createBitmap(bmp, 120, 140, width-565, height-363);
		height = rect.getHeight();
		width =  rect.getWidth();
		Log.i("height",height + "");
		Log.i("width",width + "");
	
	}
	
	public void getPixels(Bitmap img)// will put pixels of a certain bitmap int the pixar array
	{
		pixar = new int[img.getHeight()*img.getWidth()];
		
		img.getPixels(pixar, 0, img.getWidth(), 0, 0, img.getWidth() , img.getHeight());
		//ArrayList<Integer> colors = new ArrayList<Integer>();
	}
	
	public void get2Darray()
	{
		//Log.i("array", Arrays.deepToString(pixel2D));
		for(int i=0; i < nheight; i++)
		{
			for(int j=0; j< nwidth; j++)
			{
				int count = 0;
				if (pixel2D[i][j] == -65332)
				{
					count++;
					if(count == 1)
					{
						Log.i("start", i+ "");
					}
					else if (count == 5856)
					{
						Log.i("end", i+ "");
					}
				}
				else
				{
					
				}
				
			}
		}
	}

	
			
		
		
	
		
  

}
