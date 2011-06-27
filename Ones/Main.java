import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			BigInteger n = new BigInteger("" + in.nextInt());
			
			BigInteger base = new BigInteger("1");
			BigInteger ones = new BigInteger("1");
			BigInteger ten = new BigInteger("10");
			BigInteger zero = new BigInteger("0");
			
			int i = 1;
			while (true) {
				if (ones.mod(n).equals(zero)) break;
				
				base = base.multiply(ten);
				ones = ones.add(base);
				i++;
			}
			
			System.out.println(i);
		}
	}
}
