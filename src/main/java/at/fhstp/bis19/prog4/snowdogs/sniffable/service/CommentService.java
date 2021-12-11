package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

@Service
public class CommentService extends BaseService<Comment, CommentDTO> {
	public CommentService() {
		super(CommentDTO.class);
	}
}
