package Huffman_Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Huffman {

	public String compressedString = new String("");
	public String decompressedString = new String("");
	public String original = new String("");
	public Vector<Character> differentChars = new Vector();
	public Map<Float, Character> charsProbabilities = new HashMap<Float, Character>();
	public Map<Float, String> tree = new HashMap<Float, String>(); 
	public Map<String, String> codes = new HashMap<String, String>(); 
	public File compressedFile;
	public File decompressedFile;
	
	public Vector<Float> charsProbabilitiesFloat = new Vector();
	public Vector<Character> charsProbabilitiesString = new Vector();
	
	
	public void setCompressedString(String str) {
		compressedString = str;
	}
	
	
	
	
	public void setCompressedFile(File file) {
		
		String stg = new String("");
		
		try {
			
			FileInputStream fin = new FileInputStream(file);
			int ch; 
	        while((ch=fin.read())!=-1) {
	        	stg += (char)ch;
	        }
	        fin.close();    
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		compressedString = stg;
		compressedFile = file;
	}
	
	
	
	
	
	
	
	
	public void setDecompressedFile(File file) {
		
		String stg = new String("");
		
		try {
			
			FileInputStream fin = new FileInputStream(file);
			int ch; 
	        while((ch=fin.read())!=-1) {
	        	stg += (char)ch;
	        }
	        fin.close();    
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		decompressedString = stg;
		decompressedFile = file;
	}
	
	
	
	
	
	
	
	public void setTableFile(File file) {
		
		try {
			
			FileInputStream fin = new FileInputStream(file);
			int ch; 
			
	        while((ch=fin.read())!=-1) {
	        	
	        	String chr = new String("");
				String bin = new String("");
				
	        	while((char)ch != '0' && (char)ch != '1') {
	        		chr += (char)ch;
	        		ch = fin.read();
	        	}
	        	
//	        	System.out.print(chr);
//	        	System.out.print(" ");
	        	
	        	while((char)ch == '0' || (char)ch == '1') {
	        		bin += (char)ch;
	        		ch = fin.read();
	        	}
	        	
//	        	System.out.print(bin);    	
//	        	System.out.println();
	        	
	        	codes.put(bin, chr);
	        }
	        fin.close();    
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
	
	
	
	public void createTableFile() {
		
		try {
			
			String newPath = compressedFile.getPath();
			newPath = newPath.substring(0, newPath.length() - 4) + " [TABLE]" + ".txt";
			
		    FileWriter fw=new FileWriter(newPath); 
		  
	        // read character wise from string and write  
	        // into FileWriter  
	        for (Map.Entry<String, String> entry : codes.entrySet()) {
	        	fw.write(entry.getKey() + " " + entry.getValue() + "\n");
	        }
	  
	        System.out.println("Writing successful"); 
	        //close the file  
	        fw.close(); 
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	public void createCompressedFile() {
		
		try {
			
			String newPath = compressedFile.getPath();
			newPath = newPath.substring(0, newPath.length() - 4) + " [COMPRESSED]" + ".txt";
			
		    FileWriter fw=new FileWriter(newPath); 
		  
	        // read character wise from string and write  
	        // into FileWriter  
	        
	        fw.write(decompressedString);
	        
	  
	        System.out.println("Writing successful"); 
	        //close the file  
	        fw.close(); 
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	public void createDecompressedFile() {
		
		try {
			
			String newPath = decompressedFile.getPath();
			newPath = newPath.substring(0, newPath.length() - 4) + " [DECOMPRESSED]" + ".txt";
			
		    FileWriter fw=new FileWriter(newPath); 
		  
	        // read character wise from string and write  
	        // into FileWriter  
	        
	        fw.write(original);
	        
	  
	        System.out.println("Writing successful"); 
	        //close the file  
	        fw.close(); 
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	public float getCharPrbability(char x) {
		
		float counter = 0;
		for (int i = 0; i < compressedString.length(); i++) {
			if (compressedString.charAt(i) == x) {
				counter++;
			}
		}
		return counter/compressedString.length();
	}
	
	
	
	public void getAllDifferentChars() {
		
		for (int i = 0; i < compressedString.length(); i++) {
			
			boolean check = true;
			for (int j = 0; j < differentChars.size(); j++) {
				if (compressedString.charAt(i) == differentChars.get(j)) {
					check = false;
					break;
				}
			}
			
			if (check) {
				differentChars.add(compressedString.charAt(i));
			}
		}
	}
	
	
	
	
	
	public void setCharsWithProbabilities() {
		
		for (int i = 0; i < differentChars.size(); i++) {
			charsProbabilities.put(getCharPrbability(differentChars.get(i)), differentChars.get(i));
		}
	}
	
	
	
	
	//Spare
	public void setCharsWithProbabilitiesVector() {
		
		for (int i = 0; i < differentChars.size(); i++) {
			
			charsProbabilitiesFloat.add(getCharPrbability(differentChars.get(i)));
			charsProbabilitiesString.add(differentChars.get(i));
		}
	}
	
	
	
	
	// Function to sort map by Key 
    public void sortCharsProbabilities() 
    { 
        // TreeMap to store values of HashMap 
        TreeMap<Float, Character> sorted = new TreeMap<>(); 
  
        // Copy all data from hashMap into TreeMap 
        sorted.putAll(charsProbabilities); 
  
      
    }
    
    
    
    
	
	
	
	public void setTree() {
		
		Map<Float, String> treeProbabilities = new HashMap<Float, String>();
		
		
		
		for (Map.Entry<Float, Character> entry : charsProbabilities.entrySet())  {
			char[] c = {charsProbabilities.get(entry.getKey())};
			String s = new String(c);

			treeProbabilities.put(entry.getKey(), s);
		}       
		
		
		//Sort
		
		// TreeMap to store values of HashMap 
        TreeMap<Float, String> sorted = new TreeMap<>(); 
  
        // Copy all data from hashMap into TreeMap 
        sorted.putAll(treeProbabilities); 
  

		
		while (sorted.size() > 2) {
			
	        //Iterator

	        Iterator<Map.Entry<Float, String>> itr = sorted.entrySet().iterator(); 
			Map.Entry<Float, String> entry = itr.next(); 

	        
			float f1, f2; String s1, s2;
			f1 = entry.getKey();
			s1 = entry.getValue();
			
			entry = itr.next();
			f2 = entry.getKey();
			s2 = entry.getValue();
			
			sorted.put(f1+f2, s1+s2);
			
			
			
			//Remove
			
			String a = sorted.remove(f1);
			String b = sorted.remove(f2);

			
			
		}
		
		tree.putAll(sorted);	
	}
	
	
	
	
	
	public String gp (String str) {
		
		float great = 0;
		for (int i = 0; i < str.length(); i++) {
			
			if (getCharPrbability(str.charAt(i)) > great) {
				great = getCharPrbability(str.charAt(i));
			}
		}
		
		char[] c = {charsProbabilities.get(great)};
		String s = new String(c);
		return s;
	}
	
	
	
	
	public String removeChar(String str, char c) {
		
		String stg = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != c) {
				stg += str.charAt(i);
			}
		}
		
		return stg;
	}
	
	
	
	
	public void bin(String branch, String branchCode, float prob, Map<String, String> map) {
		
		String great = gp(branch);
		
		map.put(great, branchCode+"1");
		
		char greatChar = great.charAt(0);
		branch = removeChar(branch, greatChar);
		
		if (branch.length() > 1) {
			map.put(branch, branchCode+"0");
			bin(branch, branchCode+"0", prob - getCharPrbability(greatChar), map);
		}
		
		else {
			map.put(branch, branchCode+"0");
		}
		
		
	}
	
	
	
	
	public void assignBinaryCode() {
		
		Map<String, String> bc = new HashMap<String, String>();
		
		String code = "0";
		for (Map.Entry<Float, String> entry : tree.entrySet()) {
			bc.put(entry.getValue(), code);
			code = "1";
		}
		
		for (Map.Entry<Float, String> entry : tree.entrySet()) {
			
			bin(entry.getValue(), bc.get(entry.getValue()), entry.getKey(), bc);
		}
		
		codes.putAll(bc);
		
	}
	
	
	
	
	
	
	public void compression() {
		
		for (int i = 0; i < compressedString.length(); i++) {
			
			String thisChar = new String("");
			thisChar += compressedString.charAt(i);
			decompressedString += codes.get(thisChar);
		
		}
		
	}
	
	
	
	
	
	
	public void decompression() {
		
		String bin = new String("");
		for (int i = 0; i < decompressedString.length(); i++) {
			
			bin += decompressedString.charAt(i);
			
			String code = codes.get(bin);
			
			if (code.length() == 2) {

				code = code.substring(0, 1);
				original += code;
				bin = "";
			}
		}	
	}
	
	
	
	
	
	
	public void compress(File file) {
		
		setCompressedFile(file);
		
		getAllDifferentChars();

		setCharsWithProbabilities();

		sortCharsProbabilities();
			
		setTree();
		
		assignBinaryCode();
		
		compression();

		createTableFile();
		
		createCompressedFile();
		
	}
	
	
	
	
	
	public void decompress(File file, File table) {
		
		setTableFile(table);
		
		setDecompressedFile(file);
		
		decompression();
		
		createDecompressedFile();
	}
	
	
	
	

	public void printCodes() {
		
		for (Map.Entry<String, String> entry : codes.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
	}
	
	
	
	public void printDecompressedString() {
		
		System.out.println(decompressedString);
	}
	
	
	
	public void printOriginal() {
		
		System.out.println(original);
	}
	
	
}
