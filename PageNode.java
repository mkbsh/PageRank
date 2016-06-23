package pagerank;

public class PageNode {
	public String name;
	private float current_score;
	private float new_score;
	
	PageNode(String name) {
		this.name = name;
	}
	public void setCurrentScore(float score) {
		current_score = score;
	}
	public float getCurrentScore() {
		return current_score;
	}
	public void setNewScore(float score) {
		new_score = score;
	}
	public float getNewScore() {
		return new_score;
	}
}
