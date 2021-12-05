package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class Dog {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog")
	private List<Pubdate> pubdates;
	
	@OneToMany(targetEntity = Dog.class)
	private List<Dog> subcriptions;
	
	@ManyToMany(targetEntity = Pubdate.class, mappedBy = "likes")
	private List<Pubdate> likes;
}
