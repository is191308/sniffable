package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

public class ImageDTO {
	private int id;
	private String name;
	private byte[] imageData;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	@Override
	public String toString() {
		return "ImageDTO [id=" + id + ", name=" + name + "]";
	}

}
