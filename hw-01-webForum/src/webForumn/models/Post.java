package webForumn.models;

public class Post {
	
	private String name;
	private String subject;
	private String content;
	
	public Post() {}
	
	public Post(String name, String subject, String content) {
		this.name = name;
		this.subject = subject;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
