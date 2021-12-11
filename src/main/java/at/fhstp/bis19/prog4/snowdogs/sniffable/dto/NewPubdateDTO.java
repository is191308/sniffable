package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewPubdateDTO {
	private String title;
	private String content;
	private ImageDTO picture;
	private DogDTO dog;

	public NewPubdateDTO(Pubdate pubdate) {
		if (pubdate != null) {
			this.title = pubdate.getTitle();
			if (pubdate.getPicture() != null) {
				this.picture = new ImageDTO(pubdate.getPicture());
			}
			this.content = pubdate.getContent();
			this.dog = new DogDTO(pubdate.getDog());
		}
	}

}
