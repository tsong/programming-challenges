import java.util.*;

public class Main {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		
		for (int t = 0; t < cases; t++) {
			int p = in.nextInt();
			int n = in.nextInt();
			in.nextLine();
			
			Node erdos = new Node("Erdos, P.");
			Map<String, Node> map = new HashMap<String,Node>();
			map.put("Erdos, P.", erdos);
			
			for (int i = 0; i < p; i++) {
				String line = in.nextLine().split(":")[0].replaceAll("\\s", "");
				String [] tokens = line.split(",");
				
				List<Node> cowriters = new LinkedList<Node>();
				for (int j = 0; j < tokens.length; j += 2) {
					String name = tokens[j];
					if (j != tokens.length-1) name += ", " + tokens[j+1];
					
					Node node = getNode(map,name);
					for (Node co : cowriters) {
						node.add(co);
						co.add(node);
					}
					
					cowriters.add(node);
				}
			}
			
			
			
			System.out.printf("Scenario %d\n", t+1);
			for (int i = 0; i < n; i++) {
				String name = in.nextLine();
				String [] tokens = name.replaceAll("\\s", "").split(",");
				name = tokens[0];
				if (tokens.length > 1) name += ", " + tokens[1];
				
				Node node = map.get(name);
				if (node == null) {
					System.out.printf("%s %s\n", name, "infinity");
				} else {
					int dist = node.shortest(erdos);
					System.out.printf("%s %s\n", name, dist < 0 ? "infinity" : "" + dist);
				}
			}
		}
	}
	
	public static Node getNode(Map<String, Node> map, String name) {
		if (!map.containsKey(name)) {
			Node node = new Node(name);
			map.put(name,node);
		}
		
		return map.get(name);
	}
}

class Node {
	public String name;
	public List<Node> children;
	
	public Node(String n) {
		name = n;
		children = new LinkedList<Node>();
	}
	
	public void add(Node n) {
		if (!children.contains(n))
			children.add(n);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public int shortest(Node dst) {
		Set<Node> visited = new HashSet<Node>();
		Queue<Node> q = new LinkedList<Node>();
		Queue<Node> o = new LinkedList<Node>();
		q.add(this);
		
		int dist = 0;
		boolean found = false;
		while (!found && !(q.isEmpty() && o.isEmpty())) {
			
			while (!q.isEmpty()) {
				Node n = q.poll();
				visited.add(n);
				
				//System.out.println("    >" + n.name);
			
				if (n == dst) {
					found = true;
					break;
				}
				
				for (Node c : n.children) {
					if (visited.contains(c)) continue;
					o.add(c);
				}
			}
			
			if (found) break;
			
			//swap queues
			Queue<Node> tmp = q;
			q = o;
			o = tmp;
			dist++;
			//System.out.println("    <SWAP>");
		}

		return found ? dist : -1;
	}
}
