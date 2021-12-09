package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableGeneralException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientDogService;

@RestController("ClientController")
@RequestMapping("/client")
public class ClientController {
	private final String masterkeyHeaderAttribute = "masterkey";
	private final String masterkeyConfigProperty = "sniffers.masterKey";
	
	@Autowired
	private ClientDogService cDogService;
	
	@Autowired
	private Environment env;
	
	/**
	 * Register a new dog (Role User)
	 * @param name - dog username
	 * @param password - login password
	 * @return DogDTO
	 */
	@GetMapping("/register")
	public DogDTO registerUserDog(@RequestParam(required = true) String name, @RequestParam(required = true) String password) {
		return regsiterDog(name, password, Role.USER);
	}
	
	/**
	 * Register a new dog (Role Admin)
	 * @param name - dog username
	 * @param password - login password
	 * @param headerKey - masterkey to create admin dogs
	 * @return
	 */
	@GetMapping("/registerAdmin")
	public DogDTO registerAdminDog(@RequestParam(required = true) String name, @RequestParam(required = true) String password, @RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk == null || !(mk.equals(headerKey))) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "missing or invalid masterkey");
		}
		return regsiterDog(name, password, Role.ADMIN);
	}
	
	/**
	 * Change the name of a dog
	 * @param dog DogDTO
	 * @return modified DogDTO
	 */
	@GetMapping("/changeName")
	public DogDTO changeDogName(@RequestBody(required = true) final DogDTO dog) {
		try {
			return cDogService.changeDogName(dog);
		} catch (SniffableGeneralException ex) {
			throw new ResponseStatusException(ex.httpErrorCode, ex.getMessage());
		}
	}	
	
	// --- helper functions ---
	
	private DogDTO regsiterDog(String name, String password, Role role) {
		if (name.isBlank() || name.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name or password empty");
		}
		try {
			return cDogService.registerDog(name, password, role);
		} catch (SniffableGeneralException ex) {
			throw new ResponseStatusException(ex.httpErrorCode, ex.getMessage());
		}
	}
}
