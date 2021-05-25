package LZ_Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LZ78 {

	public String compressedString = new String("");
	public String decompressedString = new String("");
	public Vector<String> table = new Vector();
	public Vector<String> dtable = new Vector();
	public Vector<Integer> tagIndex = new Vector();
	public Vector<Character> tagNextChar = new Vector();
	public File compressedFile;
	public File decompressedFile;
	
	
	
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
	
	
	
	
	
	public void setDecompressdFile(File file) {
		
		FileInputStream fin;
		try {
			
			fin = new FileInputStream(file);
			int ch; 
	        while((ch=fin.read())!=-1) {
	        	
	        	tagIndex.add((int)ch - 48);
	        	fin.read();
	        	ch = fin.read();
	        	tagNextChar.add((char) ch);
	        	fin.read();
	        	
	        }
	        fin.close(); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        decompressedFile = file;
		
	}
	
	
	
	
	
	public void createCompressedFile() {
		
		try {
			
			String newPath = compressedFile.getPath();
			newPath = newPath.substring(0, newPath.length() - 4) + " [COMPRESSED]" + ".txt";
			
		   FileWriter fw=new FileWriter(newPath); 
		  
	        // read character wise from string and write  
	        // into FileWriter  
	        for (int i = 0; i < tagIndex.size(); i++) {
				
	        	fw.write(tagIndex.get(i) + " " + tagNextChar.get(i) + "\n");
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
	
	
	
	
	
	
	public void createDecompressedFile() {
		
		try {
			
			String newPath = decompressedFile.getPath();
			newPath = newPath.substring(0, newPath.length() - 4) + " [DECOMPRESSED]" + ".txt";
			
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
	
	
	
	
	
	
	
	public void compression() {
		
		table.add(" ");
		String newEntry = new String("");
		int indx;
		char nextChar;
		int tableIndx = 0;
		int thisj = 0;
		
		for (int i = 0; i < compressedString.length()-1; i++) {
			
			newEntry += compressedString.charAt(i);
			
			indx = 0;
			nextChar = compressedString.charAt(i);
			
			boolean check = false;
			
			for (int j = 0; j < table.size(); j++) {
				
				if (newEntry.equals(table.get(j))) {
					check = true;
					thisj = j;
					break;
				}
			}
			
			if (!check) {
				
				indx = thisj;
				thisj = 0;
				nextChar = compressedString.charAt(i);
				
				tableIndx++;
				table.add(newEntry);
				tagIndex.add(indx);
				tagNextChar.add(nextChar);
				
				newEntry = "";
			}
		}
		
		tagIndex.add(0);
		tagNextChar.add(compressedString.charAt(compressedString.length()-1));
	}
	
	
	
	
	public void decompression() {
		
		dtable.add(" ");
		
		for (int i = 0; i < tagIndex.size(); i++) {
			
			if (tagIndex.get(i) == 0) {
				
				String tableString = new String("");
				tableString += tagNextChar.get(i);
				dtable.add(tableString);
				decompressedString += tableString;
				tableString = "";
			}
			
			else {
				
				String tableString = new String("");
				tableString = dtable.get(tagIndex.get(i)) + tagNextChar.get(i);
				dtable.add(tableString);
				decompressedString += tableString;
				tableString = "";
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void compress(File file) {
		
		setCompressedFile(file);
		compression();
		createCompressedFile();
	}
	
	
	
	
	
	public void decompress(File file) {
		
		setDecompressdFile(file);
		decompression();
		createDecompressedFile();
	}
	
	
	
	
	
	
	public void printTags() {
		
		for (int i = 0; i < tagIndex.size(); i++) {
			System.out.println("<" + tagIndex.get(i) + "," + tagNextChar.get(i) + ">");
		}
	}
	
	
	public void printTable() {
		
		for (int i = 0; i < table.size(); i++) {
			System.out.println(i + " " + table.get(i));
		}
	}
	
	
	
	
	public String getDecompressedString() {
		return decompressedString;
	}
	
	
	
	public void printDTable() {
		
		for (int i = 0; i < dtable.size(); i++) {
			System.out.println(i + " " + dtable.get(i));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
