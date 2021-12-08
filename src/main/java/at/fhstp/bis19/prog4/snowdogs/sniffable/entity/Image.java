package at.fhstp.bis19.prog4.snowdogs.sniffable.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "image")
@Entity
public class Image extends BaseEntity {
	@Column(name = "name")
	private String Name;
	
	@JsonIgnore
	@Column(name = "imageData")
	private byte[] imageData;

	public Image() {
	}
	
	public Image(byte[] imageData) {
		this("unnamed picture", imageData);
	}
	
	public Image(String name, byte[] imageData) {
		super();
		Name = name;
		this.imageData = imageData;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Image [id=\"" + super.getId() + ", Name=" + Name + "]";
	}
	
}
