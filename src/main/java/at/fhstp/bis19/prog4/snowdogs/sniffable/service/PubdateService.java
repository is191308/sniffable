package at.fhstp.bis19.prog4.snowdogs.sniffable.service;


import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewCommentDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class PubdateService extends BaseService<Pubdate, PubdateDto> {
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	PubdateRepo pubdateRepo;
	DogRepo dogRepo;
	CommentRepo commentRepo;
	
	@Autowired
	public PubdateService(PubdateRepo pubdateRepo, DogRepo dogRepo,
			CommentRepo commentRepo) {
		super(PubdateDto.class, pubdateRepo);
		this.dogRepo = dogRepo;
		this.pubdateRepo = pubdateRepo;
		this.commentRepo = commentRepo;
	}
	
	public DogDto getByName(String name) {
		return mapper.
				map(dogRepo.findByNameIgnoreCase(name).
				orElseThrow(() -> 	new SniffableNotFoundException("dog with name \""
				+ name + "\" + not exists")),	DogDto.class);
	}
	
	public PubdateDto createPubdate(NewPubdateDto pubdate) {
		if (pubdate == null || pubdate.getTitle().isEmpty() || pubdate.getDog() == null) {
			log.warn("Unable to create pubdate: pubdate null or empty");
			throw new SniffableIllegalValueException("pubdate null or empty");
		}
		Optional<Dog> dog = dogRepo.findById(pubdate.getDog().getId());
		
		if (dog.isPresent()) {
			Pubdate pub = mapper.map(pubdate, Pubdate.class);
			pub.setDog(dog.get());
			pub = pubdateRepo.save(pub);
			
			if (pub != null) {
				log.info("New pubdate \"{}\" created sucessfully!", pubdate.getTitle());
				return mapper.map(pub, PubdateDto.class);
			} 
			else {
				log.error("Unable to create pubdate \"{}\": unable to create pubdate", 
						pubdate.getTitle());
				throw new SniffableException("unable to create pubdate");
			} 
		} else {
			log.warn("Unable to create pubdate \"{}\": dog not found!", 
					pubdate.getTitle());
			throw new SniffableNotFoundException("dog not found");
		}
	}
	

	public CommentDto addComment(int pubdateId, int dogID, NewCommentDto comment) {
		Optional<Dog> dog = dogRepo.findById(dogID);
		Optional<Pubdate> pub = pubdateRepo.findById(pubdateId);
		if (pub.isPresent()) {
			if (dog.isPresent()) {
				Comment c = mapper.map(comment, Comment.class);
				c.setDog(dog.get());
				c.setPubdate(pub.get());
				pub.get().addComment(c);
				commentRepo.save(c);
				return mapper.map(commentRepo.save(c), CommentDto.class);
			} else {
				throw new SniffableNotFoundException("dog does not exists");
			}
		} else {
			throw new SniffableNotFoundException("pubdate with id \""
					+ pubdateId + "\" + not exists");
		}
	}
}
