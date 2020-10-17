package webForumn.models;

public class Topic {
	
	private int id;
	private int replies;
	private String subject;
	private String author;
	private String content;
	private String lastPost;
	
	
	public Topic() {}
	
	public Topic(int id, String author, String subject, String content) {
		this.id = id;
		this.author = author;
		this.subject = subject;
		this.content = content;		
	}
	
	public Topic(int id, String subject, String author, int replies, String lastPost) {
		this.id = id;
		this.subject = subject;
		this.author = author;
		this.replies = replies;
		this.lastPost = lastPost;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getReplies() {
		return replies;
	}

	public void setReplies(int replies) {
		this.replies = replies;
	}

	public String getLastPost() {
		return lastPost;
	}

	public void setLastPost(String lastPost) {
		this.lastPost = lastPost;
	}

}
