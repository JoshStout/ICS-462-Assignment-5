import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		String str1 = randomReferences();
		String str2 = "0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2";
		String str3 = "7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1";
		
		// PrinterWriter & I/O File code copied from ICS-340 InitialCodebase 
		// by Metropolitan State University Professor Michael Stein 
		PrintWriter output = null;
		File outputFile;
		outputFile = new File( "output.txt" );
		if ( outputFile.exists() ) {
			outputFile.delete();
		}
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		
		
		// loop thru 1-7 page frames for first string
		for(int i = 1; i < 8; i++) {
			System.out.print("For " + i + " page frames, and using string page reference string: ");
			output.print("For " + i + " page frames, and using string page reference string: ");
			
			System.out.println(str1);
			output.print(str1 + "\n");
			
			FIFO f1 = new FIFO(str1, i);
			System.out.println("   FIFO had " + f1.getFaults() + " faults.");
			output.print("   FIFO had " + f1.getFaults() + " faults.\n");
			
			LRU l1 = new LRU(str1, i);
			System.out.println("   LRU had " + l1.getFaults() + " faults.");
			output.print("   LRU had " + l1.getFaults() + " faults.\n");
			
			Optimal o1 = new Optimal(str1, i);
			System.out.println("   Optimal had " + o1.getFaults() + " faults.");
			output.print("   Optimal had " + o1.getFaults() + " faults.\n");
			
			System.out.println();
			output.print("\n");
		}
		
		System.out.println();
		output.print("\n");
		
		// loop thru 1-7 page frames for second string
		for(int i = 1; i < 8; i++) {
			System.out.println("For " + i + " page frames, and using string page reference string:");
			output.print("For " + i + " page frames, and using string page reference string: ");
			
			System.out.println(str2);
			output.print(str2 + "\n");
			
			FIFO f2 = new FIFO(str2, i);
			System.out.println("   FIFO had " + f2.getFaults() + " faults.");
			output.print("   FIFO had " + f2.getFaults() + " faults.\n");
			
			LRU l2 = new LRU(str2, i);
			System.out.println("   LRU had " + l2.getFaults() + " faults.");
			output.print("   LRU had " + l2.getFaults() + " faults.\n");
			
			Optimal o2 = new Optimal(str2, i);
			System.out.println("   Optimal had " + o2.getFaults() + " faults.");
			output.print("   Optimal had " + o2.getFaults() + " faults.\n");
			
			System.out.println();
			output.print("\n");
		}
		
		System.out.println();
		output.print("\n");
		
		// loop thru 1-7 page frames for third string 
		for(int i = 1; i < 8; i++) {
			System.out.println("For " + i + " page frames, and using string page reference string:");
			output.print("For " + i + " page frames, and using string page reference string: ");
			
			System.out.println(str3);
			output.print(str3 + "\n");
			
			FIFO f3 = new FIFO(str3, i);
			System.out.println("   FIFO had " + f3.getFaults() + " faults.");
			output.print("   FIFO had " + f3.getFaults() + " faults.\n");
			
			LRU l3 = new LRU(str3, i);
			System.out.println("   LRU had " + l3.getFaults() + " faults.");
			output.print("   LRU had " + l3.getFaults() + " faults.\n");
			
			Optimal o3 = new Optimal(str3, i);
			System.out.println("   Optimal had " + o3.getFaults() + " faults.");
			output.print("   Optimal had " + o3.getFaults() + " faults.\n");
			
			System.out.println();
			output.print("\n");
		}
		
		output.flush();
		
	}
	
	// static method to return a String of 20 numbers with random values between 0 and 9 
	public static String randomReferences() {
		Random r = new Random();
		int num;
		String s = "";
		for(int i = 0; i < 20; i++) {
			num = r.nextInt(10);
			s = s.concat(String.valueOf(num) + ",");
		}
		
		// remove last comma before returning String
		return s.substring(0, s.length() - 1);
	}

}
