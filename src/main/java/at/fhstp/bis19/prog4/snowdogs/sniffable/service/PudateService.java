package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewCommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Image;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class PudateService {
	@Autowired
	PubdateRepo pubdateRepo;
	
	@Autowired
	DogRepo dogRepo;
	
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	public PubdateDTO createPubdate(NewPubdateDTO pubdate) throws SniffableException {
		if (pubdate == null || pubdate.getTitle().isEmpty() || pubdate.getDog() == null) {
			log.warn("Unable to create pubdate: pubdate null or empty");
			throw new SniffableIllegalValueException("pubdate null or empty");
		}
		Optional<Dog> dog = dogRepo.findById(pubdate.getDog().getId());
		if (dog.isPresent()) {
			Pubdate pub = Pubdate.builder().title(pubdate.getTitle()).dog(dog.get()).content(pubdate.getContent()).dog(dog.get()).build();
			if (pubdate.getPicture() != null) {
				pub.setPicture(new Image(pubdate.getPicture().getName(), pubdate.getPicture().getImageData()));
			}
			pub = pubdateRepo.save(pub);
			if (pub != null) {
				log.info("New pubdate \"{}\" created sucessfully!", pubdate.getTitle());
				return new PubdateDTO(pub);
			} else {
				log.error("Unable to create pubdate \"{}\": unable to create pubdate", pubdate.getTitle());
				throw new SniffableException("unable to create pubdate");
			} 
		} else {
			log.warn("Unable to create pubdate \"{}\": dog not found!", pubdate.getTitle());
			throw new SniffableNotFoundException("dog not found");
		}
	}
	
	public Set<PubdateDTO> getAll() {
		Set<PubdateDTO> pubdates = new HashSet<>();
		pubdateRepo.findAll().forEach(p -> pubdates.add(new PubdateDTO(p)));
		return pubdates;
	}
	
	public PubdateDTO getById(int id) throws SniffableNotFoundException {
		if (pubdateRepo.existsById(id)) {
			return new PubdateDTO(pubdateRepo.findById(id).get());
		} else {
			throw new SniffableNotFoundException("pubdate with id \"" + id + "\" + not exists");
		}
	}
	
	public void delete(int id) throws SniffableNotFoundException {
		if (pubdateRepo.existsById(id)) {
			pubdateRepo.deleteById(id);
		} else {
			throw new SniffableNotFoundException("pubdate with id \"" + id + "\" + not exists");
		}
	}
	
	public PubdateDTO addComment(int pubdateId, NewCommentDTO comment) throws SniffableException {
		if (pubdateRepo.existsById(pubdateId)) {
			if (comment.getDog() != null && dogRepo.existsById(comment.getDog().getId())) {
				Pubdate pub = pubdateRepo.findById(pubdateId).get();
				Comment c = Comment.builder().comment(comment.getComment()).dog(dogRepo.findById(comment.getDog().getId()).get()).pubdate(pub).build();
				pub.addComment(c);
				return new PubdateDTO(pubdateRepo.save(pub));
			} else {
				throw new SniffableNotFoundException("dog does not exists");
			}
			
		} else {
			throw new SniffableNotFoundException("pubdate with id \"" + pubdateId + "\" + not exists");
		}
	}
}