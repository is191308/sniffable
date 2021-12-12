package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;

@Service
public class CommentService extends BaseService<Comment, CommentDto> {
	public CommentService() {
		super(CommentDto.class);
	}
}
