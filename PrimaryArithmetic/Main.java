import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int a = in.nextInt();
			int b = in.nextInt();
			if (a == 0 && b == 0) break;
			
			int numCarries = 0;
			int carry = 0;
			int base = 1;
			for (int i = 0; i < 10; i++) {
				int d1 = (a%(base*10))/base;
				int d2 = (b%(base*10))/base;
				
				if (d1 + d2 + carry > 9) {
					carry = 1;
					numCarries++;
				} else {
					carry = 0;
				}
				
				base *= 10;
			}
			

			if (numCarries == 0) {
				System.out.println("No carry operation.");
			} else if (numCarries == 1) {
				System.out.println("1 carry operation.");
			} else {
				System.out.printf("%d carry operations.\n", numCarries);
			}
			
		}
	}
}
