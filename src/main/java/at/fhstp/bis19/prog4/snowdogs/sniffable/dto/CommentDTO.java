package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import lombok.Data;

@Data
public class CommentDTO {
	private int id;
	private String comment;
	private PubdateDTO pubdate;
	private DogDTO dog;
	
	public CommentDTO(Comment c) {
		this.id = c.getId();
		this.comment = c.getComment();
		this.pubdate = new PubdateDTO(c.getPubdate());
		this.dog = new DogDTO(c.getDog());
	}
}
