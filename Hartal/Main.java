import java.util.*;

public class Main {	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			int n = in.nextInt();
			int p = in.nextInt();
			
			int [] days = new int[n];
			for (int i = 0; i < p; i++) {
				int h = in.nextInt();
				for (int j = h-1; j < n; j += h) {
					if (j % 7 != 5 && j % 7 != 6) 
						days[j] = 1;
				}
			}
			
			//count days
			int count = 0;
			for (int v : days) count += v;
			System.out.println(count);
		}
	}
}
