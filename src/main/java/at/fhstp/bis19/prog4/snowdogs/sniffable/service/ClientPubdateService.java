package at.fhstp.bis19.prog4.snowdogs.sniffable.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;


@Service
public class ClientPubdateService {

	@Autowired
	DogRepo dogRepo;
	@Autowired
	PubdateRepo pubdateRepo;
	
	private static final Logger log = LoggerFactory.getLogger(ClientPubdateService.class);
	
	
	
	public Pubdate createPubdate(String title, String content, String dogID) {
		int dogIntID = 0;
		
		try {
			dogIntID = Integer.parseInt(dogID);
		} catch (Exception e) {
			throw new IllegalArgumentException("could not parse String to Int");
		}
		
		
		
		if (title == null || title.isEmpty()){
		throw new IllegalArgumentException("title null not allowed");
		}
		
		if (dogRepo.findById(dogIntID).isEmpty()) {
			log.info("Unable to find dogID,  \"{}\" does not exist!", dogIntID);
			return null;
			
		} else {
			Pubdate newPubdate = new Pubdate(title, dogRepo.findById(dogIntID).orElse(null));
			newPubdate.setContent(content);
			pubdateRepo.save(newPubdate);
			
			log.info("Dog \"{}\" successfully created new Pubdate \"{}\" with Content: \"{}\"", dogRepo.findById(dogIntID), newPubdate, newPubdate.getContent());
			return newPubdate;
		}
	}
		

				

}
