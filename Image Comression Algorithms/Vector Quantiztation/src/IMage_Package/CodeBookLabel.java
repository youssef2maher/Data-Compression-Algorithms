package IMage_Package;

import java.util.ArrayList;

public class CodeBookLabel {

	ArrayList<Label> labels = new ArrayList<Label>();
	int width;
	int height;
	int[][] codeBookValue;
	float[][] avg;
	
	
	
	public void CodeBookLabel() {
		
		width = 8;
		height = 8;
		codeBookValue = new int[8][8];
	}
	
	
	
	
	public ArrayList<Label> getLabels() {
		return labels;
	}




	public void setLabels(ArrayList<Label> labels) {
		this.labels = labels;
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




	public float[][] getAvg() {
		return avg;
	}




	public void setAvg(float[][] avg) {
		this.avg = avg;
	}




	public int[][] getCodeBookValue() {
		return codeBookValue;
	}




	public void setCodeBookValue(int[][] codeBookValue) {
		this.codeBookValue = codeBookValue;
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
	
}
