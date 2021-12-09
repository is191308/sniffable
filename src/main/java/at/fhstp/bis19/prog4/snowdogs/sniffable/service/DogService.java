package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class DogService {
	@Autowired
	DogRepo dogRepo;
	
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	public DogDTO registerDog(String name, String password, Role role) throws SniffableException {
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
				throw new SniffableException("unable to save dog");
			}
		} else {
			log.warn("Unable to register new dog \"{}\": already exists!", name);
			throw new SniffableAlreadyExistsException("name alreday exists");
		}
	}
	
	public DogDTO changeDogName(DogDTO dog) throws SniffableException {
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
					throw new SniffableException("unable to save dog");
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
	
	public Set<DogDTO> getAll() {
		Set<DogDTO> dogs = new HashSet<>();
		for (Dog d : dogRepo.findAll()) {
			dogs.add(new DogDTO(d));
		}
		return dogs;
	}
	
	public DogDTO getById(int id) throws SniffableNotFoundException {
		if (dogRepo.existsById(id)) {
			return new DogDTO(dogRepo.findById(id).get());
		} else {
			throw new SniffableNotFoundException("dog with id \"" + id + "\" + not exists");
		}
	}
	
	public void delete(int id) throws SniffableNotFoundException {
		if (dogRepo.existsById(id)) {
			dogRepo.deleteById(id);
		} else {
			throw new SniffableNotFoundException("dog with id \"" + id + "\" + not exists");
		}
	}
	
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
				for (Pubdate  p : d.getPubdates()) {
					timeline.add(new PubdateDTO(p));
				}
				for (Pubdate  p : d.getShares()) {
					timeline.add(new PubdateDTO(p));
				}
			}
			return timeline;
		} else {
			throw new SniffableNotFoundException("dog with id \"" + id + "\" + not exists");
		}
	}
	
}
