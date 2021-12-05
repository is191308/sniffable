package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "pubdate")
@Entity
public class Pubdate {
	@Id
	@GeneratedValue
	private int id;
	private Date timestamp;
	private String title;
	private String description;
	//TODO Image
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate")
	private List<Comment> comments;
	
	@ManyToMany(targetEntity = Dog.class, mappedBy = "likes")
	private List<Dog> likes;
	
	@ManyToOne(targetEntity = Dog.class)
	private Dog dog;
	
}
