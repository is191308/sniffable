package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Set;

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
	private Dog.Role role;
	
	@JsonIgnore
	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pubdate> pubdates;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, cascade = CascadeType.ALL)
	private Set<Dog> follow;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "follow", cascade = CascadeType.ALL)
	private Set<Dog> followers;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class, cascade = CascadeType.ALL)
	private Set<Pubdate> likes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Pubdate.class, cascade = CascadeType.ALL)
	private Set<Pubdate> shares;
	
	
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

	public Set<Pubdate> getPubdates() {
		return pubdates;
	}

	public void setPubdates(Set<Pubdate> pubdates) {
		this.pubdates = pubdates;
	}

	public Set<Dog> getFollow() {
		return follow;
	}

	public void setFollow(Set<Dog> follow) {
		this.follow = follow;
	}
	
	public void addFollow(Dog follow) {
		this.follow.add(follow);
	}

	public Set<Dog> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Dog> followers) {
		this.followers = followers;
	}

	public Set<Pubdate> getLikes() {
		return likes;
	}

	public void setLikes(Set<Pubdate> likes) {
		this.likes = likes;
	}
	
	public void addLike(Pubdate like) {
		this.likes.add(like);
	}

	public Set<Pubdate> getShares() {
		return shares;
	}

	public void setShares(Set<Pubdate> shares) {
		this.shares = shares;
	}
	
	public void addShare(Pubdate share) {
		this.shares.add(share);
	}
	
	@Override
	public String toString() {
		return "Dog [id=" + super.getId() + ", name=" + name + "]";
	}

	
}
