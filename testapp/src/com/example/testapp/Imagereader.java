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
	public HashMap<Integer, Integer> pixel_countSu = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countMo = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countTu = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countWe = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countTh = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countFr = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> pixel_countSa = new HashMap<Integer, Integer>();
	
	
	
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
		nwidth =  rebmp.getWidth();// 436 pixels about 62 pixels a day
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
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		Log.i("width", nwidth + "");
		//Log.i("array", Arrays.deepToString(pixel2D));
		for(int i=0; i < nheight; i++)
		{
			for(int j=0; j< nwidth; j++)
			{
				
				if (pixel2D[i][j] == -65332)
				{
					count++;
					if(count == 1)
					{
						Log.i("start1", i+ "");
					}
					else if (count == 5856)
					{
						Log.i("end1", i+ "");
					}
				}
				else if (pixel2D[i][j] == -6710989)
				{
					count1++;
					if(count1 == 1)
					{
						Log.i("start2", i+ "");
					}
					else if (count1 == 4392)
					{
						Log.i("end2", i+ "");
					}
				}
				else if (pixel2D[i][j] == -10079233)
				{
					count2++;
					if(count2 == 1)
					{
						Log.i("start3", i+ "");
					}
					else if (count2 == 4392)
					{
						Log.i("end3", i+ "");
					}
				}
				else
				{
					
				}
				
			}
		}
	}
	
	public void getHMday()//creates hashmap for each day with how many of each color pixel in each line
	{
		for(int i=0; i < nheight; i++)
		{
			for(int j=0; j< nwidth; j++)
			{
				if (j== 30)//Sunday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countSu.containsKey(pixel2D[i][j]))
						{
							pixel_countSu.put(pixel2D[i][j], pixel_countSu.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countSu.put(pixel2D[i][j], 1);
						}
					}
				}
				else if (j ==92)//Monday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countMo.containsKey(pixel2D[i][j]))
						{
							pixel_countMo.put(pixel2D[i][j], pixel_countMo.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countMo.put(pixel2D[i][j], 1);
						}
					}
				}
				else if (j == 154)//Tuesday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countTu.containsKey(pixel2D[i][j]))
						{
							pixel_countTu.put(pixel2D[i][j], pixel_countTu.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countTu.put(pixel2D[i][j], 1);
						}
					}
				}
				else if (j == 216)//Wednesday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countWe.containsKey(pixel2D[i][j]))
						{
							pixel_countWe.put(pixel2D[i][j], pixel_countWe.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countWe.put(pixel2D[i][j], 1);
						}
					}
				}
				else if (j == 278)//Thursday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countTh.containsKey(pixel2D[i][j]))
						{
							pixel_countTh.put(pixel2D[i][j], pixel_countTh.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countTh.put(pixel2D[i][j], 1);
						}
					}
					
				}
				else if (j == 340)//Friday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countFr.containsKey(pixel2D[i][j]))
						{
							pixel_countFr.put(pixel2D[i][j], pixel_countFr.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countFr.put(pixel2D[i][j], 1);
						}
					}
					
				}
				else if (j == 402)//Saturday
				{
					if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))//excludes white and gray colors
					{
						if (pixel_countSa.containsKey(pixel2D[i][j]))
						{
							pixel_countSa.put(pixel2D[i][j], pixel_countSa.get(pixel2D[i][j])+1);
						}
						else
						{
							pixel_countSa.put(pixel2D[i][j], 1);
						}
					}
				}
				
			
			}
		}
	}
	
	public void outputMap (HashMap<Integer, Integer> m1)
	{
		Set<Integer> keyset = m1.keySet();
		for(Integer s: keyset)//displays how many pixels of each color
		{
			Log.i(s+ "", m1.get(s)+"");
		}
		
	}
	
	public void get_times()
	{
		Set<Integer> Sukeyset = pixel_countSu.keySet();
		Set<Integer> Mokeyset = pixel_countMo.keySet();
		Set<Integer> Tukeyset = pixel_countTu.keySet();
		Set<Integer> Wekeyset = pixel_countWe.keySet();
		Set<Integer> Thkeyset = pixel_countTh.keySet();
		Set<Integer> Frkeyset = pixel_countFr.keySet();
		Set<Integer> Sakeyset = pixel_countSa.keySet();
		
		Log.i("Day", "Sunday");
		for(Integer s: Sukeyset)
		{
			Log.i("Color", s+ "");
			int count = 0;
			Log.i("hi", nheight+"");
			for(int i=0; i < nheight; i++)
			{
				
				for(int j=0; j< nwidth; j++)
				{
					Log.i("hi", "hi2");
					if (j== 30)
					{
						Log.i("hi1", "hi3");
						if (pixel2D[i][j] == s)
						{
							
							count++;
							Log.i("Count", count + "");
							if(count == 1)
							{
								Log.i("start", i +"");
							
							}
							else if(count == pixel_countSu.get(s))
							{
								Log.i("end", i +"");
							}
						}
					}
				}
			}
				
			
			
		}
		
		Log.i("Day", "Monday");
		for(Integer s: Mokeyset)
		{
			Log.i("Color", s+ "");
			int count = 0;
			for(int i=0; i < nheight; i++)
			{
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 92)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							//Log.i("Count",pixel2D[i][j] +"" );
							if (pixel2D[i][j] == s)
							{
								//Log.i("Count", "foo");
								count++;
								//Log.i("Count", count + "");
								if(count == 1)
								{
									Log.i("start", i +"");
								
								}
								else if(count == pixel_countMo.get(s))
								{
									Log.i("end", i +"");
								}
							}
						}
					}
				}
			}
				
			
			
		}
	
	}
	
	

	
			
		
		
	
		
  

}
