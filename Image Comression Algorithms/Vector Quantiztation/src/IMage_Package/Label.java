package IMage_Package;

public class Label {

	int[][] label;
	int width;
	int height;
	
	
	
	public Label() {
		
		label = new int[8][8];
		width = 8;
		height = 8;
	}
	
	
	
	public Label(int width, int height) {
		
		label = new int[width][height];
		this.width = width;
		this.height = height;
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



	public int[][] getLabel() {
		return label;
	}



	public void setLabel(int[][] label) {
		this.label = label;
	}
	
	
	
	
	
	
	
	
}
