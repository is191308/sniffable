//package at.fhstp.bis19.prog4.snowdogs.sniffable;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDto;
//import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
//import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
//import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
//import at.fhstp.bis19.prog4.snowdogs.sniffable.service.DogService;
//
//@SpringBootTest
//public class Mockblabla {
//
//	private static ModelMapper mapper;
//
//	Dog d1 = Dog.builder().name("Testdog").password("testpw").role(Role.USER).build();
//	NewDogDto nd1 = mapper.map(d1, NewDogDto.class);
//	
//	Dog d2 = Dog.builder().name("Testdog").password("testpw").role(Role.USER).build();
//	NewDogDto nd2 = mapper.map(d2, NewDogDto.class);
//	
//	
//	
//	@BeforeAll
//	private static void createMapper() {
//		mapper = new ModelMapper();
//	}
//	
//	
//	  @MockBean
//	  private DogService dogServiceMock;
//	
//	
//	  
//	  @Test
//	  void testSomething() {
//		  Mockito.when(dogServiceMock.createDog(nd1)).thenReturn(mapper.map(nd1, DogDto.class));  
//		  System.out.println(dogServiceMock.createDog(nd1));
//	  }
//}
