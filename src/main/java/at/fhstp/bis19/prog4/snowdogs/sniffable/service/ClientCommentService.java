package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class ClientCommentService {

	
	
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	PubdateRepo pubdateRepo;
	@Autowired
	DogRepo dogRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientCommentService.class);

	
	public Comment createComment(String pubdateComment, String pubdateID, String dogID) {
		int pubdateIntID = 0;
		int dogIntID = 0;
		
		try {
			pubdateIntID = Integer.parseInt(pubdateID);
			dogIntID = Integer.parseInt(dogID);
		} catch (Exception e) {
			throw new IllegalArgumentException("could not parse String to Int");
		}
		
				
		if (pubdateComment == null || pubdateComment.isEmpty()){
		throw new IllegalArgumentException("pubdateComment null not allowed");
		}
		
		if (dogRepo.findById(dogIntID).isEmpty()) {
			log.info("Unable to find dogID,  \"{}\" does not exist!", dogIntID);
			return null;
		}
		
		if (pubdateRepo.findById(pubdateIntID).isEmpty()) {
			log.info("Unable to find pubdateID,  \"{}\" does not exist!", pubdateIntID);
			return null;
		} else {
			Comment newComment = new Comment(pubdateComment, pubdateRepo.findById(pubdateIntID).orElse(null), dogRepo.findById(dogIntID).orElse(null));
			commentRepo.save(newComment);
			
			log.info("Dog \"{}\" successfully created new Comment \"{}\" on Pubdate: \"{}\"", dogRepo.findById(dogIntID), newComment, pubdateRepo.findById(pubdateIntID));
			return newComment;
		}
	}
		
}
	
	
