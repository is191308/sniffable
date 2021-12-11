package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewPubdateDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDTO;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableNotFoundException;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.DogService;

@RestController
@RequestMapping("/dog")
public class DogController {
	private final String masterkeyHeaderAttribute = "masterkey";
	private final String masterkeyConfigProperty = "sniffers.masterKey";

	@Autowired
	private DogService cDogService;

	@Autowired
	private Environment env;

	/**
	 * CREATE Dog
	 * @param dog (name, password, role)
	 * @param headerKey
	 * @return dog
	 */
	@PostMapping()
	public DogDTO registerUserDogPost(@RequestBody(required = true) NewDogDTO dog, @RequestHeader(value = masterkeyHeaderAttribute, required = false) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		try {
			// Force Role User if masterkey is not present or invalid
			if (mk == null || !(mk.equals(headerKey))) {
				dog.setRole(Role.USER);
			}
			return cDogService.createDog(dog);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}

	/**
	 * SELECT ALL
	 * @return dog
	 */
	@GetMapping()
	public Set<DogDTO> getDogs() {
		return cDogService.getAll();
	}
	
	/**
	 * SELECT by ID
	 * @param id ID
	 * @return dog
	 */
	@GetMapping(value = "{id}")
	public DogDTO getDogByID(@PathVariable(value = "id", required = true) int id) {
		try {
			return cDogService.getById(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}

	/**
	 * DELETE by ID
	 * @param id ID
	 */
	@DeleteMapping(value = "{id}")
	public void deleteDogById(@PathVariable(value = "id", required = true) int id) {
		try {
			cDogService.delete(id);
		} catch (SniffableNotFoundException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * LIKE SHARE FOLLOW
	 * @param id dog id
	 * @param pid pubdate or dog id
	 * @param action (like|follow|share)
	 */
	@PostMapping(value = "{id}/{action}/{pid}")
	public void createLikeShareFollow(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "pid", required = true) int pid, @PathVariable(value = "action", required = true) String action) {
		try {
			switch (action) {
			case "like":
				cDogService.likePubdate(id, pid);
				break;
			case "share":
				cDogService.sharePubdate(id, pid);
				break;
			case "follow":
				cDogService.followDog(id, pid);
				break;
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/**
	 * TIMELINE
	 * @param id dogid
	 * @return timeline
	 * @throws SniffableException
	 */
	@GetMapping(value = "{id}/timeline")
	public Set<NewPubdateDTO> getTimeline(@PathVariable(value = "id", required = true) int id) {
		try {
			return cDogService.getTimeline(id);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}
	
	/*@DeleteMapping(value = {"{id}/{action}/{pid}"})
	public void deleteLikeShareFollow(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "pid", required = true) int pid, @PathVariable(value = "action", required = true) String action) {
		try {
			switch (action) {
			case "like":
				cDogService.likePubdateDelete(id, pid);
				break;
			case "share":
				cDogService.sharePubdateDelete(id, pid);
				break;
			case "follow":
				cDogService.followDogDelete(id, pid);
				break;
			default:
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}*/
	

	/*
	@GetMapping("/changeName")
	public DogDTO changeDogName(@RequestBody(required = true) final DogDTO dog) {
		try {
			return cDogService.changeDogName(dog);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}*/
	
	/*
	@GetMapping("/register")
	public DogDTO registerUserDog(@RequestParam(required = true) String name,
			@RequestParam(required = true) String password) {
		return regsiterDog(name, password, Role.USER);
	}*/

	/*@GetMapping("/registerAdmin")
	public DogDTO registerAdminDog(@RequestParam(required = true) String name,
			@RequestParam(required = true) String password, @RequestHeader(masterkeyHeaderAttribute) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		if (mk == null || !(mk.equals(headerKey))) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "missing or invalid masterkey");
		}
		return regsiterDog(name, password, Role.ADMIN);
	}
	
	private DogDTO regsiterDog(String name, String password, Role role) {
		if (name.isBlank() || name.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name or password empty");
		}
		try {
			return cDogService.registerDog(name, password, role);
		} catch (SniffableException ex) {
			throw new ResponseStatusException(ex.getHTTPStatus(), ex.getMessage());
		}
	}*/
	
	
	
}
