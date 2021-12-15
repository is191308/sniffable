package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;

@Service
public class CommentService extends BaseService<Comment, CommentDto> {
	@Autowired
	public CommentService(CommentRepo commentRepo) {
		super(CommentDto.class, commentRepo);
	}
}
