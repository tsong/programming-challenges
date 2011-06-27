import java.util.*;

public class Main {
	static Map<String,Boolean> map = new HashMap<String,Boolean>();

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		while (in.hasNextInt()) {
			long n = in.nextInt();
		
			System.out.printf("%s wins.\n", minimax(n,1,true) > 0 ? "Stan" : "Ollie");
		}
	}
	
	public static int minimax(long n, long p, boolean max) {
		if (p >= n) {
			return max ? -1 : 1;
		}
		
		String key = n + " " + p;
		
		if (!map.containsKey(key)) {
			int m = minimax(n, p*2, !max);
			for (int i = 3; i <= 9; i++) {
				if ((max && m > 0) || (!max && m < 0)) break;
				
				int v = minimax(n, p*i, !max);
				m = max ? Math.max(m,v) : Math.min(m,v);
			}
			
			boolean canWin = (m > 0 && max) || (m < 0 && !max) ? true : false;
			map.put(key,canWin);
		}
		
		boolean canWin = map.get(key);
		return (canWin && max) || (!canWin && !max) ? 1 : -1;
	}
}
