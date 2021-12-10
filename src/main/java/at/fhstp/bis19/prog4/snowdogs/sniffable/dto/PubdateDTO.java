package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import java.util.Date;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PubdateDTO implements Comparable<PubdateDTO> {
	private int id;
	private String title;
	private Date timestamp;
	private String content;
	private ImageDTO picture;
	private DogDTO dog;

	public PubdateDTO(Pubdate pubdate) {
		if (pubdate != null) {
			this.id = pubdate.getId();
			this.title = pubdate.getTitle();
			this.timestamp = pubdate.getTimestamp();
			if (pubdate.getPicture() != null) {
				this.picture = new ImageDTO(pubdate.getPicture());
			}
			this.content = pubdate.getContent();
			this.dog = new DogDTO(pubdate.getDog());
		}
	}

	@Override
	public int compareTo(PubdateDTO o) {
		return this.timestamp.compareTo(o.timestamp);
	}

}
