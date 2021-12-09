package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableGeneralException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class ClientDogService {
	@Autowired
	DogRepo dogRepo;
	
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientDogService.class);
	
	public DogDTO registerDog(String name, String password, Role role) throws SniffableGeneralException {
		if (name == null || name.isEmpty()) {
			log.warn("Unable to register new dog \"{}\": name null or empty", name);
			throw new SniffableIllegalValueException("name null or empty");
		}
		if (dogRepo.findByNameIgnoreCase(name).isEmpty()) {
			Dog d = new Dog(name, password);
			d.setRole(role);
			Dog newDog = dogRepo.save(d);
			if (newDog != null) {
				log.info("New dog \"{}\" registerd sucessfully!", name);
				return new DogDTO(newDog);
			} else {
				log.error("Unable to register new dog \"{}\": unable to save dog", name);
				throw new SniffableGeneralException("unable to save dog");
			}
		} else {
			log.warn("Unable to register new dog \"{}\": already exists!", name);
			throw new SniffableAlreadyExistsException("name alreday exists");
		}
	}
	
	public DogDTO changeDogName(DogDTO dog) throws SniffableGeneralException {
		if (dog == null || dog.getName() == null || dog.getName().isEmpty()) {
			log.warn("Unable to change name: name null or empty!");
			throw new SniffableIllegalValueException("name null or empty");
		}
		if (dogRepo.findByNameIgnoreCase(dog.getName()).isEmpty()) {
			Optional<Dog> curDog = dogRepo.findById(dog.getId());
			if (curDog.isPresent()) {
				Dog d = curDog.get();
				d.setName(dog.getName());
				Dog newDog = dogRepo.save(d);
				if (newDog != null) {
					log.info("Name \"{}\" changed sucessfully!", dog.getName());
					return new DogDTO(newDog);
				} else {
					log.info("Unable to change name to \"{}\": unknown error!", dog.getName());
					throw new SniffableGeneralException("unable to save dog");
				}
			} else {
				log.info("Unable to change name to \"{}\": dog with id \"{}\" not found!", dog.getName(), dog.getId());
				throw new SniffableNotFoundException("dog not found");
			}	
		} else {
			log.info("Unable to change name to \"{}\": name already exists!", dog.getName());
			throw new SniffableAlreadyExistsException("name already exists");
		}
	}
	/*
	public Pubdate createPubdate(String title, String content, String dogname) {
		int dogID = 0;
		
		if (title == null || title.isEmpty() || dogname == null || dogname.isEmpty()){
			throw new IllegalArgumentException("name null not allowed");
		}
		
		try {
			dogID = dogRepo.findByNameIgnoreCase(dogname).get(0).getId();
		} catch (IndexOutOfBoundsException e) {
			log.info("Unable to find dogname,  \"{}\" does not exist!", dogname);
			return null;
		}
				
		Pubdate newPubdate = new Pubdate(title, dogRepo.findById(dogID).orElse(null));
		newPubdate.setContent(content);
		pubdateRepo.save(newPubdate);
					
		if (newPubdate != null) {
			log.info("Dog \"{}\" successfully created new Pubdate \"{}\" with Content: \"{}\"", dogRepo.findById(dogID), newPubdate, newPubdate.getContent());
			return newPubdate;
			}
					
		log.info("Unable to create Pubdate \"{}\" unknown error!", newPubdate);
		return null;		
	}*/
	
}
