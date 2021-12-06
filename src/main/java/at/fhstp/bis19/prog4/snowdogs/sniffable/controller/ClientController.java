package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientDogService;

@RestController("ClientController")
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientDogService cDogService;
	
	@GetMapping("/register")
	public Dog registerDog(@RequestParam(required = true) String name) {
		if (name.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name empty");
		}
		Dog dog = cDogService.registerDog(name);
		if (dog == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "name already exists");
		}
		return dog; 
	}
}
