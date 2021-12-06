package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;

@Service
public class ClientDogService {
	@Autowired
	DogRepo dogRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientDogService.class);
	
	public Dog registerDog(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name null not allowd");
		}
		if (dogRepo.findByNameIgnoreCase(name).isEmpty()) {
			Dog newDog = dogRepo.save(new Dog(name));
			if (newDog != null) {
				log.info("New dog \"{}\" registerd sucessfully!", name);
				return newDog;
			}
		} else {
			log.info("Unable to register new dog \"{}\": already exists!", name);
			return null;
		}
		log.info("Unable to register new dog \"{}\": unknown error!", name);
		return null;
	}
}
