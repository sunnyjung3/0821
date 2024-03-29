package board;

public class Article {
	
	// 접근제어자
	private int id;
	private String title;
	private String body;
	private String writer;
	private String regDate;
	
	//ArayList
	
	Article() {
		
	}
	
	Article(int id, String title, String body, String writer, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.writer = writer;
		this.regDate = regDate;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}