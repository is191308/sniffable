package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.*;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.BaseCrudRepository;

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

	@GetMapping("/testdata")
	public String createTestData() {
		try {
			Dog dog = new Dog("Testdog1");
			dogRepo.save(dog);
			Dog dog2 = new Dog("CommentDog1");
			dogRepo.save(dog2);
			Pubdate pubdate = new Pubdate("Erster Pubdate von Testdog1", dog);
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
