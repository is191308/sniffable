package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;

/*
 * Test ... remove!
 */
@RestController
public class TestController {
	/*@Autowired
	DogRepo dogRepo;*/
	
	/*@GetMapping(name = "/dog")
	public String testDogRepo() {
		System.out.println("Test");
		Dog dog = new Dog();
		dog.setName("Test");
		dogRepo.save(dog);
		return "OK";
	}*/
}
