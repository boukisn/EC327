package com.example.testapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.app.Activity;



import java.io.File;


public class Imagereader extends Activity{
	
	
	public Bitmap bmp;
	public Bitmap rebmp;
	public Bitmap rect;
	public int height;
	public int width;
	public int[] pixels = new int[1];
	public int[] pixar= new int[1];
	public ImageView iv;
	
	
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
		
	public void output(int dimen){
		
		if (dimen == 1)
		{
			int count = 0;
			for(int i =0; i < pixels.length; i++)
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
			int count = 0;
			for(int i =0; i < pixar.length; i++)//rectangle is about 61 pixels long
			{
				
				if (pixar[i] == -65332)
				{
					count++;
				}
				
				//Log.d("Pixels", "Pixel is :"+ pixar[i]);
				
			}
			Log.i("Pixal Count", count + "");
		}
		
	
	}
	public void dimensionfy(){
		
		int pixel2D[][] = new int[height][width];
		
		for(int i=0; i < height; i++)
		{
			for(int j=0; j< width; j++)
			{
				pixel2D[i][j]= pixels[j + (i*width)];
			}
		}
	}
	
	public void crop()
	{
		rebmp = Bitmap.createBitmap(bmp, 55, 20, width-195, height-50);
		Log.i("height",height + "");
		Log.i("width",width + "");
	}
	
	public void getRectangle()//gets rectangle
	{
		rect = Bitmap.createBitmap(bmp, 120, 140, width-565, height-363);
		height = rect.getHeight();
		width =  rect.getWidth();
		Log.i("height",height + "");
		Log.i("width",width + "");
	
	}
	
	public void getPixels(Bitmap img)
	{
		pixar = new int[img.getHeight()*img.getWidth()];
		
		img.getPixels(pixar, 0, img.getWidth(), 0, 0, img.getWidth() , img.getHeight());
		//ArrayList<Integer> colors = new ArrayList<Integer>();
	}
	
	

	
			
		
		
	
		
  

}
