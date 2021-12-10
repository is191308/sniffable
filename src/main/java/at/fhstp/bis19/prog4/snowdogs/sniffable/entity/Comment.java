package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "comment")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
	@Column(name = "comment")
	private String comment;

	@ManyToOne(targetEntity = Pubdate.class, optional = false)
	private Pubdate pubdate;

	@ManyToOne(targetEntity = Dog.class, optional = false)
	private Dog dog;
}
