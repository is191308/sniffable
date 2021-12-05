package at.fhstp.bis19.prog4.snowdogs.sniffable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;

@SpringBootApplication
@RestController()
public class SniffableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SniffableApplication.class, args);
	}
	
	@Autowired
	DogRepo dogRepo;
	
	@GetMapping(value = "/cdogs")
	public String testDogRepo() {
		System.out.println("Test Create Dogs");
		Dog dog = new Dog();
		dog.setName("Test");
		dogRepo.save(dog);
		return "OK - Dog saved!";
	}
	
	@GetMapping(value = "/gdogs")
	public String getDogs() {
		System.out.println("Test Select Dogs");
		return dogRepo.findAll().toString();
	}
	
}
