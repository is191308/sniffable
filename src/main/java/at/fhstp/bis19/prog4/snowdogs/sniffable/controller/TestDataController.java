package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.*;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.CommentRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.DogRepo;
import at.fhstp.bis19.prog4.snowdogs.sniffable.repo.PubdateRepo;

/**
 * Generate Test data via GetRequest
 */
@RestController
public class TestDataController {
	private DogRepo dogRepo;
	private PubdateRepo pubdateRepo;
	private CommentRepo commentRepo;
	
	@Autowired
	public TestDataController(DogRepo dogRepo, PubdateRepo pubdateRepo, CommentRepo commentRepo) {
		this.dogRepo = dogRepo;
		this.pubdateRepo = pubdateRepo;
		this.commentRepo = commentRepo;
	}

	@GetMapping({"/testdata", "/test"})
	public String createTestData() {
		
		try {
			Dog dog1 = Dog.builder().name("UserDog1").
					password("asdfqwer123").role(Role.USER).
					build();
			dogRepo.save(dog1);
			Dog dog2 = Dog.builder().name("UserDog2").
					password("asdfqwer321").role(Role.USER).
					build();
			dogRepo.save(dog2);
			Dog dog3 = Dog.builder().name("UserDog3").
					password("asdfqwer343").
					role(Role.USER).build();
			dogRepo.save(dog3);
			
			Dog adminDog = Dog.builder().name("AdminDog1").
					password("asdfqwer786").
					role(Role.ADMIN).build();
			dogRepo.save(adminDog);
			Dog modDog = Dog.builder().name("ModeratorDog1").
					password("asdfqwer543").role(Role.MODERATOR)
					.build();
			dogRepo.save(modDog);
			
			Pubdate pub1 = Pubdate.builder().title("Erster Pubdate von Testdog1").
					content("Useless sozial media content!").dog(dog1).
					picture(Image.builder().name("Pic1").
							imageData("asdfadfasfasf".getBytes()).build()).
					build();
			pubdateRepo.save(pub1);
			
			commentRepo.save(Comment.builder().comment("Your right!").
					dog(adminDog).pubdate(pub1).
					build());
			
			commentRepo.save(Comment.builder().comment("Your totally right!").
					dog(modDog).pubdate(pub1).
					build());	
			
			pubdateRepo.save(Pubdate.builder().title("Zweiter Pubdate von Testdog1").
					content("More useless sozial media content!").dog(dog1).
					picture(Image.builder().name("Pic2").
							imageData("asdfdsfdasfasf".getBytes()).
							build()).
					build());
			pubdateRepo.save(Pubdate.builder().title("Hello").
					
					content("Hello i am Testdog2!").dog(dog2).
					picture(Image.builder().name("Pic3").
							imageData("g24535345g32g5325".getBytes()).
							build()).
					build());
			pubdateRepo.save(Pubdate.builder().title("Hello again").
					content("Please follow me!").dog(dog2).
					picture(Image.builder().name("Pic4").
							imageData("45d3d2535d25".getBytes()).
							build()).
					build());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create test data!";
		}
		return "Test data created successfully!";
	}
}
