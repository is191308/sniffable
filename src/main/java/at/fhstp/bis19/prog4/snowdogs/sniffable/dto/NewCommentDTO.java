package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCommentDTO implements BaseDTO {
	@NotBlank
	private String comment;
	private NewPubdateDTO pubdate;
	private DogDTO dog;
}
