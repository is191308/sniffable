package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Pubdate extends BaseEntity implements Comparable<Pubdate>{	
	@Column(name = "title" , nullable = false)
	private String title;
	
	@Column(name = "timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date timestamp = new Date();
	
	@Column(name = "content")
	private String content;
	
	@OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private Image picture;
	
	@ManyToOne(targetEntity = Dog.class, optional = false)
	private Dog dog;
	
	@JsonIgnore
	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "plikes")
	private Set<Dog> dlikes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "pshares")
	private Set<Dog> dshares;
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	@Override
	public int compareTo(Pubdate o) {
		return o.timestamp.compareTo(this.timestamp);
	}
	
}
