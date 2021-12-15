package at.fhstp.bis19.prog4.snowdogs.sniffable;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableAlreadyExistsException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.DogService;

@SpringBootTest
public class TestRegisterDog {

	private static ModelMapper mapper;

	Dog d1 = Dog.builder().name("TestDog").password("testpw").role(Role.USER).build();
	NewDogDto nd1 = mapper.map(d1, NewDogDto.class);

	Dog d2 = Dog.builder().name("TestDog").password("testpw").role(Role.USER).build();
	NewDogDto nd2 = mapper.map(d2, NewDogDto.class);

	Dog d3 = Dog.builder().name("BlankPassDog").role(Role.USER).build();
	NewDogDto nd3 = mapper.map(d3, NewDogDto.class);

	Dog d4 = Dog.builder().password("hasPassButNoName").role(Role.USER).build();
	NewDogDto nd4 = mapper.map(d4, NewDogDto.class);

	@BeforeAll
	private static void createMapper() {
		mapper = new ModelMapper();
	}

	@Autowired
	DogService dogService;

	
	@Test
	void ExistingNameShouldTriggerException() {
		dogService.createDog(nd1);
		assertThrows(SniffableAlreadyExistsException.class, () -> dogService.createDog(nd2));
	}

	@Test
	void blankPasswordShouldFail() {
		assertThrows(DataIntegrityViolationException.class, () -> dogService.createDog(nd3));
	}

	@Test
	void blankNameShouldFail() {
		assertThrows(SniffableIllegalValueException.class, () -> dogService.createDog(nd4));
	}

}
