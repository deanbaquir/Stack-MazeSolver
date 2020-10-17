package webForumn.models;

import java.util.ArrayList;

public class Forum {
	
	private String title;
	private ArrayList<Topic> topics = new ArrayList<Topic>();
	
	public Forum() {}
	
	// parameterized constructor 
	public Forum(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String topic) {
		this.title = topic;
	}

	public ArrayList<Topic> getTopics() {
		return topics;
	}

	public void setTopics(ArrayList<Topic> topics) {
		this.topics = topics;
	}

	

}
