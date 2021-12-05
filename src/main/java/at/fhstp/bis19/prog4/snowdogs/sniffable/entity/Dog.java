package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.List;

import javax.persistence.*;

@Table(name = "user")
@Entity
public class Dog {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog")
	private List<Pubdate> pubdates;
	
	@OneToMany(targetEntity = Dog.class)
	private List<Dog> subcriptions;
	
	@ManyToMany(targetEntity = Pubdate.class)
	private List<Pubdate> likes;
}
