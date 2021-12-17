package at.fhstp.bis19.prog4.snowdogs.sniffable;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.ArgumentMatchers.contains;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Image;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.PubdateService;

@SpringBootTest
public class TestPubdateTransition {

	@MockBean
	private PubdateService pubdateServiceMock;

	
	private static ModelMapper mapper;
	
	//craft NewPubdate for given Object Test
	Dog d1 = Dog.builder().name("TestDog").password("testpw").role(Role.USER).build();
	Image i1 = Image.builder().name("Pic1").imageData("asdfadfasfasf".getBytes()).build();
	Pubdate p1 = Pubdate.builder().title("testTitle").content("testContent").dog(d1).picture(i1).build();
	NewPubdateDto np1 = mapper.map(p1, NewPubdateDto.class);

	
	@BeforeAll
	private static void createMapper() {
		mapper = new ModelMapper();
	}
	  
	  @Test
	  void testDtoTransition() {
		  //prepare	-> return PubdateDto
		  Mockito.when(pubdateServiceMock.createPubdate(np1)).thenReturn(mapper.map(np1, PubdateDto.class));  
		  
		  //test correct transition from given NewPubdateDto to PubdateDto
		  assertFalse(pubdateServiceMock.createPubdate(np1).getClass().equals(np1.getClass()));

		  //verify method only called once
		  verify(pubdateServiceMock, times(1)).createPubdate(np1);  

	  }

}
