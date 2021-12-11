package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewPubdateDTO implements BaseDTO {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private ImageDTO picture;
	private DogDTO dog;
}
