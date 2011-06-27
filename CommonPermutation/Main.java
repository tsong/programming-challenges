import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			char [] s1 = in.nextLine().toCharArray();
			char [] s2 = in.nextLine().toCharArray();
			
			String common = "";
			for (int i = 0; i < s1.length; i++) {
				char c = s1[i];
				for (int j = 0; j < s2.length; j++) {
					if (s2[j] == c) {
						s2[j] = '\0';
						common += c;
						break;
					}
				}
			}
			
			
			char [] x = common.toCharArray();
			Arrays.sort(x);
			System.out.println(new String(x));
		}
	}
}
