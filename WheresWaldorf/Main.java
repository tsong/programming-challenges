import java.util.*;

public class Main {
	static int [][] D = { {1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1} };
	
	
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			
			int m = in.nextInt();
			int n = in.nextInt();
			in.nextLine();
			
			char [][] grid = new char[m+10][n+10];
			for (char [] row : grid) Arrays.fill(row,'.');
			
			
			for (int i = 5; i < m+5; i++) {
				char [] line = in.nextLine().toCharArray();
				for (int j = 5; j < n+5; j++) {
					grid[i][j] = Character.toLowerCase(line[j-5]);
				}
			}
			
			/*for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					System.out.print(grid[i][j] + " ");
				}
				System.out.println();
			}*/
			
			int K = in.nextInt();
			for (int k = 0; k < K; k++) {
				String word = in.next().toLowerCase();
				//System.out.println(word);
				
				boolean found = false;
				for (int i = 5; i < m+5; i++) {
					for (int j = 5; j < n+5; j++) {
						if (findWord(grid,word,i,j)) {
							System.out.printf("%d %d\n", i-4, j-4);
							found = true;
							break;
						}
					}
					
					if (found) break;
				}
			}
			
			if (t != cases-1)
				System.out.println();
		}
	}
	
	public static boolean findWord(char [][] grid, String word, int i, int j) {
		char [] W = word.toCharArray();
		int n = word.length();
		
		for (int[] d : D) {
			int di = d[0];
			int dj = d[1];
			
			int ii = i;
			int jj = j;
			boolean found = true;
			for (int k = 0; k < n; k++) {
				if (grid[ii][jj] != W[k]) {
					found = false;
					break;
				}
				ii += di;
				jj += dj;
			}
			
			if (found) return true;
		}
		
		return false;
	}
}
