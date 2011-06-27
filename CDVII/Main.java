import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);	
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			if (t != 0) System.out.println();
			
			int [] toll = new int[24];
			for (int i = 0; i < 24; i++)
				toll[i] = in.nextInt();
			in.nextLine();
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				if (line.isEmpty()) break;
				String [] tokens = line.split("\\s+");
			}
			
		}
	}
}
