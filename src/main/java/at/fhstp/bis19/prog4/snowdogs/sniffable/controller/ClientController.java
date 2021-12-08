package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Comment;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Dog;
import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientCommentService;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientDogService;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientPubdateService;

@RestController("ClientController")
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientDogService cDogService;
	@Autowired
	private ClientPubdateService cPubdateService;
	@Autowired
	private ClientCommentService cCommentService;
	
	
	
	@GetMapping("/register")
	//GET METHOD -> localhost:8080/client/register/?name=johnDog
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
		
	
	@PostMapping("/addPubdate")
	//POST METHOD -> localhost:8080/client/addPubdate?title=testtitle&dogID=1&content=got it
	public Pubdate addPubdate(@RequestParam(required = true) String title, String content, String dogID){
			
		if ((title == null) || (dogID == null))  {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or dogID is empty");
			}
			

		Pubdate pubdate = cPubdateService.createPubdate(title, content, dogID);
		if (pubdate == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "no such dog registered");
			}
		return pubdate; 
		}
	
	
	@PostMapping("/addComment")
	//POST METHOD -> localhost:8080/client/addComment?pubdateComment=yoyo&pubdateID=2&dogID=1
	public Comment addComment(@RequestParam(required = true) String pubdateComment, String pubdateID, String dogID){
		
		if ((pubdateComment == null) || (pubdateID == null || (dogID == null)))  {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pubdateComment or pubdateID or dogID is empty");
		}
		

		Comment comment = cCommentService.createComment(pubdateComment, pubdateID, dogID);
		if (comment == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "no such dog registered or pubdate found");
		}
		return comment;
	}
	
	
	@PostMapping("/likePubdate")
	//POST METHOD -> localhost:8080/client/likePubdate?pubdateID=2&dogID=1
	public Pubdate likePubdate(@RequestParam(required = true) String pubdateID, String dogID) {
		
		if (dogID == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pubdateID or dogID is empty");
		}
		
		Pubdate pubdate = cPubdateService.createDogLikeOnPubdate(pubdateID, dogID);
		if (pubdate == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "invalid pubdateID or dogID");
		}
		
		return pubdate;
	}
}
