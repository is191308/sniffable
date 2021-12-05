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
}
