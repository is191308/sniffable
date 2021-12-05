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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pubdate> getPubdates() {
		return pubdates;
	}

	public void setPubdates(List<Pubdate> pubdates) {
		this.pubdates = pubdates;
	}

	public List<Dog> getSubcriptions() {
		return subcriptions;
	}

	public void setSubcriptions(List<Dog> subcriptions) {
		this.subcriptions = subcriptions;
	}

	public List<Pubdate> getLikes() {
		return likes;
	}

	public void setLikes(List<Pubdate> likes) {
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + "]";
	}
	
	
}
