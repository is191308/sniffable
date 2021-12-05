package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "comment")
@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(targetEntity = Pubdate.class, optional = false)
	private Pubdate pubdate;
}
