package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import lombok.Data;

@Data
public class CommentDTO {
	private int id;
	private String comment;
	private NewPubdateDTO pubdate;
	private DogDTO dog;
	
	public CommentDTO(Comment c) {
		this.id = c.getId();
		this.comment = c.getComment();
		this.pubdate = new NewPubdateDTO(c.getPubdate());
		this.dog = new DogDTO(c.getDog());
	}
}
