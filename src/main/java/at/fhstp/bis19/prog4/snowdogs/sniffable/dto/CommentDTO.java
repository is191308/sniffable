package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
	private int id;
	private String comment;
	private DogDTO dog;
	
	public CommentDTO(Comment c) {
		this.id = c.getId();
		this.comment = c.getComment();
		this.dog = new DogDTO(c.getDog());
	}
}
