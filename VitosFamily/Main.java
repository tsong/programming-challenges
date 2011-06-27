import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			int r = in.nextInt();
			int [] relatives = new int[r];
			for (int i = 0; i < r; i++) 
				relatives[i] = in.nextInt();
			
			int d = 9999999;
			for (int i = 0; i <= 30000; i++) {
				d = Math.min(d, minSum(relatives,i));
			}
			
			System.out.println(d);
		}
	}
	
	public static int minSum(int [] A, int v) {
		int sum = 0;
		for (int a : A) {
			sum += Math.abs(v-a);
		}
		
		return sum;
	}
}
