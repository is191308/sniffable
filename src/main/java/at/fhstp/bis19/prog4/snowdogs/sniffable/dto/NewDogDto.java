package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewDogDto implements BaseDto {
	@NotBlank(message = "blank name not allowed")
	private String name;
	@Size(min = 8, message = "password must be min 8 characters")
	private String password;
	private Dog.Role role;
}