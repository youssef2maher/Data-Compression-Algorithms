package IMage_Package;

import java.io.IOException;
import java.util.ArrayList;

public class CodeBook {

	
	float[][] avg;
	Quantizer quantizer;
	ArrayList<CodeBookLabel> cdLabels = new ArrayList<CodeBookLabel>();
	
	
	public CodeBook() {
		
		avg = new float[8][8];
		quantizer = new Quantizer();
		
		try {
			quantizer.generateLabels();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		avg = quantizer.getAvg();
	}
	
	
	
	public void generateCodeBook() {
		
		int[][] lowerAvg = new int[avg.length][avg.length];
		int[][] upperAvg = new int[avg.length][avg.length];
		
		for (int i = 0; i < avg.length; i++) {
			
			for (int j = 0; j < avg.length; j++) {
				
				lowerAvg[i][j] = (int) avg[i][j];
				lowerAvg[i][j] = (int) avg[i][j] + 1;
			}
		}
		
		CodeBookLabel cdLabel1 = new CodeBookLabel();
		cdLabel1.setCodeBookValue(lowerAvg);
		cdLabel1.generateAvg();
		
		CodeBookLabel cdLabel2 = new CodeBookLabel();
		cdLabel2.setCodeBookValue(upperAvg);
		cdLabel2.generateAvg();
		
		cdLabels.add(cdLabel1);
		cdLabels.add(cdLabel2);
		
		if (cdLabels.size() < 8) {
			
			generateCodeBook();
			System.out.println(cdLabels.size());
		} else {
			System.out.println("Stop");
		}
	}
	
}



