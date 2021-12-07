package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

@Service
public class ClientDogService {
	@Autowired
	DogRepo dogRepo;
	
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientDogService.class);
	
	public Dog registerDog(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("name null not allowed");
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
	}
	
}




