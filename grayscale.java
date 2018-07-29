//Convert RGB image into grayscale

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class grayscale
{
	public static void main(String args [])
	{
		BufferedImage img = null;
		File f = null;
		try
		{
			f = new File("H:\\DSCN3193.JPG");
			img = ImageIO.read(f);
			
			 
			int x,y,a,r,g,b,p,avg;
			
			int width = img.getWidth();
			int height = img.getHeight();
			
			for(y=0 ; y<height ; y++)
				for(x=0 ; x<width ; x++)
				{
					p = img.getRGB(x,y);
					a = (p>>24) & 0xFF;
					r = (p>>16) & 0xFF;
					g = (p>>8) & 0xFF;
					b = p & 0xFF;
					
					avg = (r+g+b)/3;
			
					p = (a<<24) | (avg<<16) | (avg<<8) | avg ;
			
					img.setRGB(x,y,p);
				}
			
			f= new File("H:\\Output.jpg");
			ImageIO.write(img,"jpg",f);
			
		}catch(Exception e)
		{
			System.out.println("Exception = "+e);
		}
	}
}