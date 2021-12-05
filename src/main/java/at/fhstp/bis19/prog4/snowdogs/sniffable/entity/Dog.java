package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "user")
@Entity
public class Dog extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog")
	private List<Pubdate> pubdates;
	
	@JsonIgnore
	@OneToMany(targetEntity = Dog.class)
	private List<Dog> subcriptions;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class)
	private List<Pubdate> likes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class)
	private List<Pubdate> shares;
	
	public Dog() {
		this("anonymous dog");
	}
	
	public Dog(String name) {
		this.name = name;
	}
	
	//TODO: removed unused setter
		
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

	public List<Pubdate> getShares() {
		return shares;
	}

	public void setShares(List<Pubdate> shares) {
		this.shares = shares;
	}
	
	

	@Override
	public String toString() {
		return "Dog [id=" + super.getId() + ", name=" + name + "]";
	}
	
	
}
