package AC_Binary;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ACB acb = new ACB();
		
		File file = new File("C:\\Users\\Original-asd\\Desktop\\doc.txt");
		acb.readForComp(file);
		acb.compress();
		acb.writeForComp();
		
//		acb.compress();
//		System.out.println("Code ==> " + acb.getCompCode());
//		
//		acb.decompress();
//		System.out.println(acb.getDcompString());
	}

}
