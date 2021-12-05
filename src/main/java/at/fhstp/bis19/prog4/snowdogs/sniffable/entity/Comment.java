package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.*;

@Table(name = "comment")
@Entity
public class Comment extends BaseEntity{
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(targetEntity = Pubdate.class, optional = false)
	private Pubdate pubdate;
	
	@ManyToOne(targetEntity = Dog.class, optional = false)
	private Dog dog;
	
	public Comment(String comment, Pubdate pubdate, Dog dog) {
		this.comment = comment;
		this.pubdate = pubdate;
		this.dog = dog;
	}
	
	public Comment() {
		
	}

	//TODO: removed unused setter
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Pubdate getPubdate() {
		return pubdate;
	}

	public void setPubdate(Pubdate pubdate) {
		this.pubdate = pubdate;
	}
	
	
	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Comment [id=" + super.getId() + ", comment=" + comment + "]";
	}
	
	
}
