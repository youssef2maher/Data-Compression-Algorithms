package IMage_Package;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		IMage image = new IMage();
		int[][] var = image.getImagePixels();
//		System.out.println(var[170][170]);
//		System.out.println(image.imagePixels[170][170]);
		
		
		
		
		
		
//		Quantizer q = new Quantizer();
//		q.generateLabels();
//		ArrayList<Label> labels = q.getLabels();
//		Label label = labels.get(100);
//		int[][] pix = label.getLabel();
//		
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				System.out.print(pix[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("\n");
//		for (int i = 0; i < 8; i++) {
//			for (int j = 800; j < 808; j++) {
//				System.out.print(var[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		
		
		
//		Quantizer q = new Quantizer();
//		q.generateLabels();
//		q.generateAvg();
		
		
		
		
		CodeBook cd = new CodeBook();
		cd.generateCodeBook();
		
		
		
		
	}

}
