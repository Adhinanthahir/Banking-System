package JDBC;
	 
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import java.awt.image.BufferedImage;

	public class textgames {

		public void usertext(String g, int l,int size,String symbol, String back) {
			 
			

			String strUpper = g.toUpperCase();
					// TODO Auto-generated method stub
					int width=250, height=23;
					 
					BufferedImage  Image1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
					Graphics grap  =  Image1.getGraphics();
					grap.setFont(new Font("SansSerif", Font.BOLD, size));
					
					Graphics2D grap2D = (Graphics2D) grap ;
					grap2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					grap2D.drawString( strUpper, l, size);
					 
				
					for (int y = 0; y <  height; y++) 
					{
						    StringBuilder stringBuilder = new StringBuilder();
					
						    for (int x = 0; x <  width; x++)
						    {
						        stringBuilder.append(Image1.getRGB(x, y) == -16777216 ? back : symbol);
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


