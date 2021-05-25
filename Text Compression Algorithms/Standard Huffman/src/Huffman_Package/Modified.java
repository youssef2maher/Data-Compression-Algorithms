package Huffman_Package;

import java.io.File;
import java.util.Map;
import java.util.Vector;

public class Modified extends Huffman {

	
	public void other(float prob) {
		
		float otherProb = 0;
		Vector<Float> removedProb = new Vector();
		for (Map.Entry<Float, Character> entry : charsProbabilities.entrySet()) {
			
			if (entry.getKey() < prob) {
				otherProb += entry.getKey();
				removedProb.add(entry.getKey());
			}
		}
		
		for (int i = 0; i < removedProb.size(); i++) {
			
			charsProbabilities.remove(removedProb.get(i));
		}
		
		charsProbabilities.put(otherProb, '@');
	}
	

	
	
	
	public void compressModified(File file, float otherProb) {
		
		setCompressedFile(file);
		
		getAllDifferentChars();

		setCharsWithProbabilities();
		
		other(otherProb);

		sortCharsProbabilities();
			
		setTree();
		
		assignBinaryCode();
		
		compression();

		createTableFile();
		
		createCompressedFile();
		
	}
	
	
	
	
	public void printCharsProbs() {
		
		for (Map.Entry<Float, Character> entry : charsProbabilities.entrySet()) {
			
			System.out.println(entry.getValue() + " " + entry.getKey());
		}
	}
	
	

	
}
