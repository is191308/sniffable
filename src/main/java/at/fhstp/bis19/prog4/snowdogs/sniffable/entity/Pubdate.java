package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "pubdate")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pubdate extends BaseEntity implements Comparable<Pubdate> {
	/**
	 * title of the pubdate (must be set)
	 */
	@Column(name = "title" , nullable = false)
	private String title;
	
	/**
	 * time when the pubdate was created (must be set)
	 * default: current creation time
	 */
	@Column(name = "timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date timestamp = new Date();
	
	/**
	 * content of the pubdate (optional)
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * pubdate picture (optional)
	 */
	@OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private Image picture;
	
	/**
	 * dog who created the pubdate
	 */
	@ManyToOne(targetEntity = Dog.class, optional = false)
	private Dog dog;

	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments;
	
	@ManyToMany(targetEntity = Dog.class, mappedBy = "liked")
	private Set<Dog> likes;
	
	@ManyToMany(targetEntity = Dog.class, mappedBy = "shared")
	private Set<Dog> shares;
	
	@PreRemove
	private void removeManyToManyRelations() {
	   for (Dog d: this.likes) {
		   d.getLiked().remove(this);
	   }
	   for (Dog d: this.shares) {
		   d.getShared().remove(this);
	   }
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	// Sort desc by timestamp
	@Override
	public int compareTo(Pubdate o) {
		return o.timestamp.compareTo(this.timestamp);
	}
	
}
