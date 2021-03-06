package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.util.Set;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.DogService;

@RestController
@RequestMapping("/dog")
public class DogController extends BaseController<Dog, DogDto> {
	private final String masterkeyHeaderAttribute = "masterkey";
	private final String masterkeyConfigProperty = "sniffers.masterKey";

	private DogService cDogService;
	private Environment env;
	
	@Autowired
	public DogController(DogService cDogService, Environment env) {
		super(cDogService);
		this.env = env;
		this.cDogService = cDogService;
	}

	/**
	 * CREATE Dog
	 * @param dog (name, password, role)
	 * @param headerKey
	 * @return dog
	 */
	@PostMapping()
	public DogDto createDog(@RequestBody(required = true) @Valid final NewDogDto dog,
			@RequestHeader(value = masterkeyHeaderAttribute, required = false) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		// Force Role User if masterkey is not present or invalid
		if (mk != null && !(mk.equals(headerKey))) {
			dog.setRole(Role.USER);
		}
		return cDogService.createDog(dog);
	}

	/**
	 * LIKE SHARE FOLLOW
	 * 
	 * @param id     dog id
	 * @param pid    pubdate id
	 * @param action (like|follow|share)
	 */
	@PostMapping(value = "{id}/{action}/{pid}")
	public void createLikeShareFollow(@PathVariable(value = "id", required = true) int id,
			@PathVariable(value = "pid", required = true) int pid,
			@PathVariable(value = "action", required = true) String action) {
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "action \"" + action + "\" not allowed");
		}
	}
	
	//---------------------Begin TOB Change>
	/**
	 * Post Comment
	 * @param id dog id
	 * @param action (comment)
	 */
	
	/*
	@PostMapping(value = "{id}/{action}")
	public void createLikeShareFollow(@PathVariable(value = "id", required = true) int id,
								@PathVariable(value = "action", required = true) String action) {
		try {
			switch (action) {
			case "comment":
				cDogService.addCommment(id,action);
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
//-----------------------_END TOB Change>
*/	
	
	/**
	 * TIMELINE
	 * 
	 * @param id dogid
	 * @return timeline
	 */
	@GetMapping(value = "{id}/timeline")
	public Set<PubdateDto> getTimeline(@PathVariable(value = "id", required = true) int id) {
		return cDogService.getTimeline(id);
	}

	/**
	 * PUBDATES
	 * 
	 * @param id dogid
	 * @return Pubdates
	 */
	@GetMapping(value = "{id}/pubdates")
	public Set<PubdateDto> getPubdates(@PathVariable(value = "id", required = true) int id) {
		return cDogService.getPubdates(id);
	}

	/*
	 * @DeleteMapping(value = {"{id}/{action}/{pid}"}) public void
	 * deleteLikeShareFollow(@PathVariable(value = "id", required = true) int
	 * id, @PathVariable(value = "pid", required = true) int
	 * pid, @PathVariable(value = "action", required = true) String action) { switch
	 * (action) { case "like": cDogService.likePubdateDelete(id, pid); break; case
	 * "share": cDogService.sharePubdateDelete(id, pid); break; case "follow":
	 * cDogService.followDogDelete(id, pid); break; default: throw new
	 * ResponseStatusException(HttpStatus.BAD_REQUEST); }
	 */
}
