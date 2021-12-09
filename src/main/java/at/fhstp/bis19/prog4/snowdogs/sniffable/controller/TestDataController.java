package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.*;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.DogService;

/**
 * Generate Test data via GetRequest
 * 
 * @author Stefan
 *
 */
@RestController
public class TestDataController {
	@Autowired
	private BaseCrudRepository<Dog> dogRepo;
	@Autowired
	private BaseCrudRepository<Pubdate> pubdateRepo;
	@Autowired
	private BaseCrudRepository<Comment> commentRepo;
	@Autowired
	DogService ds;

	@GetMapping("/testdata")
	public String createTestData() {
		try {
			Dog dog = new Dog("UserDog1", "qwertz");
			dogRepo.save(dog);
			Dog dog2 = new Dog("UserDog2", "qwertz");
			dogRepo.save(dog2);
			
			Dog dog3 = new Dog("CommentDog", "asdf1234");
			dogRepo.save(dog3);
			
			Dog adminDog = new Dog("AdminDog", "asdf1234");
			adminDog.setRole(Role.ADMIN);
			dogRepo.save(adminDog);
			
			Dog modDog = new Dog("ModeratorDog", "mod1234");
			modDog.setRole(Role.MODERATOR);
			dogRepo.save(modDog);
			
			Pubdate pubdate = new Pubdate("Erster Pubdate von Testdog1", dog);
			pubdate.setContent("Useless sozial media content!");
			pubdate.setPicture(new Image("DogPic", "dfaldfa8jf3p983rpfawe8wfp9a".getBytes()));
			pubdateRepo.save(pubdate);
			
			Pubdate pubdate2 = new Pubdate("Zweiter Pubdate von Testdog1", dog);
			pubdate2.setContent("More useless sozial media content!");
			pubdate2.setPicture(new Image("DogPic2", "dfaldfa8jf3dffdfdp983rpfawe8wfp9a".getBytes()));
			pubdateRepo.save(pubdate2);
			
			Pubdate pubdate3 = new Pubdate("Erster Pubdate von Testdog2", dog2);
			pubdate3.setContent("Hi ist me Testdog 2!");
			pubdate3.setPicture(new Image("DogPic3", "sdfdsfdsfdsfdsfddsf".getBytes()));
			pubdateRepo.save(pubdate3);
			
			Pubdate pubdate4 = new Pubdate("Share example Pudate", modDog);
			pubdate4.setContent("This is to test share feature!");
			pubdate4.setPicture(new Image("DogPic4", "sdfdsfdsfdsfdsfddsf".getBytes()));
			pubdateRepo.save(pubdate4);
			
			
			Comment comment = new Comment("Your right!", pubdate, dog3);
			Comment comment2 = new Comment("Your totally right!", pubdate3, modDog);
			commentRepo.save(comment);
			commentRepo.save(comment2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create test data!";
		}
		return "Test data created!";
	}
}
