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
	
	@OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Image picture;
	
	@ManyToOne(targetEntity = Dog.class, optional = false, fetch = FetchType.EAGER)
	private Dog dog;
	
	@JsonIgnore
	@OneToMany(targetEntity = Comment.class, mappedBy = "pubdate", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Comment> pubdateComments;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "likes", fetch = FetchType.EAGER)
	private Set<Dog> pubdateLikes;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Dog.class, mappedBy = "shares", fetch = FetchType.EAGER)
	private Set<Dog> pubdateShares;
	
	public void addComment(Comment comment) {
		this.pubdateComments.add(comment);
	}

	@Override
	public int compareTo(Pubdate o) {
		return o.timestamp.compareTo(this.timestamp);
	}
	
}
