package AC_Binary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ACB {
	
	
	private ArrayList<Character> chars = new ArrayList();
	private float[] bounds;
	private float[] newBounds;
	
	private String scallingCode = new String();
	private int numberOfBits = 12;
	private int numberOfChars = 3;
	
	private String compString = "";
	private String compCode = "";
	
	private String decompCode = "110001100000";
	private String decompString = "";
	
	
	
	ACB() {
		
//		chars.add('A');
//		chars.add('B');
//		chars.add('C');
		
//		bounds = new float[chars.size() * 2];
//		newBounds = new float[compString.length() * 2];
		
//		bounds[0] = (float) 0;
//		bounds[1] = (float) 0.8;
//		
//		
//		bounds[2] = (float) 0.8;
//		bounds[3] = (float) 0.82;
//		
//		
//		bounds[4] = (float) 0.82;
//		bounds[5] = (float) 1;
	}
	
	
	
	
	public void readForComp (File file) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		bounds = new float[numberOfChars * 2];
		String st; 
		float lower = 0, upper = 0;
		int i = 0;
		
		
		while ((st = br.readLine()) != null) {

			if (i < numberOfChars*2) {
				
				chars.add(st.charAt(0));
//				System.out.println(st.charAt(0) + " ");
				
				String value = st.substring(2);
				
				lower = upper;
				bounds[i] = lower;
//				System.out.println(bounds[i] + " ");
				
				upper += Float.valueOf(value);
				bounds[i+1] = upper;
//				System.out.println(bounds[i+1] + " ");
				
				i+=2;
				
//				System.out.println("con");
				continue;
			}
			
			compString += st;
			System.out.println(compString);
		} 
		
		newBounds = new float[compString.length() * 2];
	}
	
	
	
	
	
	
	public void writeForComp () throws IOException {

        FileWriter fw=new FileWriter("C:\\Users\\Original-asd\\Desktop\\doc[comp].txt"); 
  
        for (int i = 0; i < compCode.length(); i++) {
            fw.write(compCode.charAt(i)); 
        }
 
        fw.close();
	}
	
	
	
	
	
	
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
	public String getCompCode() {
		return compCode;
	}
	
	public void setDecompString(String decompString) {
		this.decompString = decompString;
	}
	
	public String getDcompString() {
		return decompString;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Calculate the lower bound of the character probability
	 * @param lower
	 * @param upper
	 * @param oldLower
	 * @return
	 */
	public float calcLower(float lower, float upper, float oldLower) {
		
		float result = lower + ((upper - lower) * oldLower);
		return result;
	}
	
	
	
	/**
	 * Calculate the upper bound of the character probability
	 * @param lower
	 * @param upper
	 * @param oldUpper
	 * @return
	 */
	public float calcUpper(float lower, float upper, float oldUpper) {
		
		float result = lower + (upper - lower) * oldUpper;
		return result;
	}
	
	
	
	/**
	 * Scalling E1 and E2
	 * @param lower
	 * @param upper
	 */
	public String scalling(int l, int u) {
		
		String scall = "";
		while (true) {
			
			if (newBounds[l] < 0.5 && newBounds[u] > 0.5) {
				break;
			}
			
			else if (newBounds[l] < 0.5 && newBounds[u] < 0.5) {
				newBounds[l] *= 2;
				newBounds[u] *= 2;
				scallingCode += '0';
				scall += '0';
			}
			
			else if (newBounds[l] > 0.5 && newBounds[u] > 0.5) {
				newBounds[l] = (float) ((newBounds[l] - 0.5)*2);
				newBounds[u] = (float) ((newBounds[u] - 0.5)*2);
				scallingCode += '1';
				scall += '1';
//				System.out.println("lower ==> " + newBounds[l] + " upper ==> " + newBounds[u]);
			}
		}
		return scall;
	}
	
	
	
	
	
	public int getCharIndex(char x) {
		
		for (int i = 0; i < chars.size(); i++) {
			if (chars.get(i) == x) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	public void getCompressedCode() {
		
		compCode += scallingCode;
		compCode += '1';
		
		int zero = numberOfBits - compCode.length();
		for (int i = 0; i < zero; i++) {
			compCode += '0';
		}
	}
	
	
	
	
	
	public void compress() {
		
		for (int i = 0; i < compString.length(); i++) {
			
			float lower = 0, upper = 0, oldLower = 0, oldUpper = 0;
			
			int index = getCharIndex(compString.charAt(i));
			int lowerIndex = index*2;
			int upperIndex = lowerIndex + 1;
			
			if (i > 0) {
				lower = newBounds[i*2 - 2];
				upper = newBounds[i*2 - 1];
				//System.out.println("curr ==> " + lower + " " + upper);
			} else {
				lower = bounds[0];
				upper = bounds[1];
				//System.out.println("curr ==> " + lower + " " + upper);
			}
			
			oldLower = bounds[lowerIndex];
			oldUpper = bounds[upperIndex];
			//System.out.println("old ==> " + oldLower + " " + oldUpper);
			
			if (i > 0) {
				newBounds[i*2] = calcLower(lower, upper, oldLower);
				newBounds[i*2 + 1] = calcUpper(lower, upper, oldUpper);
				//System.out.println("new ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
			} else { 
				newBounds[i*2] = lower;
				newBounds[i*2 + 1] = upper;
				//System.out.println("new ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
			}
			
			scalling(i*2, i*2 + 1);
			//System.out.println("scl ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
		}
		
		getCompressedCode();
		
	}
	
	
	
	
	
	
//	public String shift(int shamt) {
//		
//		String binary = "";
//		int subindex = 0;
//		
//		for (int i = decompCode.length()-1; i > 0; i--) {
//			if (decompCode.charAt(i) == '1') {
//				subindex = i;
//				break;
//			}
//		}
//		
//		binary = decompCode.substring(0, subindex);
//		return binary;
//	}
	
	
	
	
	
	public float convert(String decompCode) {
		
		String binary = decompCode;
		float decimal = 0;
		
//		System.out.println(binary);
		
		int binarySum = 0, binaryPower = -1;
		for (int i = binary.length()-1; i >= 0; i--) {
			
			binaryPower++;
			if (binary.charAt(i) == '1') {
				binarySum += Math.pow(2, binaryPower);
			}
		}
		
		decimal = (float) (binarySum / Math.pow(2, binary.length()));
//		System.out.println("Decimal inside fun ==> " + decimal);
		
		return decimal;
	}
	
	
	
	
	public int getCompChar(float num) {
		
		for (int i = 0; i < bounds.length; i+=2) {
			
			if (bounds[i] < num && bounds[i+1] > num) {
				
				decompString += chars.get(i/2);
//				System.out.println("HI");
				return i/2;
//				System.out.println(chars.get(i/2));
			}
		}
		return -1;
	}
	
	
	
	
	public float getCode(float oldCode, float lower, float upper) {
		
		return (oldCode - lower) / (upper - lower);
	}
	
	
	
	
	public void decompress() {
		
		int beg = 0, fin = 6, i = 0;
		float decimal = convert(decompCode.substring(beg, fin));
		
		while (true) {
			
			float lower = 0, upper = 0, oldLower = 0, oldUpper = 0;
//			System.out.println("Code ==> " + decompCode.substring(beg, fin));
//			System.out.println("Decimal ==> " + decimal);
			
			int ichar = getCompChar(decimal);
//			System.out.println("Char index ==> " + ichar);
			
			if (i > 0) {
				lower = newBounds[i*2 - 2];
				upper = newBounds[i*2 - 1];
//				System.out.println("curr ==> " + lower + " " + upper);
			} else {
				lower = bounds[0];
				upper = bounds[1];
//				System.out.println("curr ==> " + lower + " " + upper);
			}
			
			oldLower = bounds[ichar*2];
			oldUpper = bounds[ichar*2 + 1];
//			System.out.println("old ==> " + oldLower + " " + oldUpper);
			
			if (i > 0) {
				newBounds[i*2] = calcLower(lower, upper, oldLower);
				newBounds[i*2 + 1] = calcUpper(lower, upper, oldUpper);
//				System.out.println("new ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
			} else { 
				newBounds[i*2] = lower;
				newBounds[i*2 + 1] = upper;
//				System.out.println("new ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
			}
			
//			System.out.println();
//			System.out.println();
			
			if (convert(decompCode.substring(beg, fin)) == 0.5) {
				break;
			}
			
			String scall = scalling(i*2, i*2 + 1);
			beg += scall.length();
			fin += scall.length();
			
			
			decimal = getCode(convert(decompCode.substring(beg, fin)), newBounds[i*2], newBounds[i*2 + 1]);
			
//			System.out.println("Code ==> " + convert(decompCode.substring(beg, fin)));
//			System.out.println("new ==> " + newBounds[i*2] + " " + newBounds[i*2 + 1]);
//			System.out.println("new Decimal ==> " + decimal);
//			System.out.println("beg ==> " + beg);
//			System.out.println("beg ==> " + fin);
//			System.out.println();
//			System.out.println();
			
			i++;
			
		}
	}
	
	
	
	
	
	
	
	
	

}
