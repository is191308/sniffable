package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

public class CommentDTO {
	private int id;
	private String comment;
	private PubdateDTO pubdate;
	private DogDTO dog;
	
	public CommentDTO() {
	}
	
	public CommentDTO(Comment c) {
		this.id = c.getId();
		this.comment = c.getComment();
		this.pubdate = new PubdateDTO(c.getPubdate());
		this.dog = new DogDTO(c.getDog());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public PubdateDTO getPubdate() {
		return pubdate;
	}
	public void setPubdate(PubdateDTO pubdate) {
		this.pubdate = pubdate;
	}
	public DogDTO getDog() {
		return dog;
	}
	public void setDog(DogDTO dog) {
		this.dog = dog;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", comment=" + comment + "]";
	}
	
	
}
