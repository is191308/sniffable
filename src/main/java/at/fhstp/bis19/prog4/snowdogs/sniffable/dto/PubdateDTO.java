package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import java.util.Date;

public class PubdateDTO {
	private int id;
	private String title;
	private Date timestamp;
	private String content;
	private ImageDTO picture;
	private DogDTO dog;
	
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DogDTO getDog() {
		return dog;
	}
	public void setDog(DogDTO dog) {
		this.dog = dog;
	}
	public ImageDTO getPicture() {
		return picture;
	}
	public void setPicture(ImageDTO picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "PubdateDTO [id=" + id + ", title=" + title + ", timestamp=" + timestamp + "]";
	}
}
