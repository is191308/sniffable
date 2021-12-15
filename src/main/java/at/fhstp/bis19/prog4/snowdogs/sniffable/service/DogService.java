package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

<<<<<<< HEAD
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class DogService extends BaseService<Dog, DogDto> {
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
	DogRepo dogRepo;
	PubdateRepo pubdateRepo;
	
	@Autowired
	public DogService(DogRepo dogRepo, PubdateRepo pubdateRepo) {
		super(DogDto.class, dogRepo);
		this.dogRepo = dogRepo;
		this.pubdateRepo = pubdateRepo;
	}

	public DogDto getByName(String name) {
		return mapper.map(
				dogRepo.findByNameIgnoreCase(name).orElseThrow(
						() -> new SniffableNotFoundException("dog with name \"" + name + "\" + not exists")),
				DogDto.class);
	}

	public DogDto createDog(NewDogDto dog) {
		if (dog.getName() == null || dog.getName().isEmpty()) {
			log.warn("Unable to register new dog \"{}\": name null or empty", dog.getName());
			throw new SniffableIllegalValueException("name null or empty");
		}
		if (dogRepo.findByNameIgnoreCase(dog.getName()).isEmpty()) {
			Dog d = Dog.builder().name(dog.getName()).password(dog.getPassword()).role(dog.getRole()).build();
			Dog newDog = dogRepo.save(d);
			if (newDog != null) {
				log.info("New dog \"{}\" registerd sucessfully!", dog.getName());
				return mapper.map(newDog, DogDto.class);
			} else {
				log.error("Unable to register new dog \"{}\": unable to save dog", dog.getName());
				throw new SniffableException("unable to save dog");
			}
		} else {
			log.warn("Unable to register new dog \"{}\": already exists!", dog.getName());
			throw new SniffableAlreadyExistsException("name alreday exists");
		}
	}

<<<<<<< HEAD
	// TODO
	/*
	 * public DogDTO updateDog() { }
	 */

	//---------_> BEGIN Tob changes
	
/*
	public void commentPubdate(int id, int cid) throws SniffableException {
		if (dogRepo.existsById(id) && pubdateRepo.existsById(cid)  ) {
			Dog dog = dogRepo.findById(id).get();
			Pubdate pubdate = pubdateRepo.findById(cid).get();
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
*/	
//----------------_> END Tob changes	
	
	public void likePubdate(int id, int pid) throws SniffableException {
=======
	public void likePubdate(int id, int pid) {
>>>>>>> refs/remotes/origin/berger
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

	public void sharePubdate(int id, int pid) {
		if (dogRepo.existsById(id) && pubdateRepo.existsById(pid)) {
			Dog dog = dogRepo.findById(id).get();
			Pubdate pubdate = pubdateRepo.findById(pid).get();
			if (dog.equals(pubdate.getDog())) {
				throw new SniffableIllegalValueException("self share not allowed");
			}
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

	public void followDog(int id, int did) {
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

	public Set<PubdateDto> getPubdates(int id) {
		Optional<Dog> dog = dogRepo.findById(id);
		if (dog.isPresent()) {
			return dog.get().getPubdates().stream().map(p -> mapper.map(p, PubdateDto.class))
					.collect(Collectors.toSet());
		} else {
			throw new SniffableNotFoundException("dog with id \"" + id + "\" + not exists");
		}
	}

	public Set<PubdateDto> getTimeline(int id) {
		Optional<Dog> dog = dogRepo.findById(id);
		Set<PubdateDto> timeline = new TreeSet<>();
		for (Dog d : dog.orElseThrow(() -> new SniffableNotFoundException("dog with id \"" + id + "\" + not exists"))
				.getFollow()) {
			timeline.addAll(
					d.getPubdates().stream().map(p -> mapper.map(p, PubdateDto.class)).collect(Collectors.toSet()));
			timeline.addAll(
					d.getShared().stream().map(p -> mapper.map(p, PubdateDto.class)).collect(Collectors.toSet()));
		}
		return timeline;
	}
	
	// TODO
	/*
	 * public DogDTO updateDog() { }
	 */

	/*
	 * public DogDTO registerDog(String name, String password, Role role) throws
	 * SniffableException { if (name == null || name.isEmpty()) {
	 * log.warn("Unable to register new dog \"{}\": name null or empty", name);
	 * throw new SniffableIllegalValueException("name null or empty"); } if
	 * (dogRepo.findByNameIgnoreCase(name).isEmpty()) { Dog d =
	 * Dog.builder().name(name).password(password).role(role).build();
	 * d.setRole(role); Dog newDog = dogRepo.save(d); if (newDog != null) {
	 * log.info("New dog \"{}\" registerd sucessfully!", name); return new
	 * DogDTO(newDog); } else {
	 * log.error("Unable to register new dog \"{}\": unable to save dog", name);
	 * throw new SniffableException("unable to save dog"); } } else {
	 * log.warn("Unable to register new dog \"{}\": already exists!", name); throw
	 * new SniffableAlreadyExistsException("name alreday exists"); } }
	 */

=======
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class DogService {
	@Autowired
	DogRepo dogRepo;
	
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(DogService.class);
	
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
	
	public DogDTO getByName(String name) throws SniffableNotFoundException {
		if (!dogRepo.findByNameIgnoreCase(name).isEmpty()) {
			return new DogDTO(dogRepo.findByNameIgnoreCase(name).get(0));
		} else {
			throw new SniffableNotFoundException("dog with name \"" + name + "\" + not exists");
		}
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
				timeline.addAll(d.getPubdates().stream().map(p -> new PubdateDTO(p)).collect(Collectors.toSet()));
				timeline.addAll(d.getShares().stream().map(p -> new PubdateDTO(p)).collect(Collectors.toSet()));
			}
			for(Pubdate p::)
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
	
>>>>>>> refs/remotes/origin/toesch_experimental_Dto
}
