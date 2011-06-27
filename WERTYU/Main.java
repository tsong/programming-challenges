import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			char [] C = line.toCharArray();
			for (int i = 0; i < C.length; i++) {
				C[i] = shift(C[i]);
			}
			
			System.out.println(new String(C));
		}
	}
	
	static char [] keys = "`1234567890-=qwertyuiop[]\\asdfghjkl;\'zxcvbnm,./".toCharArray();
	
	public static char shift(char c) {
		char l = Character.toLowerCase(c);
		if (l == ' ') return l;
		
		int i;
		for (i = 0; i < keys.length; i++) {
			if (keys[i] == l) break;
		}
		
		return Character.toUpperCase(keys[i-1]);
	}
}

