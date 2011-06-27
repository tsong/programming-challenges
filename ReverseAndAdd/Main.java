import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			long n = in.nextInt();
			
			int i = 0;
			for (i = 0; i < 1000; i++) {
				if (isPalindrome(n)) break;
				long r = reverse(n);
				n = n + r;
			}
			
			System.out.printf("%d %d\n", i, n);
		}
	}
	
	public static long reverse(long n) {
		char [] C = ("" + n).toCharArray();
		for (int i = 0; i < C.length/2; i++) {
			char tmp = C[i];
			C[i] = C[C.length-1-i];
			C[C.length-1-i] = tmp;
		}
		
		return Long.parseLong(new String(C));
	}
	
	public static boolean isPalindrome(long n) {
		char [] C = ("" + n).toCharArray();
		
		for (int i = 0; i < C.length/2; i++) {
			if (C[i] != C[C.length-1-i]) 
				return false;
		}
	
		return true;
	}
}
