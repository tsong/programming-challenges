import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int n = in.nextInt();
			if (n == 0) break;
			
			long [] costs = new long[n];
			long sum = 0;
			for (int i = 0; i < n; i++) {
				String x = in.next();
				x = x.replace(".", "");
				
				costs[i] = Integer.parseInt(x);
				sum += costs[i];
			}
			
			//System.out.println(Arrays.toString(costs));
			
			long avg = sum / n;
			//System.out.println("AVG: " + avg);
			
			
			long give = 0;
			for (int i = 0; i < n; i++) {
				if (costs[i] <= avg) continue;
				give += costs[i] - (avg + 1);
			}
			
			long need = 0;
			for (int i = 0; i < n; i++) {
				if (costs[i] >= avg) continue;
				need += avg - costs[i];
			}
			
			//System.out.println(give + " " + need);
			
			long transfer = give < need ? need : give; 
			System.out.printf("$%.2f\n", transfer/100.0);
		}
	}
}
