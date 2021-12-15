package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "dog")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dog extends BaseEntity {
	// Roles
	public static enum Role {ADMIN, MODERATOR, USER}
	
	/**
	 * name of the dog (must be set)
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	/**
	 * password of the dog (must be set)
	 */
	@Column(name = "password", nullable = false)
	private String password;
	
	/**
	 * role of the dog (must be set)
	 * default: USER
	 */
	@Column(name = "role", nullable = false)
	@Builder.Default
	private Dog.Role role = Role.USER;

	@OneToMany(targetEntity = Pubdate.class, mappedBy = "dog", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Pubdate> pubdates;
	
	@ManyToMany(targetEntity = Dog.class)
	private Set<Dog> follow;
	
	@ManyToMany(targetEntity = Dog.class, mappedBy = "follow")
	private Set<Dog> followers;
	
	@ManyToMany(targetEntity = Pubdate.class)
	private Set<Pubdate> liked;
	
	@ManyToMany(targetEntity = Pubdate.class)
	private Set<Pubdate> shared;
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "dog", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	@PreRemove
	private void removeManyToManyRelations() {
	   for (Dog d : this.followers) {
		   d.getFollow().remove(this);
	   }
	}
	
	public void addFollow(Dog follow) {
		this.follow.add(follow);
	}
	
	public void removeFollow(Dog follow) {
		this.follow.remove(follow);
	}

	public void addLike(Pubdate like) {
		this.liked.add(like);
	}
	
	public void removeLike(Pubdate like) {
		this.liked.remove(like);
	}
	
	public void addShare(Pubdate share) {
		this.shared.add(share);
	}
	
	public void removeShare(Pubdate share) {
		this.shared.remove(share);
	}	
}
