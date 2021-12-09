package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.*;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientDogService;

/**
 * Generate Test data via GetRequest
 * 
 * @author Stefan
 *
 */
@RestController
public class TestController {
	@Autowired
	private BaseCrudRepository<Dog> dogRepo;
	@Autowired
	private BaseCrudRepository<Pubdate> pubdateRepo;
	@Autowired
	private BaseCrudRepository<Comment> commentRepo;
	@Autowired
	ClientDogService ds;

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
			
			dogRepo.save(adminDog);
			Pubdate pubdate = new Pubdate("Erster Pubdate von Testdog1", dog);
			pubdate.setPicture(new Image("DogPic", "dfaldfa8jf3p983rpfawe8wfp9a".getBytes()));
			pubdateRepo.save(pubdate);
			
			pubdate.setContent("Useless sozial media content!");
			Comment comment = new Comment("Your right!", pubdate, dog2);
			commentRepo.save(comment);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create test data!";
		}
		return "Test data created!";
	}
}
