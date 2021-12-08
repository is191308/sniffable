package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "pubdate")
@Entity
public class Pubdate extends BaseEntity{	
	@Column(name = "title" , nullable = false)
	private String title;
	
	@Column(name = "timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
	private Image picture;
	
	@ManyToOne(targetEntity = Dog.class, optional = false, cascade = CascadeType.ALL)
	private Dog dog;
	
	@JsonIgnore
	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "likes", cascade = CascadeType.ALL)
	private List<Dog> pubdate_likes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "shares", cascade = CascadeType.ALL)
	private List<Dog> pubdate_shares;
	
	
	public Pubdate() {
	}
	
	public Pubdate(String title, Dog dog) {
		this.title = title;
		this.dog = dog;
		this.timestamp = new Date();
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
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

	public List<Dog> getPubdate_shares() {
		return pubdate_shares;
	}

	public void setPubdate_shares(List<Dog> pubdate_shares) {
		this.pubdate_shares = pubdate_shares;
	}

	@Override
	public String toString() {
		return "Pubdate [id=" + super.getId() + ", timestamp=" + timestamp + ", title=" + title + "]";
	}
	
}
