package IMage_Package;

import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO;

public class IMage {

	String filePath;
	File file;
	BufferedImage image = null;
	int width; 
	int height; 
	int[][] imagePixels;
	
	
	public IMage() {
		
		filePath = "C:\\Users\\Original-asd\\Desktop\\image.jpg";
		file = new File(filePath);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = image.getWidth();
        height = image.getHeight();
        imagePixels = new int[width][height];
        try {
			generateImagePixels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getFilePath() {
		return filePath;
	}




	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




	public BufferedImage getImage() {
		return image;
	}




	public void setImage(BufferedImage image) {
		this.image = image;
	}




	public int getWidth() {
		return width;
	}




	public void setWidth(int width) {
		this.width = width;
	}




	public int getHeight() {
		return height;
	}




	public void setHeight(int height) {
		this.height = height;
	}




	public int[][] getImagePixels() {
		return imagePixels;
	}




	public void setImagePixels(int[][] imagePixels) {
		this.imagePixels = imagePixels;
	}




	public void generateImagePixels() throws IOException {
		
		for (int i = 0; i < width; i++) {
			
			for (int j = 0; j < height; j++) {
				
				int rgb = image.getRGB(i, j);
				int r = (rgb >> 0) & 0xff;
				imagePixels[i][j] = r;
//				System.out.print(r + " ");
			}
//			System.out.println();
		}
		
	}
	
}





