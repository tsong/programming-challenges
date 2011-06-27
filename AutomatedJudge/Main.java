import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		
		int run = 0;
		while (in.hasNextLine()) {
			run++;
			
			int n = in.nextInt(); in.nextLine();
			if (n == 0) break;
			
			String solution = "";
			for (int i = 0; i < n; i++)
				solution += in.nextLine() + "\n";
			
			int m = in.nextInt(); in.nextLine();
			String submission = "";
			for (int i = 0; i < m; i++)
				submission += in.nextLine() + "\n";
			
			//System.out.printf("[%s]\n[%s]\n", solution, submission);
			
			
			boolean totalMatch = totalMatch(submission, solution);
			boolean presentationMatch = presentationMatch(submission, solution);
			
			System.out.printf("Run #%d: ", run);
			if (totalMatch) 
				System.out.println("Accepted");
			else if (presentationMatch)
				System.out.println("Presentation Error");
			else
				System.out.println("Wrong Answer");
		}
	}
	
	public static boolean totalMatch(String s1, String s2) {
		return s1.equals(s2);
	}
	
	public static boolean presentationMatch(String s1, String s2) {
		String r1 = s1.replaceAll("\\D", "");
		String r2 = s2.replaceAll("\\D", "");
		
		return r1.equals(r2);
	}
}
