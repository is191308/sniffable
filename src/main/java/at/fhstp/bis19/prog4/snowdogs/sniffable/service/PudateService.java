package at.fhstp.bis19.prog4.snowdogs.sniffable.service;


import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewCommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
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
public class PudateService extends BaseService<Pubdate, PubdateDTO> {
	@Autowired
	PubdateRepo pubdateRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	DogRepo dogRepo;
	
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	public PudateService() {
		super(PubdateDTO.class);
	}
	
	public DogDTO getByName(String name) throws SniffableNotFoundException {
		return mapper.
				map(dogRepo.findByNameIgnoreCase(name).
						orElseThrow(() -> new SniffableNotFoundException("dog with name \"" + name + "\" + not exists")), DogDTO.class);
	}
	
	public PubdateDTO createPubdate(NewPubdateDTO pubdate) throws SniffableException {
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
				return mapper.map(pub, PubdateDTO.class);
			} else {
				log.error("Unable to create pubdate \"{}\": unable to create pubdate", pubdate.getTitle());
				throw new SniffableException("unable to create pubdate");
			} 
		} else {
			log.warn("Unable to create pubdate \"{}\": dog not found!", pubdate.getTitle());
			throw new SniffableNotFoundException("dog not found");
		}
	}
	
	public CommentDTO addComment(int pubdateId, NewCommentDTO comment) throws SniffableException {
		Optional<Dog> dog = dogRepo.findById(comment.getDog().getId());
		Optional<Pubdate> pub = pubdateRepo.findById(pubdateId);
		if (pub.isPresent()) {
			if (dog.isPresent()) {
				Comment c = mapper.map(comment, Comment.class);
				c.setDog(dog.get());
				c.setPubdate(pub.get());
				pub.get().addComment(c);
				commentRepo.save(c);
				return mapper.map(commentRepo.save(c), CommentDTO.class);
			} else {
				throw new SniffableNotFoundException("dog does not exists");
			}
		} else {
			throw new SniffableNotFoundException("pubdate with id \"" + pubdateId + "\" + not exists");
		}
	}
}
