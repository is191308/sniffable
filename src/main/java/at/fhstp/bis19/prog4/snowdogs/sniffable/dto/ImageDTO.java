package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Image;
import lombok.Data;

@Data
public class ImageDTO {
	private int id;
	private String name;
	private byte[] imageData;
	
	public ImageDTO(Image image) {	
		this.id = image.getId();
		this.name = image.getName();
		this.imageData = image.getImageData();
	}
}
