package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewPubdateDto implements BaseDto {
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private ImageDto picture;
	@NotNull
	private DogDto dog;
}
