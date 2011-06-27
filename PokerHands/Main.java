import java.util.*;

public class Main {
	public static HashMap<Character,Integer> SUIT = new HashMap<Character,Integer>();
	public static char [] SUIT_REV = {'H', 'D', 'S', 'C'};
	
	public static HashMap<Character,Integer> VALUE = new HashMap<Character,Integer>();
	public static char [] VALUE_REV = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
	
	public static void main(String [] args) {
		for (int i = 0; i < SUIT_REV.length; i++) {
			SUIT.put(SUIT_REV[i], i);
		}
		
		for (int i = 0; i < VALUE_REV.length; i++) {
			VALUE.put(VALUE_REV[i], i);
		}
		
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int [][] hands = new int[2][5];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 5; j++) {
					hands[i][j] = parse(in.next());
				}
			}
			
			long v1 = eval(hands[0]);
			long v2 = eval(hands[1]);
			
			//System.out.printf("%d %d\n", v1, v2);
			if (v1 > v2) 
				System.out.println("Black wins.");
			else if (v2 > v1)
				System.out.println("White wins.");
			else
				System.out.println("Tie.");
		}
	}
	
	public static int parse(String s) {
		char value = s.charAt(0);
		char suit = s.charAt(1);

		int v = SUIT.get(suit)*13 + VALUE.get(value);
		
		//System.out.printf("%s (%s %s) => %s %s\n", s, VALUE.get(value), SUIT.get(suit), v%13, v/13);
		
		return v;
	}
	
	public static void printHand(String heading, int [] h) {
		/*System.out.print(heading + ": ");
		for (int v : h) {
			System.out.print(VALUE_REV[v%13] + "" + SUIT_REV[v/13] + " ");
		}
		System.out.println();*/
	}
	
	public static long eval(int [] h) {
		long stage = 10, rank = -1;
				
		if ((rank = straightFlush(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = four(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = fullHouse(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = flush(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = straight(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = three(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = twoPairs(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = pair(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		if ((rank = high(h)) != -1) return (stage<<32) | rank;
		stage--;
		
		return -1;
	}
	
	public static int[] stripSuit(int [] hand) {
		int [] suitStripped = new int[5];
		for (int i = 0; i < 5; i++) 
			suitStripped[i] = hand[i] % 13;
		
		Arrays.sort(suitStripped);
		return suitStripped;
	}
	
	public static int[] stripValue(int [] hand) {
		int [] valueStripped = new int[5];
		for (int i = 0; i < 5; i++)
			valueStripped[i] = hand[i] / 13;
		
		Arrays.sort(valueStripped);
		return valueStripped;
	}
	
	public static TreeMap<Integer,Integer> getCount(int [] hand) {
		hand = stripSuit(hand);
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		for (int v : hand) {
			if (!map.containsKey(v)) {
				map.put(v, 0);
			}
			
			map.put(v, map.get(v)+1);
		}
		
		return map;
	}
	
	public static int high(int [] hand) {
		int [] orig = hand;
		hand = stripSuit(hand);
		
		int rank = 0, base = 1;
		for (int i = 0; i < 5; i++) {
			rank += base*hand[i];
			base *= 13;
		}
		
		printHand("High", orig);
		return rank;
	}
	
	public static int pair(int [] hand) {
		TreeMap<Integer,Integer> map = getCount(hand);
		
		int pair = -1;
		for (int v : map.keySet()) {
			if (map.get(v) == 2) {
				pair = Math.max(pair,map.get(v));
			}
		}
		
		if (pair < 0) return -1;
		
		int rank = 0, base = 1;
		for (int v : map.keySet()) {
			if (v == pair) continue;
			rank += base*v;
			base *= 13;
		}
		
		printHand("Pair", hand);
		return base*pair + rank;
	}
	
	public static int twoPairs(int [] hand) {
		TreeMap<Integer,Integer> map = getCount(hand);
		int p1 = -1, p2 = -1, s = -1;
		for (int v : map.keySet()) {
			if (map.get(v) == 2 && v > p1) {
				p2 = p1;
				p1 = v;
			} else if (map.get(v) == 2 && v > p2) {
				p2 = v;
			} else {
				s = v;
			}
		}
		
		if (p1 < 0 || p2 < 0 || s < 0) return -1;
		
		printHand("Two Pairs", hand);
		return 1*s + 13*p2 + 13*13*p1;
	}
	
	public static int three(int [] hand) {	
		TreeMap<Integer,Integer> map = getCount(hand);
		
		int t = -1;
		for (int v : map.keySet()) {
			if (map.get(v) == 3) t = 3;
		}
		
		if (t < 0) return -1;
		
		int rank = 0, base = 1;
		for (int v : map.keySet()) {
			if (v == t) continue;
			rank += base*v;
			base *= 13;
		}
		
		printHand("Three", hand);
		return base*t + rank;
	}
	
	public static int straight(int [] hand) {
		int [] orig = hand;
		
		hand = stripSuit(hand);
		int last = hand[0];
		for (int i = 1; i < 5; i++) {
			if (hand[i] != last+1) return -1;
			last = hand[i];
		}
		
		printHand("Straight", orig);
		return last;
	}
	
	public static int flush(int [] hand) {
		int [] suits = stripValue(hand);
		int s = suits[0];
		for (int i = 1; i < 5; i++) {
			if (suits[i] != s) return -1;
		}
		
		printHand("Flush", hand);
		return high(hand);
	}
	
	public static int fullHouse(int [] hand) {
		TreeMap<Integer,Integer> map = getCount(hand);
		
		int t = -1, p = -1;
		for (int v : map.keySet()) {
			if (map.get(v) == 3) {
				t = v;
			} else if (map.get(v) == 2) {
				p = v;
			}
		}
		
		if (t < 0 || p < 0) return -1;
		
		printHand("Full House", hand);
		return 1*p + 13*t;
	}
	
	public static int four(int [] hand) {
		TreeMap<Integer,Integer> map = getCount(hand);
		int f = -1, s = -1;
		for (int v : map.keySet()) {
			if (map.get(v) == 4) 
				f = v;
			else
				s = v;
		}
		
		if (f < 0 || s < 0) return -1;
		
		printHand("Four", hand);
		return 1*s + 13*f;
	}
	
	public static int straightFlush(int [] hand) {
		int s = straight(hand);
		if (s < 0) return -1;
		int f = flush(hand);
		if (f < 0) return -1;
				
		printHand("Straight Flush", hand);
		return s;
	}
}
