package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import lombok.Data;

@Data
public class NewDogDTO {
	private String name;
	private String password;
	private Dog.Role role;		
}