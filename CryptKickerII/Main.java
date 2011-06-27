import java.util.*;

public class Main {
	static String fox = "the quick brown fox jumps over the lazy dog";
	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt(); in.nextLine();
		in.nextLine();
		
		for (int t = 0; t < cases; t++) {
			
			List<String> lines = new LinkedList<String>();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.isEmpty()) break;	
				lines.add(line);
			}
			
			char [] map = null;
			for (String line : lines) {
				if (line.length() == fox.length()) {
					map = getMap(line,fox);
					if (map != null) break;
				}
			}
			
			if (map == null) {
				System.out.println("No solution.");
			} else {
				for (String line : lines) {
					System.out.println(decrypt(map,line));
				}
			}
			
			if (t != cases-1) System.out.println();
		}
	}
	
	public static String decrypt(char [] map, String line) {
		char [] D = new char[line.length()];
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == ' ')
				D[i] = ' ';
			else 
				D[i] = map[c-'a'];
		}
		
		return new String(D);
	}
	
	public static char [] getMap(String line, String known) {
		char [] map = new char[26];
		Arrays.fill(map, '\0');
		
		for (int i = 0; i < known.length(); i++) {
			char c1 = line.charAt(i);
			char c2 = known.charAt(i);
			if (c1 == ' ' && c2 != ' ') return null;
			if (c1 == ' ' && c2 == ' ') continue;
		
			if (map[c1-'a'] != '\0' && map[c1-'a'] != c2) return null;
		
			map[c1-'a'] = c2;
		}
		
		return map;
	}
}
