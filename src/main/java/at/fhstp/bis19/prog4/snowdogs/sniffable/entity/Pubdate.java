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
	
}
