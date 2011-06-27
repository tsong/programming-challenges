import java.util.*;

public class Main {
	static Map<Integer,Integer> solved;
	static int [][] wrong;
	static int [] penalty;
	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		int cases = in.nextInt();
		in.nextLine();
		in.nextLine();
		for (int t = 0; t < cases; t++) {
			solved = new TreeMap<Integer,Integer>();
			wrong = new int[100][10];
			penalty = new int[100];
			boolean [][] problems = new boolean[100][10];
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.isEmpty()) break;
				String [] tokens = line.split(" ");
				
				int C = Integer.parseInt(tokens[0])-1;
				int P = Integer.parseInt(tokens[1])-1;
				int T = Integer.parseInt(tokens[2]);
				char L = tokens[3].charAt(0);
				
				if (!solved.containsKey(C)) {
					solved.put(C,0);
				}
				
				if (L == 'E' || L == 'U' || L == 'R') continue;
			
				if (L == 'C') {
					if (!problems[C][P]) {
						penalty[C] += (wrong[C][P]*20 + T);
						solved.put(C, solved.get(C)+1);
						problems[C][P] = true;
					}
				} else if (L == 'I') {
					wrong[C][P]++;
				}
			}
			
			
			Set<Integer> contestants = solved.keySet();
			List<Integer> list = new ArrayList<Integer>(contestants.size());
			list.addAll(contestants);
			Collections.sort(list, new Comparator<Integer>() {
				public int compare(Integer a, Integer b) {
					if (solved.get(a) > solved.get(b))
						return -1;
					else if (solved.get(a) < solved.get(b))
						return 1;
					
					
					if (penalty[a] < penalty[b])
						return -1;
					else if (penalty[a] > penalty[b])
						return 1;
					
					return a < b ? -1 : 1;
				}
			});
			
			for (int C : list) {
				System.out.printf("%d %d %d\n", C+1, solved.get(C), penalty[C]);
			}
			
			if (t != cases-1) System.out.println();
			
		}
	}
}
