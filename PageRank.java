package pagerank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRank {
	static float INITIAL_SCORE = (float) 100;
	// Nodes. Easy look up by name of node and its corresponding PageNode object.
	private Map<String, PageNode> name_to_node = new HashMap<String, PageNode>();
	// Edges. The association between a node and the nodes it sends a directed edge.
	private Map<PageNode, List<PageNode>> page_edges = new HashMap<PageNode, List<PageNode>>();
	
	// Parse file and initialize graph represented by member variables.
	public void initialize(String path) throws IOException {
		BufferedReader text_reader = new BufferedReader(new FileReader(path));
		int number_of_nodes = Integer.valueOf(text_reader.readLine());
		// Create name_to_node.
		for (int i = 0; i < number_of_nodes; i++) {
			String name = text_reader.readLine();
			PageNode page_node = new PageNode(name);
			page_node.setCurrentScore(INITIAL_SCORE);
			name_to_node.put(name, page_node);
			page_edges.put(page_node, null);
		}
		int number_of_edges = Integer.valueOf(text_reader.readLine());
		// Create page_edges.
		for (int i = 0; i < number_of_edges; i++) {
			String[] nodes_in_edge = text_reader.readLine().split(" ");
			List<PageNode> nodes;
			if (page_edges.get(name_to_node.get(nodes_in_edge[0])) != null) {
				nodes = new ArrayList<PageNode>(page_edges.get(name_to_node.get(nodes_in_edge[0])));
			} else {
				nodes = new ArrayList<PageNode>();
			}
			nodes.add(name_to_node.get(nodes_in_edge[1]));
			page_edges.put(name_to_node.get(nodes_in_edge[0]), nodes);
		}
	}
	// One iteration of updating the score.
	public void updateScore() {
		for (PageNode origin: page_edges.keySet()) {
			for (PageNode dest: page_edges.get(origin)) {
				dest.setNewScore(dest.getNewScore() + origin.getCurrentScore() / page_edges.get(origin).size());
			}
		}
		for (PageNode page_node: page_edges.keySet()) {
			page_node.setCurrentScore(page_node.getNewScore());
			page_node.setNewScore(0);
		}
	}
}
