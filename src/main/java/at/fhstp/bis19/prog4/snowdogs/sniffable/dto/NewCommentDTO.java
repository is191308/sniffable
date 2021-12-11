package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCommentDTO {
	private String comment;
	private NewPubdateDTO pubdate;
	private DogDTO dog;
	
	public NewCommentDTO(Comment c) {
		this.comment = c.getComment();
		this.dog = new DogDTO(c.getDog());
	}
}
