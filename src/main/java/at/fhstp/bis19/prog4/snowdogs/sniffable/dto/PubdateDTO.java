package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PubdateDTO implements Comparable<PubdateDTO>, BaseDTO {
	private int id;
	private String title;
	private Date timestamp;
	private String content;
	private ImageDTO picture;
	private DogDTO dog;
	private Set<CommentDTO> comments;

	@Override
	public int compareTo(PubdateDTO o) {
		return o.timestamp.compareTo(this.timestamp);
	}

}
