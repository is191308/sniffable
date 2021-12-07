package at.fhstp.bis19.prog4.snowdogs.sniffable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import at.fhstp.bis19.prog4.snowdogs.sniffable.entity.Pubdate;
import at.fhstp.bis19.prog4.snowdogs.sniffable.service.ClientDogService;

@RestController("PubdateController")
@RequestMapping("/restapi/pubdate")
public class PubdateController extends GenericContoller<Pubdate> {
	
	@Autowired
	private ClientDogService cDogService;
	
	@PostMapping("/addPubdate")
	public Pubdate addPubdate(@RequestParam(required = true) String title, String content, String dogname){
		
		if ((title == null) || (dogname == null))  {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title or dogname is empty");
		}
		

		Pubdate pubdate = cDogService.createPubdate(title, content, dogname);
		if (pubdate == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "no such dog registered");
		}
		return pubdate; 
	}
		
	
	
}
