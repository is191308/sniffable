package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDTO implements BaseDTO {
	private int id;
	private String name;
	private byte[] imageData;
}
