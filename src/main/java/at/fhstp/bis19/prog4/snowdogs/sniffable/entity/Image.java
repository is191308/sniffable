package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "image")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@Column(name = "imageData", nullable = false)
	private byte[] imageData;
	
}
