package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCommentDto implements BaseDto {
	@NotBlank
	private String comment;
	private NewPubdateDto pubdate;
	private DogDto dog;
}
