import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

class currency
{
	BufferedImage currency=null;
	File f=null;
	int a;
	BufferedImage cropped=null;
		
	public static void main(String args[])
	{
		File f=null;
		File output=null;
		currency c = new currency();
		
		c.getImage();
		c.convertGrayscale();
		f=new File("H:\\coding\\cropped1.JPG");
		output = new File("H:\\coding\\binary1.txt");
		c.cropImage(839,140,254,316,f);
		c.convertBinary(f,output);
		f=new File("H:\\coding\\cropped2.JPG");
		output = new File("H:\\coding\\binary2.txt");
		c.cropImage(478,207,247,127,f);
		c.convertBinary(f,output);
	}
	
	public void getImage()			//Get the Image
	{
		try
		{
			f=new File("H:\\coding\\100.JPG");
			currency = ImageIO.read(f);
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred "+e);
		}
	}
	public void convertGrayscale()			//Convert Image to Grayscale
	{
		int flag=0;
		try
		{
			int x,y,a,r,g,b,p,avg;
			
			int width = currency.getWidth();
			int height = currency.getHeight();
			
			for(y=0 ; y<height ; y++)
				for(x=0 ; x<width ; x++)
				{
					p = currency.getRGB(x,y);
					a = (p>>24) & 0xFF;
					r = (p>>16) & 0xFF;
					g = (p>>8) & 0xFF;
					b = p & 0xFF;
					
					avg = (r+g+b)/3;
			
					p = (a<<24) | (avg<<16) | (avg<<8) | avg ;
			
					currency.setRGB(x,y,p);
				}
			f= new File("H:\\coding\\grayscale.jpg");
			ImageIO.write(currency,"jpg",f);
			flag=1;
		}
		catch(Exception e)
		{	
			System.out.println("Exception Occurred "+e);
		}
		
		finally
		{
			if(flag==1)
				System.out.println("\nImage Successfully Converted to Grayscale.\t\tLocation : H:\\coding\\grayscale.jpg");
			else
				System.out.println("Error Occurred....Conversion to Grayscale Unsuccessful.");
		}
	}
	
	public void cropImage(int x, int y, int w, int h, File f)			//Crop the Image
	{
		int flag=0;
		try
		{
			//f=new File("H:\\coding\\cropped.JPG");
			cropped = currency.getSubimage(x,y,w,h);
			ImageIO.write(cropped,"jpg",f);
			flag=1;
		}
		catch(Exception e)
		{
			System.out.println("Exception Occurred "+e);
		}
		finally
		{
			if(flag==1)
				System.out.println("\nGrayscale Image Successfully Cropped.\t\t\tLocation : H:\\coding\\cropped.jpg");
			else
				System.out.println("Error Occurred....Cropping Unsuccessful.");
		}
	}

	public void convertBinary(File f, File output)			//Convert Image into Binary String
	{
		int flag=0;
		try
		{
			//f=new File("H:\\coding\\cropped1	.JPG");
			//File output = new File("H:\\coding\\binary.txt");
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
		    BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			int read,count=0,i;
			String text;
			String []t=new String[60000];
			while ((read=bis.read()) != -1) 
			{
              text = Integer.toString(read,2);
			  
              while (text.length() < 8) 
			  {
                    text="0"+text;
              }
              bw.write(text+"\n");
			  t[count]=text;
			  count++;
			}
			flag=1;
		}
		catch(Exception e)
		{
			System.out.println("Exception Occurred "+e);
		}
		finally
		{
			if(flag==1)
				System.out.println("\nCropped Image Successfully converted to Binary file.\tLocation : H:\\coding\\binary.txt");
			else
				System.out.println("Error Occurred....Cropping Unsuccessful.");
		}
	}
	
}