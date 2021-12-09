package at.fhstp.bis19.prog4.snowdogs.sniffable.dto;

import org.springframework.stereotype.Component;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;

@Component
public class DogDTO {
	private int id;
	private String name;
	private Dog.Role role;
	
	public DogDTO() {
	}
	
	public DogDTO(Dog dog) {
		this.id = dog.getId();
		this.name = dog.getName();
		this.role = dog.getRole();
	}
	
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
	public Dog.Role getRole() {
		return role;
	}
	public void setRole(Dog.Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "DogDTO [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
		
}
