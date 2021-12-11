package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.CommentDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class DogService extends BaseService<Dog, DogDTO> {
	@Autowired
	DogRepo dogRepo;
	
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	public DogService() {
		super(DogDTO.class);
	}
	
	public DogDTO getByName(String name) throws SniffableNotFoundException {
		return mapper.
				map(dogRepo.findByNameIgnoreCase(name).
						orElseThrow(() -> new SniffableNotFoundException("dog with name \"" + name + "\" + not exists")), DogDTO.class);
	}
	
	public DogDTO createDog(NewDogDTO dog) throws SniffableException {
		if (dog.getName() == null || dog.getName().isEmpty()) {
			log.warn("Unable to register new dog \"{}\": name null or empty", dog.getName());
			throw new SniffableIllegalValueException("name null or empty");
		}
		if (dogRepo.findByNameIgnoreCase(dog.getName()).isEmpty()) {
			Dog d = Dog.builder().name(dog.getName()).password(dog.getPassword()).role(dog.getRole()).build();
			Dog newDog = dogRepo.save(d);
			if (newDog != null) {
				log.info("New dog \"{}\" registerd sucessfully!", dog.getName());
				return new DogDTO(newDog);
			} else {
				log.error("Unable to register new dog \"{}\": unable to save dog", dog.getName());
				throw new SniffableException("unable to save dog");
			}
		} else {
			log.warn("Unable to register new dog \"{}\": already exists!", dog.getName());
			throw new SniffableAlreadyExistsException("name alreday exists");
		}
	}
	
	//TODO
	/*
	public DogDTO updateDog() {
	}*/
	
	public void likePubdate(int id, int pid) throws SniffableException {
		if (dogRepo.existsById(id) && pubdateRepo.existsById(pid)) {
			Dog dog = dogRepo.findById(id).get();
			Pubdate pubdate = pubdateRepo.findById(pid).get();
			dog.addLike(pubdate);
			dogRepo.save(dog);
			if (dogRepo.save(dog) != null) {
				log.info("Like \"{}\" from \"{}\" sucessfully created!", pubdate.getTitle(), dog.getName());
			} else {
				log.info("Unable to like \"{}\" from \"{}\"!", pubdate.getTitle(), dog.getName());
				throw new SniffableException("unable to create like");
			}
		} else {
			throw new SniffableNotFoundException("dog or pubdate not exists");
		}
	}
	
	public void sharePubdate(int id, int pid) throws SniffableException {
		if (dogRepo.existsById(id) && pubdateRepo.existsById(pid)) {
			Dog dog = dogRepo.findById(id).get();
			Pubdate pubdate = pubdateRepo.findById(pid).get();
			dog.addShare(pubdate);
			dogRepo.save(dog);
			if (dogRepo.save(dog) != null) {
				log.info("Share \"{}\" from \"{}\" sucessfully created!", pubdate.getTitle(), dog.getName());
			} else {
				log.info("Unable to like \"{}\" from \"{}\"!", pubdate.getTitle(), dog.getName());
				throw new SniffableException("unable to create share");
			}
		} else {
			throw new SniffableNotFoundException("dog or pubdate not exists");
		}
	}
	
	public void followDog(int id, int did) throws SniffableException {
		if (id == did) {
			throw new SniffableIllegalValueException("self follow not allowed");
		}
		if (dogRepo.existsById(id) && dogRepo.existsById(did)) {
			Dog dog = dogRepo.findById(id).get();
			Dog dog2 = dogRepo.findById(did).get();
			dog.addFollow(dog2);
			dogRepo.save(dog);
			if (dogRepo.save(dog) != null) {
				log.info("Follow \"{}\" to \"{}\" sucessfully created!", dog.getName(), dog2.getName());
			} else {
				log.info("Unable to follow \"{}\" to \"{}\"!", dog.getName(), dog.getName());
				throw new SniffableException("unable to create follow");
			}
		} else {
			throw new SniffableNotFoundException("dog not exists");
		}
	}
	
	public Set<PubdateDTO> getTimeline(int id) throws SniffableException {
		if (dogRepo.existsById(id)) {
			Set<PubdateDTO> timeline = new TreeSet<>();
			Dog dog = dogRepo.findById(id).get();
			for (Dog d : dog.getFollow()) {
				timeline.addAll(d.getPubdates().stream().map(p -> new PubdateDTO(p)).collect(Collectors.toSet()));
				timeline.addAll(d.getShares().stream().map(p -> new PubdateDTO(p)).collect(Collectors.toSet()));
			}
			return timeline;
		} else {
			throw new SniffableNotFoundException("dog with id \"" + id + "\" + not exists");
		}
	}
	
	
	/*
	public DogDTO registerDog(String name, String password, Role role) throws SniffableException {
		if (name == null || name.isEmpty()) {
			log.warn("Unable to register new dog \"{}\": name null or empty", name);
			throw new SniffableIllegalValueException("name null or empty");
		}
		if (dogRepo.findByNameIgnoreCase(name).isEmpty()) {
			Dog d = Dog.builder().name(name).password(password).role(role).build();
			d.setRole(role);
			Dog newDog = dogRepo.save(d);
			if (newDog != null) {
				log.info("New dog \"{}\" registerd sucessfully!", name);
				return new DogDTO(newDog);
			} else {
				log.error("Unable to register new dog \"{}\": unable to save dog", name);
				throw new SniffableException("unable to save dog");
			}
		} else {
			log.warn("Unable to register new dog \"{}\": already exists!", name);
			throw new SniffableAlreadyExistsException("name alreday exists");
		}
	}*/
	
}
