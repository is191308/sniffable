package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.*;

@Table(name = "comment")
@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(targetEntity = Pubdate.class, optional = false)
	private Pubdate pubdate;

	public int getId() {
		return id;
	}

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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + "]";
	}
	
	
}
