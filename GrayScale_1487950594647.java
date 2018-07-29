import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GrayScale {

  public static void toGray(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        Color c = new Color(image.getRGB(j, i));
        int red = (int)(c.getRed() * 0.21);
        int green = (int)(c.getGreen() * 0.72);
        int blue = (int)(c.getBlue() *0.07);
        int sum = red + green + blue;
        Color newColor = new Color(sum,sum,sum);
        image.setRGB(j,i,newColor.getRGB());
      }
    }
  }

  static public void main(String args[]) throws IOException {
    File input = new File("D:\\project\\aj.jpg");
    BufferedImage image = ImageIO.read(input);
    toGray(image);
    File output = new File("D:\\project\\out.jpg");
    ImageIO.write(image, "jpg", output);
  }
}