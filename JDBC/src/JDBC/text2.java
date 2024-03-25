package JDBC;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class text2 {

	public void ascii20() {
		 
		

			
				// TODO Auto-generated method stub
				int width=150, height=23;
				int width1=150, height1=23;
				BufferedImage  Image1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics grap  =  Image1.getGraphics();
				grap.setFont(new Font("SansSerif", Font.BOLD, 18));
				
				Graphics2D grap2D = (Graphics2D) grap ;
				grap2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				grap2D.drawString("     E-BANK", 10, 18);
				 
			
				for (int y = 0; y <  height; y++) 
				{
					    StringBuilder stringBuilder = new StringBuilder();
				
					    for (int x = 0; x <  width; x++)
					    {
					        stringBuilder.append(Image1.getRGB(x, y) == -16777216 ? "." : "E");
					    }
				
					    if (stringBuilder.toString().trim().isEmpty())
					    {
					        continue;
					    }
				
					    try{
					    	System.out.println(stringBuilder);
					    	Thread.sleep(100);
					    }
					    catch(Exception e)
					    {
					    	System.out.println("");
					    }
					    
				}

				
			}

		

	}


