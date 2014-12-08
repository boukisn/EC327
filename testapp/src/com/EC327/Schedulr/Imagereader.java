package com.EC327.Schedulr;


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
	public int height;
	public int width;
	public int nheight;
	public int nwidth;
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
	public HashMap<String, ArrayList<String>> day_time = new HashMap<String,ArrayList<String>>();
	public ArrayList<String> final_list = new ArrayList<String>();
	
	
	


	public void dimensionfy(){// turns pixar array into a 2D array
		
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
		height = bmp.getHeight();
		width =  bmp.getWidth();
		rebmp = Bitmap.createBitmap(bmp, 55, 20, width-195, height-50);
		nheight = rebmp.getHeight();
		nwidth =  rebmp.getWidth();
	}
	
	
	public void getPixels(Bitmap img)// will put pixels of a certain bitmap int the pixar array
	{
		pixar = new int[img.getHeight()*img.getWidth()];
		
		img.getPixels(pixar, 0, img.getWidth(), 0, 0, img.getWidth() , img.getHeight());
		//ArrayList<Integer> colors = new ArrayList<Integer>();
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
	
	
	public void get_times()// determines the times for the classes each day
	{
		Set<Integer> Sukeyset = pixel_countSu.keySet();
		Set<Integer> Mokeyset = pixel_countMo.keySet();
		Set<Integer> Tukeyset = pixel_countTu.keySet();
		Set<Integer> Wekeyset = pixel_countWe.keySet();
		Set<Integer> Thkeyset = pixel_countTh.keySet();
		Set<Integer> Frkeyset = pixel_countFr.keySet();
		Set<Integer> Sakeyset = pixel_countSa.keySet();
		
		ArrayList<String> sunday = new ArrayList<String>();
		for(Integer s: Sukeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 30)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countSu.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			sunday.add(s1);
			
			
		}
		
		day_time.put("Sunday", sunday);
		
		ArrayList<String> monday = new ArrayList<String>();
		for(Integer s: Mokeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 92)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countMo.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			monday.add(s1);
			
			
		}
		
		day_time.put("Monday", monday);
		
		ArrayList<String> tuesday = new ArrayList<String>();
		for(Integer s: Tukeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 154)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countTu.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			tuesday.add(s1);
			
			
		}
		
		day_time.put("Tuesday", tuesday);
		ArrayList<String> wednesday = new ArrayList<String>();
		for(Integer s: Wekeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 216)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countWe.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			wednesday.add(s1);
			
			
		}
		
		day_time.put("Wednesday", wednesday);
		
		
		ArrayList<String> thursday = new ArrayList<String>();
		for(Integer s: Thkeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 278)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countTh.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			thursday.add(s1);
			
			
		}
		
		day_time.put("Thursday", thursday);
		
		
		ArrayList<String> friday= new ArrayList<String>();
		for(Integer s: Frkeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 340)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countFr.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			friday.add(s1);
			
			
		}
		
		day_time.put("Friday", friday);
		
		ArrayList<String> saturday = new ArrayList<String>();
		for(Integer s: Sakeyset)
		{
			
			int count = 0;
			String s1 = "";
			for(int i=0; i < nheight; i++)
			{
				
				
				for(int j=0; j< nwidth; j++)
				{
					
					if (j== 402)
					{
						if ((pixel2D[i][j] != -1) && (pixel2D[i][j] != -3355444))
						{
							
							if (pixel2D[i][j] == s)
							{
								
								count++;
								
								if(count == 1)
								{
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
									
								
								}
								else if(count == pixel_countSa.get(s))
								{
									//Log.i("end", i +"");
									s1+=".";
									if(i <= 5 && i>= 1 )
									{
										s1+="7:00AM";
									}
									else if(i <= 16 && i>= 11 )
									{
										s1+="7:30AM";
									}
									else if(i <= 28 && i>= 24 )
									{
										s1+="8:00AM";
									}
									else if(i <= 40 && i>= 35 )
									{
										s1+="8:30AM";
									}
									else if(i <= 52 && i>= 45 )
									{
										s1+="9:00AM";
									}
									else if(i <= 64 && i>= 59 )
									{
										
										s1+="9:30AM";
									}
									else if(i <= 78 && i>= 71 )
									{
										s1+="10:00AM";
									}
									else if(i <= 88 && i>= 80 )
									{
										s1+="10:30AM";
									}
									else if(i <= 100 && i >= 90  )
									{
										s1+="11:00AM";
									}
									else if(i <= 115 && i>= 105 )
									{
										s1+="11:30AM";
									}
									else if(i <= 124 && i>= 118 )
									{
										s1+="12:00PM";
									}
									else if(i <= 135 && i>= 128 )
									{
										s1+="12:30PM";
									}
									else if(i <= 147 && i>= 140 )
									{
										s1+="1:00PM";
									}
									else if(i <= 159 && i>= 153 )
									{
										s1+="1:30PM";
									}
									else if(i <= 171 && i>= 164 )
									{
										
										s1+="2:00PM";
									}
									else if(i <= 183 && i>= 175 )
									{
										s1+="2:30PM";
									}
									else if(i <= 195 && i>= 188 )
									{
										s1+="3:00PM";
									}
									else if(i <= 207 && i>= 200 )
									{
										s1+="3:30PM";
									}
									else if(i <= 219 && i>= 211 )
									{
										
										s1+="4:00PM";
									}
									else if(i <= 231 && i>= 223 )
									{
										s1+="4:30PM";
									}
									else if(i <= 240 && i>= 234 )
									{
										s1+="5:00PM";
									}
									else if(i <= 254 && i>= 246 )
									{
										s1+="5:30PM";
									}
									else if(i <= 265 && i>= 259 )
									{
										s1+="6:00PM";
									}
									else if(i <= 276 && i>= 270 )
									{
										s1+="6:30PM";
									}
									else if(i <= 289 && i>= 282 )
									{
										s1+="7:00PM";
									}
									else if(i <= 300 && i>= 293 )
									{
										s1+="7:30PM";
									}
									else if(i <= 312 && i>= 305 )
									{
										s1+="8:00PM";
									}
									else if(i <= 324 && i>= 316 )
									{
										s1+="8:30PM";
									}
									else if(i <= 335 && i>= 328 )
									{
										s1+="9:00PM";
									}
									else if(i <= 347 && i>= 340 )
									{
										s1+="9:30PM";
									}
									else if(i <= 358 && i>= 352 )
									{
										s1+="10:00PM";
									}
									else if(i <= 370 && i>= 363 )
									{
										s1+="10:30PM";
									}
									else 
									{
										s1+="11:00PM";
									}
								}
							}
						}
					}
				}
				
			}
				
			saturday.add(s1);
			
			
		}
		
		day_time.put("Saturday", saturday);
		
		for (String day: day_time.keySet()){

            String key = day;
            String value = day_time.get(day).toString();  
            Log.i("Final Map" , key + " " + value);  


} 
	}
	
	
public void HM_to_string()// converts hashmap of day and times into an ArrayList of strings to pass on
{
	//String output;

	for (String day: day_time.keySet()){

        String key = day;
        for (String f1: day_time.get(day))
        {
        	if(f1.equals("7:00AM.11:00PM") == false)
        	{
        		final_list.add(day + ": " + f1);
        	}
        }
          
        
	}
}

	

	
			
		
		
	
		
  

}
