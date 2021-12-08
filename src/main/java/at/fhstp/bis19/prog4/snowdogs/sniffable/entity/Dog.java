package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "dog")
@Entity
public class Dog extends BaseEntity {
	// Roles
	public static enum Role {ADMIN, MODERATOR, USER}

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	private Role role;
	
	@ManyToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
	private Image profilePicture;
	
	@JsonIgnore
	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog", cascade = CascadeType.ALL)
	private List<Pubdate> pubdates;
	
	@JsonIgnore
	@OneToMany(targetEntity = Dog.class, cascade = CascadeType.ALL)
	private List<Dog> subcriptions;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class, cascade = CascadeType.ALL)
	private List<Pubdate> likes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class, cascade = CascadeType.ALL)
	private List<Pubdate> shares;
	
	
	public Dog() {
		this("Anonymous dog", "password");
	}
	
	public Dog(String name, String password) {
		this.name = name;
		this.password = password;
		this.role = Role.USER;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("name null or empty");
		}
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("password null or empty");
		}
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Image getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
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
