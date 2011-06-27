import java.util.*;

public class Main {
	public static final int INFINITY = 999999;
	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
				//get names
				int n = in.nextInt(); in.nextLine();
				String [] names = new String[n];
				for (int i = 0; i < n; i++) 
					names[i] = in.nextLine();
				
				//get ballots
				LinkedList<int[]> ballots = new LinkedList<int[]>();
				while (in.hasNextLine()) {
					String line = in.nextLine();
					//System.out.println(line);
					if (line.isEmpty()) break;
					
					int [] ballot = new int[n];
					String [] ranking = line.split("\\s+");
					
					for (int i = 0; i < n; i++) {
						//ballot[i] = Integer.parseInt(ranking[i]);
						int choice = Integer.parseInt(ranking[i]);
						ballot[choice-1] = i+1;
					}
					
					ballots.add(ballot);
				}
				
				printWinners(names, ballots);
				if (t != cases-1) System.out.println();
		}
	}
	
	public static void printWinners(String [] names, LinkedList<int[]> ballots) {
		int n = names.length;
		int remaining = n;
		Set<Integer> eliminated = new HashSet<Integer>();
		
		while (true) {
			//calculate votes
			int [] votes = new int[n];
			for (int[] ballot : ballots) {
				int index = minIndex(ballot);
				votes[index]++;
			}
			
			//System.out.printf("%s: %s\n", "eliminated", eliminated);
			//System.out.printf("%s: %s\n", "votes", Arrays.toString(votes));
			
			//find least votes
			int min = -1;
			for (int i = 0; i < n; i++) {
				if (eliminated.contains(i)) continue;
				if (min < 0) min = votes[i];
				min = Math.min(min,votes[i]);
			}
			TreeSet<Integer> toEliminate = new TreeSet<Integer>();
			for (int i = 0; i < n; i++) {
				if (eliminated.contains(i)) continue;
				if (votes[i] == min) toEliminate.add(i);
			}
			
			//System.out.printf("%s: %s (min = %d)\n", "toEliminate", toEliminate.toString(), min);
			
			//check for final elimination
			remaining -= toEliminate.size();
			if (remaining == 0) break;
			eliminated.addAll(toEliminate);
			
			
			//eliminate candidates
			for (int[] ballot : ballots) {
				for(int i : toEliminate) {
					ballot[i] = INFINITY;
				}
			}
		}
		
		//print
		for (int i = 0; i < n; i++) {
			if (!eliminated.contains(i)) {
				System.out.println(names[i]);
			}
		}
	}
	
	public static int minIndex(int [] A) {
		if (A.length == 0) return -1;
		
		int min = A[0];
		int index = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[i] < min) {
				min = A[i];
				index = i;
			}
		}
		
		return index;
	}
	
	
}