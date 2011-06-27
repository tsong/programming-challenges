import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext()) {
			String line = in.nextLine();
			
			Scanner s = new Scanner(line);
			
			int n = s.nextInt();
			int [] V = new int[n];
			
			if (n == 1) {
				System.out.println("Jolly");
				continue;
			} else if (n == 0) {
				System.out.println("Not jolly");
				continue;
			}
			
			int a = s.nextInt();
			int b = s.nextInt();
			
			int d = Math.abs(a-b);
			if (d < n) 
				V[ Math.abs(a-b) ] = 1;
			
			while (s.hasNext()) {
				a = b;
				b = s.nextInt();
				
				d = Math.abs(a-b);
				if (d >= n) continue;
				V[ Math.abs(a-b) ] = 1;
			}
			
			//System.out.println(Arrays.toString(V));
			
			boolean jolly = true;
			for (int i = 1; i < n; i++) 
				if (V[i] != 1) {
					jolly = false;
					break;
				}
			
			System.out.println(jolly ? "Jolly" : "Not jolly");
		}
	}
}
