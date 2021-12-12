package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PubdateDto implements Comparable<PubdateDto>, BaseDto {
	private int id;
	private String title;
	private Date timestamp;
	private String content;
	private ImageDto picture;
	private DogDto dog;
	private Set<CommentDto> comments;

	@Override
	public int compareTo(PubdateDto o) {
		return o.timestamp.compareTo(this.timestamp);
	}

}
