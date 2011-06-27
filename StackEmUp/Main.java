import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
	
		int cases = in .nextInt();
		for (int t = 0; t < cases; t++) {
			//new deck
			int [] deck = new int[52];
			for (int i = 0; i < 52; i++) deck[i] = i;
			
			int n = in.nextInt();
			int [][] shuffles = new int[n][52];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 52; j++) {
					shuffles[i][j] = in.nextInt()-1;
				}
			}
			
			in.nextLine();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.isEmpty()) break;
				
				int k = Integer.parseInt(line)-1;
				int [] newDeck = new int[52];
				for (int j = 0; j < 52; j++) {
					newDeck[j] = deck[ shuffles[k][j] ];
				}
				deck = newDeck;
			}
			
			printDeck(deck);
			if (t != cases -1) System.out.println();
		}
	}
	
	static String [] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	static String [] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	public static void printDeck(int [] deck) {
		for (int i = 0; i < 52; i++) {
			int v = deck[i] % 13;
			int s = deck[i] / 13;
			System.out.printf("%s of %s\n", VALUES[v], SUITS[s]);
		}
	}
}
