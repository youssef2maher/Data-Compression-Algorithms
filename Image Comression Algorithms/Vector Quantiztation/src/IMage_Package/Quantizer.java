package IMage_Package;

import java.io.IOException;
import java.util.ArrayList;

public class Quantizer {

	IMage img = new IMage();
	ArrayList<Label> labels = new ArrayList<Label>();
	float[][] avg;
	int width;
	int height;
	
	
	public Quantizer() {
		
		width = 8;
		height = 8;
		avg = new float[width][height];
	}
	
	
	
	
	
	public IMage getImg() {
		return img;
	}





	public void setImg(IMage img) {
		this.img = img;
	}





	public ArrayList<Label> getLabels() {
		return labels;
	}





	public void setLabels(ArrayList<Label> labels) {
		this.labels = labels;
	}





	public float[][] getAvg() {
		return avg;
	}





	public void setAvg(float[][] avg) {
		this.avg = avg;
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





	public void generateLabels() throws IOException {
		
		int[][] imagePixels = img.getImagePixels(); 
		
		for (int i = 0; i < img.getWidth(); i+=width) {
			
			for (int j = 0; j < img.getHeight(); j+=height) {
				
				int[][] pix = new int[8][8];
				
				for (int k = 0; k < width; k++) {
					
					for (int s = 0; s < height; s++) {
						
						pix[k][s] = imagePixels[i+k][j+s];
//						System.out.println(imagePixels[i+k][j+s]);
					}
				}
				
				Label label = new Label();
				label.setLabel(pix);
				labels.add(label);
			}
		}
	}
	
	
	
	
	
	public void generateAvg() {
		
		for (int j = 0; j < width; j++) {
			
			for (int k = 0; k < height; k++) {
				
				float avgPix = 0;
				for (int i = 0; i < labels.size(); i++) {
					
					int[][] labelPix = labels.get(i).getLabel();
					avgPix += labelPix[j][k];
//					System.out.print(avgPix + " ");
				}
				avg[j][k] = (float) (avgPix/labels.size());
				System.out.print(avg[j][k] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	
	public void split() {
		
		
	}
	
	
	
}





