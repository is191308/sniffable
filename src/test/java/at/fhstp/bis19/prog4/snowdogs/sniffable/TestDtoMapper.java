package at.fhstp.bis19.prog4.snowdogs.sniffable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.ImageDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Image;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*; 

@SpringBootTest
class TestDtoMapper {
	private static ModelMapper mapper;
	
	@BeforeAll
	private static void createMapper() {
		mapper = new ModelMapper();
	}

	@Test
	void testDogToNewDogMapper() {
		Dog d = Dog.builder().name("Testdog").password("testpw").role(Role.USER).build();
		NewDogDto d2 = mapper.map(d, NewDogDto.class);
		
		assertThat(d.getName(), equalTo(d2.getName()));
		assertThat(d.getPassword(), equalTo(d2.getPassword()));
		assertThat(d.getRole(), equalTo(d2.getRole()));
	}
	
	@Test
	void testNewDogToDogMapper() {
		NewDogDto d = new NewDogDto();
		d.setName("Testdog");
		d.setPassword("testpw");
		d.setRole(Role.USER);
		Dog d2 = mapper.map(d, Dog.class);
		
		assertThat(d.getName(), equalTo(d2.getName()));
		assertThat(d.getPassword(), equalTo(d2.getPassword()));
		assertThat(d.getRole(), equalTo(d2.getRole()));
	}
	
	@Test
	void testPubdateToNewPubdate() {
		Pubdate d = Pubdate.builder().title("Testpubdate").content("testcontent").picture(new Image("Testpic", "asdf".getBytes())).build();
		NewPubdateDto d2 = mapper.map(d, NewPubdateDto.class);
		
		assertThat(d.getTitle(), equalTo(d2.getTitle()));
		assertThat(d.getContent(), equalTo(d2.getContent()));
		assertThat(d.getPicture().getName(), equalTo(d2.getPicture().getName()));
		assertThat(d.getPicture().getImageData(), equalTo(d2.getPicture().getImageData()));
	}
	
	@Test
	void testNewPubdateToPubdate() {
		NewPubdateDto d = new NewPubdateDto();
		d.setTitle("Testpubdate");
		d.setContent("testcontent");
		ImageDto i = new ImageDto();
		i.setName("Testpic");
		i.setImageData("asdf".getBytes());
		d.setPicture(i);
		Pubdate d2 = mapper.map(d, Pubdate.class);
		
		assertThat(d.getTitle(), equalTo(d2.getTitle()));
		assertThat(d.getContent(), equalTo(d2.getContent()));
		assertThat(d.getPicture().getName(), equalTo(d2.getPicture().getName()));
		assertThat(d.getPicture().getImageData(), equalTo(d2.getPicture().getImageData()));
	}
}
