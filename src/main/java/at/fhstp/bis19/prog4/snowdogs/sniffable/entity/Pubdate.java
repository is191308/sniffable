package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Table(name = "pubdate")
@Entity
public class Pubdate {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "timestamp")
	private Date timestamp;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate")
	private List<Comment> comments;
	
	@ManyToMany(targetEntity = Dog.class, mappedBy = "likes")
	private List<Dog> pubdate_likes;
	
	@ManyToOne(targetEntity = Dog.class)
	private Dog dog;

	public int getId() {
		return id;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Dog> getPubdate_likes() {
		return pubdate_likes;
	}

	public void setPubdate_likes(List<Dog> pubdate_likes) {
		this.pubdate_likes = pubdate_likes;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Pubdate [id=" + id + ", timestamp=" + timestamp + ", title=" + title + "]";
	}
	
}
