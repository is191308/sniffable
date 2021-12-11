package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogDTO {
	private int id;
	private String name;
	private Dog.Role role;
	
	/*public DogDTO(Dog dog) {
		this.id = dog.getId();
		this.name = dog.getName();
		this.role = dog.getRole();
	}*/
}
