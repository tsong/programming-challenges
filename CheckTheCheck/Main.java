import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
	
		int game = 0;
		while(true) {
			game++;
			char [][] board = new char[100][100];
			for (int i = 0; i < 100; i++)
				for (int j = 0; j < 100; j++)
					board[i][j] = '.';
			
			boolean blank = true;
			for (int i = 10; i < 18; i++) {
				String line = in.nextLine();
				for (int j = 10; j < 18; j++) {
					board[i][j] = line.charAt(j-10);
					if (board[i][j] != '.') blank = false;
				}
			}
			
			if (blank) break;
			in.nextLine();
			
			String king = solve(board);
			
			System.out.printf("Game #%d: %s king is in check.\n", game, king);
		}
	}
	
	
	public static String solve(char [][] B) {
		for (int i = 10; i < 18; i++) {
			for (int j = 10; j < 18; j++) {
				if (B[i][j] == '.') continue;
				boolean black = Character.isLowerCase(B[i][j]);
				char king = black ? 'K' : 'k';
				
				boolean hit = check(B, i, j, king);
				if (hit)
					return black ? "white" : "black";
			}
		}
		
		
		return "no";
	}
	
	public static boolean check(char [][] B, int i, int j, char king) {
		char c = Character.toLowerCase(B[i][j]);
		boolean black = Character.isLowerCase(B[i][j]);
		
		boolean hit = false;
		
		switch(c) {
			case 'p':
				if (black)
					hit = B[i+1][j-1] == king || B[i+1][j+1] == king;
				else
					hit = B[i-1][j-1] == king || B[i-1][j+1] == king;
				break;
			case 'r':
				hit = rook(B,i,j,king);
				break;
			case 'b':
				hit = bishop(B,i,j,king);
				break;
			case 'q':
				hit = rook(B,i,j,king) || bishop(B,i,j,king);
				break;
			case 'n':
				hit = knight(B,i,j,king);
				break;
		}
		
		
		return hit;
	}


	public static boolean rook(char [][] B, int i, int j, char king) {
		boolean hit = false;
		

		
		//up
		for (int y = 1; y < 8; y++) {
			if (B[i+y][j] == '.') continue;
			hit = B[i+y][j] == king;
			break;
		}
		
		if (hit) return hit;
		
		//down
		for (int y = 1; y < 8; y++) {
			if (B[i-y][j] == '.') continue;
			hit = B[i-y][j] == king;
			break;
		}
		
		if (hit) return hit;
		
		//left
		for (int x = 1; x < 8; x++) {
			if (B[i][j+x] == '.') continue;
			hit = B[i][j+x] == king;
			break;
		}
		
		if (hit) return hit;
		
		//right
		for (int x = 1; x < 8; x++) {
			if (B[i][j-x] == '.') continue;
			hit = B[i][j-x] == king;
			break;
		}
		
		return hit;
	}
	
	
	public static boolean bishop(char [][] B, int i, int j, char king) {
		boolean hit = false;
		
		//up
		for (int y = 1; y < 8; y++) {
			if (B[i+y][j+y] == '.') continue;
			hit = B[i+y][j+y] == king;
			break;
		}
		
		if (hit) return hit;
		
		//down
		for (int y = 1; y < 8; y++) {
			if (B[i-y][j-y] == '.') continue;
			hit = B[i-y][j-y] == king;			
			break;
		}
		
		if (hit) return hit;
		
		//left
		for (int x = 1; x < 8; x++) {
			if (B[i-x][j+x] == '.') continue;
			hit = B[i-x][j+x] == king;
			break;
		}
		
		if (hit) return hit;
		
		//right
		for (int x = 1; x < 8; x++) {
			if (B[i+x][j-x] == '.') continue;
			hit = B[i+x][j-x] == king;
			break;
		}
		
		return hit;
	}
	
	public static boolean knight(char [][] B, int i, int j, char king) {
		int [][] D = { {-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,-2}, {2,-1}, {1,2}, {2,1} };
		
		for (int [] d : D) {
			if ( B[i+d[0]][j+d[1]] == king) return true;
		}
		
		return false;
	}
}
