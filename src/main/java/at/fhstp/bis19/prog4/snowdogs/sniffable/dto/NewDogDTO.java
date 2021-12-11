package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewDogDTO {
	@NotBlank
	private String name;
	@NotBlank
	private String password;
	@NotNull
	private Dog.Role role;		
}