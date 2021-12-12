package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.DogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.PubdateDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.dto.NewDogDto;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog.Role;
import at.fhstp.bis19.prog4.snowdogs.sniffable.exception.SniffableIllegalValueException;
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
	public DogDto createDog(@RequestBody(required = true) @Valid NewDogDto dog,
			@RequestHeader(value = masterkeyHeaderAttribute, required = false) String headerKey) {
		String mk = env.getProperty(masterkeyConfigProperty);
		// Force Role User if masterkey is not present or invalid
		if (mk == null || !(mk.equals(headerKey))) {
			dog.setRole(Role.USER);
		}
		return cDogService.createDog(dog);
	}

	/**
	 * LIKE SHARE FOLLOW
	 * 
	 * @param id     dog id
	 * @param pid    pubdate or dog id
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

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
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	          String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
	          List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
	          return new ResponseEntity<>(validationList, HttpStatus.BAD_REQUEST);
	   }
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ResponseEntity<Object>handleMethodArgumentNotValidException(final HttpServletRequest request, final MethodArgumentNotValidException e) {
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", new Date());
        map.put("status", 400);
        map.put("error", "Bad Request");
        map.put("message", e.getAllErrors().stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString());
        map.put("path", request.getRequestURI());

        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
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
