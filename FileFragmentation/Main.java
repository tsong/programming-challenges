import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt(); in.nextLine();
		in.nextLine();
		
		for (int t = 0; t < cases; t++) {
			LinkedList<String> fragments = new LinkedList<String>();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.isEmpty()) break;
				fragments.add(line);
			}
			
			Collections.sort(fragments, new Comparator<String>() {
				public int compare(String s1, String s2) {
					if (s1.length() < s2.length()) 
						return -1;
					else if (s1.length() > s2.length())
						return 1;
					else
						return 0;
				}
			
			});
			
			String file = fragments.getFirst() + fragments.getLast();
			if (isValid(file,fragments)) {
				System.out.println(file);
			} else {
				System.out.println(fragments.getLast() + fragments.getFirst());
			}
			
			if (t != cases-1)
				System.out.println();
		}
	}
	
	public static boolean isValid(String file, List<String> fragments) {
		int n = fragments.size();
		
		for (int i = 0; i < n/2; i++) {
			String f1 = fragments.get(i);
			String f2 = fragments.get(n-1-i);
			
			if (!(file.equals(f1+f2) || file.equals(f2+f1))) {
				return false;
			}
		}
		
		return true;
	}
}
