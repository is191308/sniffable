package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class ClientCommentService {

	
	
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientCommentService.class);

	
	public Comment createPubdate() {
		//toDo
		
		return (new Comment());
	}
}
